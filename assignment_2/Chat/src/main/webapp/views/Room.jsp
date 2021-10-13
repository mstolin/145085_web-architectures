<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="activeRoom" class="it.unitn.disi.webarch.chat.models.room.Room" scope="request" />
<html>
<head>
    <title>Room - <jsp:getProperty name="activeRoom" property="name"/></title>
</head>
<body>
    <jsp:include page="Banner.jsp" />
    <div>
        <h1><jsp:getProperty name="activeRoom" property="name"/></h1>

        <div>

        </div>

        <form action="<% request.getContextPath(); %>/room/<jsp:getProperty name="activeRoom" property="name"/>" method="post">
            <input type="text" name="message" />
            <input type="submit" />
        </form>
    </div>
</body>
</html>
