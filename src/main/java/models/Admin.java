package models;

public class Admin extends User{
    private String adminLevel;

    public Admin(int id, String record, String password, String adminLevel) {
        super(id, record, password, UserRole.ADMIN);
        this.adminLevel = adminLevel;
    }

    public Admin(String record, String password, String adminLevel) {
        super(record, password, UserRole.ADMIN);
        this.adminLevel = adminLevel;
    }

    public String getAdminLevel() {
        return adminLevel;
    }

    public void setAdminLevel(String adminLevel) {
        this.adminLevel = adminLevel;
    }

    @Override
    public String toString() {
        return "Admin{" +
                "adminLevel='" + adminLevel + '\'' +
                ", id=" + id +
                ", record='" + record + '\'' +
                ", password='" + password + '\'' +
                ", role=" + role +
                '}';
    }
}
