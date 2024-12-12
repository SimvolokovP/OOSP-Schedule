<%@ page contentType="text/html;charset=UTF-8" language="java" %> <%@ taglib
uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="models.User" %>
<%@ page import="models.UserRole" %>

<% User user = (User) session.getAttribute("user");
    if (user == null) {
        response.sendRedirect("login.jsp");
        return;
       }
%>

<html>
  <head>
    <title>Расписание</title>
    <link rel="stylesheet" type="text/css" href="css/normalize.css" />
    <link rel="stylesheet" type="text/css" href="css/style.css" />
  </head>
  <body>
    <main>
      <div class="container index-page__container">
        <h2 class="page-title">Расписание занятий</h2>
        <div class="index-page__descr">
          <div>Ваш номер: <%= user.getRecord() %> (<%= user.getRole().name() %>)</div>
          <% if (user.getRole() == UserRole.ADMIN || user.getRole() ==
          UserRole.TEACHER) { %>
          <a class="toAdmin" href="admin.jsp">админ</a>
          <% } %>
        </div>

        <%@ include file="settingsForm.jsp" %> <%@ include file="lessons.jsp" %>
      </div>
    </main>
  </body>
</html>
