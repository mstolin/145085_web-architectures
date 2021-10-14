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
    <jsp:include page="Banner.jsp" />
    <h1>Admin Page</h1>
    <div>
        <h2>Add new user</h2>
        <form action="<% request.getContextPath(); %>/admin" method="post">
            <input type="text" name="username" />
            <input type="password" name="password" />
            <input type="submit" />
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
