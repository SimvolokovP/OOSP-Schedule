<%@ page contentType="text/html;charset=UTF-8" language="java" %> <%@ taglib
uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
  <head>
    <title>Авторизация</title>
    <link rel="stylesheet" type="text/css" href="css/normalize.css" />
    <link rel="stylesheet" type="text/css" href="css/style.css" />
    <link rel="stylesheet" type="text/css" href="css/login.css" />
  </head>
  <body>
    <main>
      <div class="container auth-page__container">
        <h2 class="page-title">Авторизация</h2>
        <%@include file="authForm.jsp" %>
         <c:if test="${not empty errorMessage}">
                  <div style="color: red">${errorMessage}</div>
         </c:if>
      </div>
    </main>
  </body>
</html>
