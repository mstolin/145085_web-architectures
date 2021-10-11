<%
String roomName = (String)request.getAttribute("REQUESTED_ROOM");
%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Room - <%= roomName %></title>
</head>
<body>
    <h1><%= roomName %></h1>
    <form action="<% request.getContextPath(); %>/room/<%= roomName %>" method="post">
        <input type="text" name="message" />
        <input type="submit" />
    </form>
</body>
</html>
