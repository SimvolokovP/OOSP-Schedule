package controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import models.User;
import models.UserRole;
import service.UsersServiceImpl;

import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private UsersServiceImpl usersService = new UsersServiceImpl();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String record = request.getParameter("record");
        String password = request.getParameter("password");

        User user = usersService.login(record, password);
        if (user != null) {
            HttpSession session = request.getSession();
            session.setAttribute("user", user);

            response.sendRedirect("index.jsp");
        } else {
            request.setAttribute("errorMessage", "Неверные учетные данные.");
            RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
            dispatcher.forward(request, response);
        }
    }
}