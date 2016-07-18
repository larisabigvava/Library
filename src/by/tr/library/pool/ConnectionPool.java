package by.tr.library.pool;

import by.tr.library.pool.exception.DatabaseConnectorException;

import javax.annotation.PreDestroy;
import java.sql.SQLException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConnectionPool {
    private static final int CREATE_ATTEMPTS = 5;
    private static final long CLOSE_TIMEOUT_SEC = 1;
    private static final String DRIVER = "com.mysql.jdbc.Driver";

    private static AtomicBoolean created = new AtomicBoolean(false);
    private static ConnectionPool instance;
    private static AtomicBoolean closed = new AtomicBoolean(false);
    private static Lock lock = new ReentrantLock();

    private ArrayBlockingQueue<ProxyConnection> availableConnections;
    private ConcurrentLinkedQueue<ProxyConnection> usedConnections;
    private int poolSize = 16;

    private ConnectionPool() {
        availableConnections = new ArrayBlockingQueue<>(poolSize);
        usedConnections = new ConcurrentLinkedQueue<>();

        try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException ex) {
            throw new RuntimeException(ex);
        }

        for (int i = 0; i < poolSize; i++) {
            addConnection();
        }
    }

    public static ConnectionPool getInstance() {
        if (!created.get()) {
            lock.lock();
            try {
                if (instance == null) {
                    instance = new ConnectionPool();
                    created.set(true);
                }
            } finally {
                lock.unlock();
            }
        }
        return instance;
    }

    void addConnection() {
        boolean added = false;
        int attempts = 0;
        while (!added) {
            try {
                added = availableConnections.add(DatabaseConnector.getConnection());
            } catch (DatabaseConnectorException ex) {
                if (attempts != CREATE_ATTEMPTS) {
                    added = false;
                    attempts++;
                } else {
                    added = true;
                }
            }
        }
    }

    public ProxyConnection getConnection() {
        ProxyConnection connection = null;
        if (!closed.get()) {
            try {
                connection = availableConnections.take();
                usedConnections.add(connection);
            } catch (InterruptedException ex) {
            }
        }

        return connection;
    }

    void freeConnection(ProxyConnection connection) {
        usedConnections.remove(connection);
        try {
            availableConnections.put(connection);
        } catch (InterruptedException ex) {
        }
    }

    @PreDestroy
    private void releasePool() {
        closed.set(true);
        try {
            TimeUnit.SECONDS.sleep(CLOSE_TIMEOUT_SEC);
            for (ProxyConnection connection : availableConnections) {
                try {
                    connection.closeConnection();
                } catch (SQLException ex) {
                }
            }
            if (!usedConnections.isEmpty()) {
                for (ProxyConnection connection : usedConnections) {
                    try {
                        connection.closeConnection();
                    } catch (SQLException ex) {
                    }
                }
            }
        } catch (InterruptedException ex) {
        }
    }

    boolean checkPoolSize() {
        int currentPoolSize = availableConnections.size() + usedConnections.size();

        return currentPoolSize == poolSize;
    }

    public int getPoolSize(){
        return usedConnections.size() + availableConnections.size();
    }
}
