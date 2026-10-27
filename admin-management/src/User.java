// Base class for all user types in the Real Estate Portal (Admin, Agent, Client etc.)
// Keeping the common fields here so Admin can inherit them instead of repeating

public abstract class User {

    protected String userId;
    protected String username;
    protected String password;
    protected String email;

    public User(String userId, String username, String password, String email) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public String getUserId() {
        return userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    // every user type shows its own dashboard info - this is what makes it polymorphic
    public abstract String getDashboardInfo();

    // used when saving to a text file, each subclass can format it differently
    public abstract String toFileString();
}
