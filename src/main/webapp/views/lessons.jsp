<%@ page contentType="text/html;charset=UTF-8" language="java" %> <%@ taglib
uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="lessons">
  <c:if test="${empty lessons}">
    <c:if test="${empty param.dayOfWeek}">
      <div class="lessons__empty">
        <p>Давайте узнаем ваше расписание..</p>
      </div>
    </c:if>
    <c:if test="${not empty param.dayOfWeek}">
      <div class="lessons__empty">
        <p>В этот день нет занятий.</p>
      </div>
    </c:if>
  </c:if>

  <c:if test="${not empty lessons}">
    <ul class="lessons__list">
      <c:forEach var="lesson" items="${lessons}">
        <li class="lessons__list--item lessons__item">
          <span>${lesson.startTime} - ${lesson.endTime}</span>
          <h3>${lesson.name}</h3>
          <div>${lesson.lessonType}</div>
          <div class="lessons__item--stats">
            <div>${lesson.teacher}</div>
            <div>${lesson.audience}</div>
          </div>
        </li>
      </c:forEach>
    </ul>
  </c:if>
</div>
