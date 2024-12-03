package controller;

import dao.LessonDaoImpl;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.DayOfWeek;
import models.Lesson;
import models.WeekType;
import service.LessonsService;
import service.LessonsServiceImpl;
import utils.DBConnector;

import java.io.IOException;
import java.util.List;

@WebServlet("/searchLessons")
public class SearchLessonsServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String dayOfWeek = request.getParameter("dayOfWeek");
        String weekType = request.getParameter("weekType");
        int group = Integer.parseInt(request.getParameter("group"));

        LessonsServiceImpl lessonsService = new LessonsServiceImpl();
        List<Lesson> lessons = lessonsService.getLessonsByParams(DayOfWeek.valueOf(dayOfWeek), WeekType.valueOf(weekType), group);

        request.setAttribute("lessons", lessons);

        RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
        dispatcher.forward(request, response);
    }
}