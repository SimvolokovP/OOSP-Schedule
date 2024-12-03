package models;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserFactory {
    public static User createUserFromResultSet(ResultSet rs) throws SQLException {
        UserRole role = UserRole.valueOf(rs.getString("role").toUpperCase());
        User user = null;
        switch (role) {
            case ADMIN:
                user = new Admin(rs.getInt("id"), rs.getString("record"), rs.getString("password"), rs.getString("adminLevel"));
                break;
            case STUDENT:
                user = new Student(rs.getInt("id"), rs.getString("record"), rs.getString("password"), rs.getInt("group"));
                break;
            case TEACHER:
                user = new Teacher(rs.getInt("id"), rs.getString("record"), rs.getString("password"), rs.getString("department"));
                break;
                default:
                    throw new IllegalArgumentException("Unknown role: " + role);
        }
        return user;
    }
}
