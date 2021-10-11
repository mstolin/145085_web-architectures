<%--
  Created by IntelliJ IDEA.
  User: marcel
  Date: 11.10.21
  Time: 15:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Create a Room</title>
</head>
<body>
    <form action="<% request.getContextPath(); %>/room/create" method="post">
        <input type="text" name="name" />
        <input type="submit" />
    </form>
</body>
</html>
