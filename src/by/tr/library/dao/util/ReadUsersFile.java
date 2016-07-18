package by.tr.library.dao.util;

import by.tr.library.dao.exception.DAOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by Larisa_Bigvava on 7/18/2016.
 */
public class ReadUsersFile {
    private final static Logger LOGGER = LogManager.getRootLogger();
    private final static String USERS_FILE = "C:\\ \\_poit+epam\\test automation\\лаба\\Library\\src\\resources\\users.txt";

    public static List<String> readUsers() throws DAOException {
        List<String> strings = new ArrayList<>();
        try (FileInputStream fis = new FileInputStream(new File(USERS_FILE))) {
            Scanner scanner = new Scanner(fis);
            while (scanner.hasNextLine()) {
                strings.add(scanner.nextLine());
            }
        } catch (IOException ex) {
            LOGGER.error(ex.getMessage());
            throw new DAOException("File with users data not found!", ex);
        }
        return strings;
    }
}
