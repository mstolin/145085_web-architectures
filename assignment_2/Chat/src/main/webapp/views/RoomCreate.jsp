
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Create a Room</title>
</head>
<body>
    <jsp:include page="Banner.jsp" />
    <div>
        <form action="<% request.getContextPath(); %>/room/create" method="post">
            <input type="text" name="name" />
            <input type="submit" />
        </form>
    </div>
</body>
</html>
