package dao;

import models.DayOfWeek;
import models.Lesson;
import models.LessonType;
import models.WeekType;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LessonDaoImpl implements Dao<Lesson>{

    private Connection connection;

    public LessonDaoImpl(Connection connection) {
        this.connection = connection;
    }
    @Override
    public boolean add(Lesson lesson) {
        String sqlQuery = "INSERT INTO lessons(name, audience, teacher, day_of_week, start_time, end_time, lesson_type, week_type, `group`) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(sqlQuery)) {
            ps.setString(1, lesson.getName());
            ps.setString(2, lesson.getAudience());
            ps.setString(3, lesson.getTeacher());
            ps.setString(4, lesson.getDayOfWeek().name());
            ps.setString(5, lesson.getStartTime());
            ps.setString(6, lesson.getEndTime());
            ps.setString(7, lesson.getLessonType().name());
            ps.setString(8, lesson.getWeekType().name());
            ps.setInt(9, lesson.getGroup());
            return ps.executeUpdate() == 1;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean edit(int id, Lesson lesson) {
        boolean isEdit = false;
        String sql = "UPDATE lessons SET name = ?, audience = ?, teacher = ?, day_of_week = ?, start_time = ?, end_time = ?, lesson_type = ?, week_type = ?, `group` = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, lesson.getName());
            stmt.setString(2, lesson.getAudience());
            stmt.setString(3, lesson.getTeacher());
            stmt.setString(4, lesson.getDayOfWeek().name());
            stmt.setString(5, lesson.getStartTime());
            stmt.setString(6, lesson.getEndTime());
            stmt.setString(7, lesson.getLessonType().name());
            stmt.setString(8, lesson.getWeekType().name());
            stmt.setInt(9, lesson.getGroup());
            stmt.setInt(10, id);
            stmt.executeUpdate();
            isEdit = true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return isEdit;
    }

    @Override
    public List<Lesson> getList() {
        List<Lesson> lessons = new ArrayList<>();
        String sql = "SELECT * FROM lessons ORDER BY start_time ASC";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Lesson lesson = new Lesson(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("audience"),
                        rs.getString("teacher"),
                        DayOfWeek.valueOf(rs.getString("day_of_week")),
                        rs.getString("start_time"),
                        rs.getString("end_time"),
                        LessonType.valueOf(rs.getString("lesson_type")),
                        WeekType.valueOf(rs.getString("week_type")),
                        rs.getInt("group")
                );
                lessons.add(lesson);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lessons;
    }

    public List<Lesson> getLessonsListByParams(DayOfWeek dayOfWeek, WeekType weekType, int group) {
        List<Lesson> lessons = new ArrayList<>();
        String sql = "SELECT * FROM lessons WHERE day_of_week = ? AND `group` = ? " +
                "AND (week_type = ? OR week_type = 'EVERY_WEEK') " +
                "ORDER BY start_time ASC";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, dayOfWeek.name());
            stmt.setInt(2, group);
            stmt.setString(3, weekType.name());

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Lesson lesson = new Lesson(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("audience"),
                        rs.getString("teacher"),
                        DayOfWeek.valueOf(rs.getString("day_of_week")),
                        rs.getString("start_time"),
                        rs.getString("end_time"),
                        LessonType.valueOf(rs.getString("lesson_type")),
                        WeekType.valueOf(rs.getString("week_type")),
                        rs.getInt("group")
                );
                lessons.add(lesson);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lessons;
    }


    @Override
    public Lesson getById(int id) {
        String sql = "SELECT * FROM lessons WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Lesson(rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("audience"),
                        rs.getString("teacher"),
                        DayOfWeek.valueOf(rs.getString("day_of_week")),
                        rs.getString("start_time"),
                        rs.getString("end_time"),
                        LessonType.valueOf(rs.getString("lesson_type")),
                        WeekType.valueOf(rs.getString("week_type")),
                        rs.getInt("group"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean remove(int id) {
        boolean isDeleted = false;
        String sql = "DELETE FROM lessons WHERE id = ?";
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
