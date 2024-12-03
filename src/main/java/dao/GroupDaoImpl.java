package dao;

import models.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GroupDaoImpl implements Dao<Group> {
    private Connection connection;

    public GroupDaoImpl(Connection connection) {
        this.connection = connection;
    }
    @Override
    public boolean add(Group group) {
        String sqlQuery = "INSERT INTO `groups`(`number`) VALUES (?)";
        try (PreparedStatement ps = connection.prepareStatement(sqlQuery)) {
            ps.setString(1, group.getNumber());
            return ps.executeUpdate() == 1;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean edit(int id, Group group) {
        boolean isEdit = false;
        String sql = "UPDATE `groups` SET number = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, group.getNumber());
            stmt.setInt(2, id);
            stmt.executeUpdate();
            isEdit = true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return isEdit;
    }

    @Override
    public List<Group> getList() {
        List<Group> groups = new ArrayList<>();
        String sql = "SELECT * FROM `groups`";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Group group = new Group(
                        rs.getInt("id"),
                        rs.getString("number")
                );
                groups.add(group);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return groups;
    }

    @Override
    public Group getById(int id) {
        String sql = "SELECT * FROM `groups` WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Group(rs.getInt("id"), rs.getString("number"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean remove(int id) {
        boolean isDeleted = false;
        String sql = "DELETE FROM `groups` WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
            isDeleted = true;
        } catch (SQLException e) {
            e.printStackTrace();

        }
        return isDeleted;
    }
}
