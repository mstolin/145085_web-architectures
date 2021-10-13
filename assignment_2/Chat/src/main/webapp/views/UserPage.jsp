<%@ page import="java.util.Set" %>
<%@ page import="it.unitn.disi.webarch.chat.helper.RoomStore" %>
<%@ page import="it.unitn.disi.webarch.chat.models.room.Room" %>
<%
    Set<Room> rooms = RoomStore.getInstance().getAll();
%>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <jsp:include page="Banner.jsp" />
    <div>
        <h1>Available Rooms</h1>

        <div>
            <a href="<% request.getContextPath(); %>/room/create">Create a new room</a>
        </div>

        <div>
            <ul>
                <% for(Room room: rooms) { %>
                <li><a href="<% request.getContextPath(); %>/room/<%= room.getName() %>"><%= room.getName() %></a></li>
                <% } %>
            </ul>
        </div>
    </div>

</body>
</html>
