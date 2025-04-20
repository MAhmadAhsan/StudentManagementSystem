package Details;

import java.security.SecureRandom;

public class Credentials {
    private String password;
    private String username;

    // Constructor
    public Credentials(){}
    public Credentials(String password, String username) {
        this.setPassword(password);
        this.setUsername(username);
    }

    // Getters
    public String getPassword() {
        return password;
    }
    public String getUsername() {
        return username;
    }

    // Setters
    public void setPassword(String password) {
        if (password.length() <= 8) {
            throw new IllegalArgumentException("Password should have at least 8 characters.");
        } else if (password.contains(",")) {
            throw new IllegalArgumentException("Password should not contain a comma.");
        } else {
            this.password = password;
        }
    }

    public void setUsername(String username) {
        if (username.length() <= 3) {
            throw new IllegalArgumentException("Username should have at least 3 characters.");
        }else if (username.contains(",")) {
            throw new IllegalArgumentException("Username should contain comma.");
        }else{
            this.username = username;
        }
    }


    // toString Method
    @Override
    public String toString() {
        return getUsername()  + "," + getPassword();
    }
}
