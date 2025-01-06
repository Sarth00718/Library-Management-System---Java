package lmsystem.model;
public abstract class Person {
    protected String username;
    private String password;

    public Person(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() { return username; }
    public String getPassword() { return password; }

    public abstract void login();
    public abstract void logout();
}
