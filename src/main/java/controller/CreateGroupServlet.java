package controller;

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

@WebServlet("/storeGroup")
public class CreateGroupServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String number = request.getParameter("number");
        String idStr = request.getParameter("id");

        GroupsServiceImpl groupsService = new GroupsServiceImpl();

        if (idStr != null && !idStr.trim().isEmpty()) {
            int id = Integer.parseInt(idStr);
            Group existingGroup = new Group(id, number);
            groupsService.updateGroup(id, existingGroup);
        } else {
            Group newGroup = new Group(number);
            groupsService.createGroup(newGroup);
        }

        response.sendRedirect("admin.jsp");
    }
}