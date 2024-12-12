package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.Admin;
import models.Student;
import models.Teacher;
import models.User;
import service.UsersServiceImpl;

import java.io.IOException;

@WebServlet("/storeUser")
public class CreateUserServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String record = request.getParameter("record");
        String role = request.getParameter("role");
        String password = request.getParameter("password");
        String idStr = request.getParameter("id");

        String descr = request.getParameter("descr");

        UsersServiceImpl usersService = new UsersServiceImpl();

        if (idStr != null && !idStr.trim().isEmpty()) {

            int id = Integer.parseInt(idStr);
            User existingUser = usersService.getUserById(id);
            if (existingUser != null) {
                existingUser.setRecord(record);

                if (existingUser instanceof Admin) {
                    Admin admin = (Admin) existingUser;
                    admin.setAdminLevel(descr);
                } else if (existingUser instanceof Teacher) {
                    Teacher teacher = (Teacher) existingUser;
                    teacher.setDepartment(descr);
                } else if (existingUser instanceof Student) {
                    Student student = (Student) existingUser;
                    student.setGroup(Integer.parseInt(descr));
                }
                usersService.updateUser(id, existingUser);
            }
        } else {
            User newUser;
            switch (role) {
                case "ADMIN":
                    newUser = new Admin(record, password, descr);
                    break;
                case "TEACHER":
                    newUser = new Teacher(record, password, descr);
                    break;
                case "STUDENT":
                    newUser = new Student(record, password, Integer.parseInt(descr));
                    break;
                default:
                    response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid role.");
                    return;
            }
            usersService.createUser(newUser);
        }
        response.sendRedirect("admin.jsp");
    }
}