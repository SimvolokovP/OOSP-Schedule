package controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.DayOfWeek;
import models.Lesson;
import models.LessonType;
import models.WeekType;
import service.LessonsServiceImpl;

import java.io.IOException;

@WebServlet("/storeLesson")
public class CreateLessonServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String weekDayStr = request.getParameter("weekDay");
        String weekTypeStr = request.getParameter("weekType");
        String lessonTypeStr = request.getParameter("lessonType");
        String startTime = request.getParameter("startTime");
        String endTime = request.getParameter("endTime");
        String teacher = request.getParameter("teacher");
        String audience = request.getParameter("audience");
        String groupIdStr = request.getParameter("group");
        String idStr = request.getParameter("id");

        DayOfWeek weekDay = DayOfWeek.valueOf(weekDayStr.toUpperCase());
        WeekType weekType = WeekType.valueOf(weekTypeStr.toUpperCase());
        LessonType lessonType = LessonType.valueOf(lessonTypeStr.toUpperCase());

        int groupId = Integer.parseInt(groupIdStr);

        LessonsServiceImpl lessonsService = new LessonsServiceImpl();

        if (idStr != null && !idStr.trim().isEmpty()) {
            int id = Integer.parseInt(idStr);
            Lesson existingLesson = new Lesson(id, name, audience, teacher, weekDay, startTime, endTime, lessonType, weekType, groupId);
            lessonsService.updateLesson(id, existingLesson);
        } else {
            Lesson newLesson = new Lesson(name, audience, teacher, weekDay, startTime, endTime, lessonType, weekType, groupId);
            lessonsService.createLesson(newLesson);
        }

        response.sendRedirect("admin.jsp");
    }
}