<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="service.UsersServiceImpl" %>
<%@ page import="models.User" %>
<%@ page import="models.Admin" %>
<%@ page import="models.Teacher" %>
<%@ page import="models.Student" %>

<%
UsersServiceImpl usersService = new UsersServiceImpl();
List<User> users = usersService.getUsers();
User editingUser = (User) request.getAttribute("editingUser");
%>

<div class="table-height">
    <table>
        <thead>
            <tr>
                <th>Логин</th>
                <th>Пароль</th>
                <th>Роль</th>
                <th>Характеристика</th>
                <th>Действия</th>
            </tr>
        </thead>
        <tbody>
            <tr class="sticky-row">
                <form action="storeUser" method="post">
                    <td>
                        <input type="text" name="record" value="<%= editingUser != null ? editingUser.getRecord() : "" %>" required />
                    </td>
                    <td>
                        <input type="password" name="password" value="<%= editingUser != null ? editingUser.getPassword() : "" %>" required />
                    </td>
                    <td>
                        <input type="hidden" name="id" value="<%= editingUser != null ? editingUser.getId() : "" %>" />
                        <select name="role">
                            <option value="ADMIN" <%= editingUser != null && editingUser.getRole().name().equals("ADMIN") ? "selected" : "" %>>Админ</option>
                            <option value="TEACHER" <%= editingUser != null && editingUser.getRole().name().equals("TEACHER") ? "selected" : "" %>>Преподаватель</option>
                            <option value="STUDENT" <%= editingUser != null && editingUser.getRole().name().equals("STUDENT") ? "selected" : "" %>>Студент</option>
                        </select>

                    </td>
                    <td>
                        <input name="descr" type="text" required />
                    </td>
                    <td>
                        <input type="hidden" name="id" value="<%= editingUser != null ? editingUser.getId() : "" %>" />
                        <button type="submit" class="button"><%= editingUser != null ? "Сохранить" : "Создать" %></button>
                        <% if (editingUser != null) { %>
                           <a href="admin.jsp" class="button cancel">Отмена</a>
                        <% } %>
                    </td>
                </form>
            </tr>
            <% for (User currentUser : users) { %>
            <tr>
                <td><%= currentUser.getRecord() %></td>
                <td>password</td>
                <td><%= currentUser.getRole() %></td>

                <td>
                    <%
                    if (currentUser instanceof Admin) {
                        Admin admin = (Admin) currentUser;
                        out.print("Уровень: " + admin.getAdminLevel());
                    } else if (currentUser instanceof Teacher) {
                        Teacher teacher = (Teacher) currentUser;
                        out.print("Отдел: " + teacher.getDepartment());
                    } else if (currentUser instanceof Student) {
                        Student student = (Student) currentUser;
                        out.print("Группа: " + student.getGroup());
                    } else {
                        out.print("Нет характеристик");
                    }
                    %>
                </td>
                <td>
                    <form action="editUser" method="get" style="display: inline">
                        <input type="hidden" name="id" value="<%= currentUser.getId() %>" />
                        <button type="submit" class="button edit">Редактировать</button>
                    </form>
                    <form action="deleteUser" method="post" style="display: inline">
                        <input type="hidden" name="id" value="<%= currentUser.getId() %>" />
                        <button type="submit" class="button delete">Удалить</button>
                    </form>
                </td>
            </tr>
            <% } %>
        </tbody>
    </table>
</div>