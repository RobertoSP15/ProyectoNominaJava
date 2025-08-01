package Login;

public class User {
    String username;
    String password;
    int accessLevel;

    public User(String username, String password, int accessLevel) {
        this.username = username;
        this.password = password;
        this.accessLevel = accessLevel;
    }
}