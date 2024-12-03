package controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.Group;
import models.Lesson;
import service.GroupsServiceImpl;
import service.LessonsServiceImpl;

import java.io.IOException;

@WebServlet("/editGroup")
public class EditGroupServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int lessonId = Integer.parseInt(request.getParameter("id"));
        GroupsServiceImpl groupsService = new GroupsServiceImpl();
        Group group = groupsService.getGroupById(lessonId);
        request.setAttribute("editingGroup", group);
        RequestDispatcher dispatcher = request.getRequestDispatcher("admin.jsp");
        dispatcher.forward(request, response);
    }
}