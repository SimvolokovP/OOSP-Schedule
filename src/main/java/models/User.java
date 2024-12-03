package models;

public abstract class User {
    protected int id;
    protected String record;
    protected String password;
    protected UserRole role;

    public User() {
    }

    public User(int id, String record, String password, UserRole role) {
        this.id = id;
        this.record = record;
        this.password = password;
        this.role = role;
    }

    public User(String record, String password, UserRole role) {
        this.record = record;
        this.password = password;
        this.role = role;
    }

    public String getRecord() {
        return record;
    }

    public void setRecord(String record) {
        this.record = record;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserRole getRole() {
        return role;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }
}
