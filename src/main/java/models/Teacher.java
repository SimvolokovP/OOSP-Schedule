package models;

public class Teacher extends User{
    private String department;

    public Teacher(int id, String record, String password, String department) {
        super(id, record, password, UserRole.TEACHER);
        this.department = department;
    }

    public Teacher(String record, String password, String department) {
        super(record, password, UserRole.TEACHER);
        this.department = department;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "department='" + department + '\'' +
                ", id=" + id +
                ", record='" + record + '\'' +
                ", password='" + password + '\'' +
                ", role=" + role +
                '}';
    }
}
