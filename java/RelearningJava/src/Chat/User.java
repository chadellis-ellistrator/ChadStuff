package Chat;

public class User {
    private String name;
    private String password;
    private boolean isLoggedIn = false;

    public User(String userName, String password) {
        this.name = userName;
        this.password = password;
    }

    public boolean authenticate(String password) {
        this.isLoggedIn = password.equals(this.password);
        return this.isLoggedIn();
    }

    public String getName() {
        return this.name;
    }

    public boolean isLoggedIn() {
        return this.isLoggedIn;
    }
}
