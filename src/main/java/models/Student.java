package models;

public class Student extends User{
    private int group;

    public Student(int id, String record, String password, int group) {
        super(id, record, password, UserRole.STUDENT);
        this.group = group;
    }

    public Student(String record, String password, int group) {
        super(record, password, UserRole.STUDENT);
        this.group = group;
    }

    public int getGroup() {
        return group;
    }

    public void setGroup(int group) {
        this.group = group;
    }

    @Override
    public String toString() {
        return "Student{" +
                "group=" + group +
                ", id=" + id +
                ", record='" + record + '\'' +
                ", password='" + password + '\'' +
                ", role=" + role +
                '}';
    }
}
