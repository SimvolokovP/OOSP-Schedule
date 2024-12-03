<%@ page contentType="text/html;charset=UTF-8" language="java" %> <%@ taglib
uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> <%@ page
import="service.GroupsServiceImpl" %> <%@ page import="models.Group" %> <%@ page
import="java.util.List" %> <% GroupsServiceImpl groupsService = new
GroupsServiceImpl(); List<Group>
  groups = groupsService.getGroups(); %>

  <form class="settings" action="searchLessons" method="post">
    <label class="settings__label" for="dayOfWeek">
      <span>День недели</span>
      <select id="dayOfWeek" name="dayOfWeek" required>
        <option value="MONDAY">Понедельник</option>
        <option value="TUESDAY">Вторник</option>
        <option value="WEDNESDAY">Среда</option>
        <option value="THURSDAY">Четверг</option>
        <option value="FRIDAY">Пятница</option>
        <option value="SATURDAY">Суббота</option>
      </select>
    </label>

    <label class="settings__label" for="weekType">
      <span>Тип недели:</span>
      <select id="weekType" name="weekType" required>
        <option value="NUMERATOR">Числитель</option>
        <option value="DENOMINATOR">Знаменатель</option>
      </select>
    </label>

    <label class="settings__label" for="group">
      <span>Группа:</span>
      <select id="group" name="group" required>
        <% for (Group group : groups) { %>
        <option value="<%= group.getId() %>"><%= group.getNumber() %></option>
        <% } %>
      </select>
    </label>

    <input class="settings__submit" type="submit" value="Поиск" /></form
></Group>
