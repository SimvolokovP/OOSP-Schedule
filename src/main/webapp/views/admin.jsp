<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="models.User" %>
<%@ page import="models.UserRole" %>
<%
    User user = (User) session.getAttribute("user");
    boolean isAdminOrTeacher = (user != null) && (user.getRole() == UserRole.ADMIN || user.getRole() == UserRole.TEACHER);
    boolean isAdmin = (user != null) && (user.getRole() == UserRole.ADMIN);
    if (!isAdminOrTeacher) {
    response.sendRedirect("login.jsp");
    return;
    }
%>
  <html lang="ru">
    <head>
      <meta charset="UTF-8" />
      <meta name="viewport" content="width=device-width, initial-scale=1.0" />
      <title>Админ Панель</title>
      <link rel="stylesheet" type="text/css" href="css/normalize.css" />
    <link rel="stylesheet" type="text/css" href="css/style.css" />
    <link rel="stylesheet" type="text/css" href="css/admin.css" />
    </head>
    <body>
      <main>
        <div class="container admin-container">
        <a class="toAdmin" href="index.jsp">Назад</a>
            <h2>Список Уроков</h2>


      <%@include file="lessonsTable.jsp" %>

      <h2>Список Групп</h2>

      <%
        if (isAdmin) {
      %>
        <%@ include file="groupsTable.jsp" %>
      <%
      } else {
      out.println("<p>У вас нет прав для доступа к таблице групп.</p>");
      }
      %>

      <h2>Список Пользователей</h2>

      <%
              if (isAdmin) {
            %>
              <%@ include file="usersTable.jsp" %>
            <%
            } else {
            out.println("<p>У вас нет прав для доступа к таблице пользователй.</p>");
            }
            %>
        </div>
      </main>
    </body>
  </html></Lesson
>
