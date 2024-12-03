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

@WebServlet("/deleteLesson")
public class DeleteLessonServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int lessonId = Integer.parseInt(request.getParameter("id"));
        LessonsServiceImpl lessonsService = new LessonsServiceImpl();
        lessonsService.deleteLesson(lessonId);
        response.sendRedirect("index.jsp");
    }
}