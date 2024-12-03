<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="service.LessonsServiceImpl" %>
<%@ page import="models.Lesson" %>
<%@ page import="models.DayOfWeek" %>
<%@ page import="models.WeekType" %>
<%@ page import="models.LessonType" %>
<%@ page import="service.GroupsServiceImpl" %>
<%@ page import="models.Group" %> <%@ page import="java.util.List" %>


<%
LessonsServiceImpl lessonsService = new LessonsServiceImpl();
List<Lesson> lessons = lessonsService.getLessons();
Lesson editingLesson = (Lesson) request.getAttribute("editingLesson");
GroupsServiceImpl groupService = new GroupsServiceImpl();
List<Group> groupList = groupService.getGroups();

%>

<div class="table-height">
    <table>
        <thead>
            <tr>
                <th>Название</th>
                <th>День недели</th>
                <th>Тип недели</th>
                <th>Тип пары</th>
                <th style="width: 85px;">Время начала</th>
                <th style="width: 85px;">Время окончания</th>
                <th>Учитель</th>
                <th style="width: 70px;">Аудитория</th>
                <th style="width: 70px;">Группа</th>
                <th>Действия</th>
            </tr>
        </thead>
        <tbody>
            <tr class="sticky-row">
                <form action="storeLesson" method="post">
                    <td><input type="text" name="name" value="<%= editingLesson != null ? editingLesson.getName() : "" %>" required /></td>
                    <td>
                    <select name="weekDay" required>
                       <% for (DayOfWeek day : DayOfWeek.values()) { %>
                          <option value="<%= day.name() %>" <%= (editingLesson != null && editingLesson.getDayOfWeek() == day) ? "selected" : "" %>><%= day %></option>
                       <% } %>
                    </select>
                    </td>
                    <td>
                        <select name="weekType" required>
                            <% for (WeekType type : WeekType.values()) { %>
                               <option value="<%= type.name() %>" <%= (editingLesson != null && editingLesson.getWeekType() == type) ? "selected" : "" %>><%= type %></option>
                            <% } %>
                         </select>
                    </td>
                    <td>
                        <select name="lessonType" required>
                            <% for (LessonType type : LessonType.values()) { %>
                               <option value="<%= type.name() %>" <%= (editingLesson != null && editingLesson.getLessonType() == type) ? "selected" : "" %>><%= type %></option>
                            <% } %>
                         </select>
                    </td>
                    <td><input type="time" name="startTime" value="<%= editingLesson != null ? editingLesson.getStartTime() : "" %>" required /></td>
                    <td><input type="time" name="endTime" value="<%= editingLesson != null ? editingLesson.getEndTime() : "" %>" required /></td>
                    <td><input type="text" name="teacher" value="<%= editingLesson != null ? editingLesson.getTeacher() : "" %>" required /></td>
                    <td><input type="text" name="audience" value="<%= editingLesson != null ? editingLesson.getAudience() : "" %>" required /></td>
                    <td>
                        <select id="group" name="group" required>
                                <% for (Group group : groupList) { %>
                                <option value="<%= group.getId() %>" <%= (editingLesson != null && editingLesson.getGroup() == group.getId()) ? "selected" : "" %> ><%= group.getNumber() %></option>
                                <% } %>
                        </select>
                    </td>
                    <td>
                        <input type="hidden" name="id" value="<%= editingLesson != null ? editingLesson.getId() : "" %>" />
                        <button type="submit" class="button"><%= editingLesson != null ? "Сохранить" : "Создать" %></button>
                        <% if (editingLesson != null) { %>
                           <a href="admin.jsp" class="button cancel">Отмена</a>
                        <% } %>
                    </td>
                </form>
            </tr>
            <% for (Lesson lesson : lessons) { %>
            <tr>
                <td><%= lesson.getName() %></td>
                <td><%= lesson.getDayOfWeek() %></td>
                <td><%= lesson.getWeekType() %></td>
                <td><%= lesson.getLessonType() %></td>
                <td><%= lesson.getStartTime() %></td>
                <td><%= lesson.getEndTime() %></td>
                <td><%= lesson.getTeacher() %></td>
                <td><%= lesson.getAudience() %></td>
                <td><%= lesson.getGroup() %></td>
                <td>
                    <form action="editLesson" method="get" style="display: inline">
                        <input type="hidden" name="id" value="<%= lesson.getId() %>" />
                        <button type="submit" class="button edit">Редактировать</button>
                    </form>
                    <form action="deleteLesson" method="post" style="display: inline">
                        <input type="hidden" name="id" value="<%= lesson.getId() %>" />
                        <button type="submit" class="button delete">Удалить</button>
                    </form>
                </td>
            </tr>
            <% } %>
        </tbody>
    </table>
</div>