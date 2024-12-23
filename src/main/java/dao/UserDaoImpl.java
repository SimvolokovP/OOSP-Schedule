package dao;

import at.favre.lib.crypto.bcrypt.BCrypt;
import models.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements Dao<User>{
    private Connection connection;

    public UserDaoImpl(Connection connection) {
        this.connection = connection;
    }
    @Override
    public boolean add(User user) {
        String sql = "INSERT INTO users (record, password, role, adminLevel, department, `group`) VALUES (?, ?, ?, ?, ?, ?)";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, user.getRecord());

            String hashedPassword = BCrypt.withDefaults().hashToString(12, user.getPassword().toCharArray());
            ps.setString(2, hashedPassword);

            ps.setString(3, user.getRole().toString().toUpperCase());

            insertUser(ps, user);

            return ps.executeUpdate() == 1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean edit(int id, User user) {
        String sql = "UPDATE users SET record = ?, password = ?, role = ?, adminLevel = ?, department = ?, `group` = ? WHERE id = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, user.getRecord());

            String hashedPassword = BCrypt.withDefaults().hashToString(12, user.getPassword().toCharArray());
            ps.setString(2, hashedPassword);

            ps.setString(3, user.getRole().toString().toUpperCase());
            insertUser(ps, user);
            ps.setInt(7, id);

            return ps.executeUpdate() == 1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<User> getList() {
        List<User> users = new ArrayList<>();
        String sql = "SELECT * FROM `users`";

        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                User user = UserFactory.createUserFromResultSet(rs);
                if (user != null) {
                    users.add(user);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return users;
    }

    @Override
    public User getById(int id) {
        String sql = "SELECT * FROM `users` WHERE id = ?";
        User user = null;

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    user = UserFactory.createUserFromResultSet(rs);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user;
    }

    @Override
    public boolean remove(int id) {
        boolean isDeleted = false;
        String sql = "DELETE FROM users WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            isDeleted = stmt.executeUpdate() == 1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return isDeleted;
    }

    public User auth(String record, String password) {
        String sql = "SELECT * FROM users WHERE record = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, record);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    String storedHash = resultSet.getString("password");
                    if (BCrypt.verifyer().verify(password.toCharArray(), storedHash).verified) {
                        User user = UserFactory.createUserFromResultSet(resultSet);
                        user.setId(resultSet.getInt("id"));
                        user.setRecord(resultSet.getString("record"));
                        user.setPassword(storedHash);
                        return user;
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Database error during authentication", e);
        }
        return null;
    }

    private void insertUser(PreparedStatement ps, User user) throws SQLException {
        System.out.println(user);
        switch (user.getRole()) {
            case ADMIN:
                ps.setString(4, ((Admin) user).getAdminLevel());
                ps.setNull(5, 1);
                ps.setNull(6, 1);
                break;
            case TEACHER:
                ps.setNull(4, 1);
                ps.setString(5, ((Teacher) user).getDepartment());
                ps.setNull(6, 1);
                break;
            case STUDENT:
                ps.setNull(4, 1);
                ps.setNull(5, 1);
                ps.setInt(6, ((Student) user).getGroup());
                break;
            default:
                break;
        }
    }
}
