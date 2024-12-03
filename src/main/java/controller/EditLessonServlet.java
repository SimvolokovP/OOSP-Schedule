package controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.Lesson;
import service.LessonsServiceImpl;

import java.io.IOException;
@WebServlet("/editLesson")
public class EditLessonServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int lessonId = Integer.parseInt(request.getParameter("id"));
        LessonsServiceImpl lessonsService = new LessonsServiceImpl();
        Lesson lesson = lessonsService.getLessonById(lessonId);
        request.setAttribute("editingLesson", lesson);
        RequestDispatcher dispatcher = request.getRequestDispatcher("admin.jsp");
        dispatcher.forward(request, response);
    }
}
