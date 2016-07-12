package by.tr.library.bean;

/**
 * Created by Larisa_Bigvava on 7/8/2016.
 */
public class User {
    private String login;
    private String password;
    private String role; //admin or user
    private boolean blocked;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public boolean getBlocked() {
        return blocked;
    }

    public void setBlocked(boolean blocked) {
        this.blocked = blocked;
    }
}
