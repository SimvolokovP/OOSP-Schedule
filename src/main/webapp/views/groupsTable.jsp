<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="service.GroupsServiceImpl" %>
<%@ page import="models.Group" %>


<%
GroupsServiceImpl groupsService = new GroupsServiceImpl();
List<Group> groups = groupsService.getGroups();
Group editingGroup = (Group) request.getAttribute("editingGroup");
%>

<div class="table-height">
    <table>
        <thead>
            <tr>
                <th>Id</th>
                <th>Номер</th>
                <th>Действия</th>
            </tr>
        </thead>
        <tbody>
            <tr class="sticky-row">
                <form action="storeGroup" method="post">
                    <td><input disabled type="text" value="<%= editingGroup != null ? editingGroup.getId() : "" %>" required /></td>
                    <td><input type="text" name="number" value="<%= editingGroup != null ? editingGroup.getNumber() : "" %>" required /></td>
                    <td>
                        <input type="hidden" name="id" value="<%= editingGroup != null ? editingGroup.getId() : "" %>" />
                        <button type="submit" class="button"><%= editingGroup != null ? "Сохранить" : "Создать" %></button>
                        <% if (editingGroup != null) { %>
                           <a href="admin.jsp" class="button cancel">Отмена</a>
                        <% } %>
                    </td>
                </form>
            </tr>
            <% for (Group group : groups) { %>
            <tr>
                <td><%= group.getId() %></td>
                <td><%= group.getNumber() %></td>
                <td>
                    <form action="editGroup" method="get" style="display: inline">
                        <input type="hidden" name="id" value="<%= group.getId() %>" />
                        <button type="submit" class="button edit">Редактировать</button>
                    </form>
                    <form action="deleteGroup" method="post" style="display: inline">
                        <input type="hidden" name="id" value="<%= group.getId() %>" />
                        <button type="submit" class="button delete">Удалить</button>
                    </form>
                </td>
            </tr>
            <% } %>
        </tbody>
    </table>
</div>