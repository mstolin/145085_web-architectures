<%@ page import="java.util.Set" %>
<%@ page import="it.unitn.disi.webarch.chat.models.user.User" %>
<%@ page import="it.unitn.disi.webarch.chat.helper.UserStore" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    Set<User> users = UserStore.getInstance().getAll();
%>
<html>
<head>
    <title>Admin Page</title>
</head>
<body>
    <jsp:include page="../Banner.jsp" />
    <h1>Admin Page</h1>
    <div>
        <h2>Add new user</h2>
        <form action="<% request.getContextPath(); %>/admin" method="post">
            <label for="username">Username:</label><br />
            <input type="text" id="username" name="username" /><br />
            <label for="password">Password:</label><br />
            <input type="password" id="password" name="password" /><br />
            <input type="submit" value="Create user" />
        </form>
    </div>
    <div>
        <h2>All users</h2>
        <ul>
            <% for(User user: users) { %>
            <li><%= user.getName() %></li>
            <% } %>
        </ul>
    </div>
</body>
</html>
