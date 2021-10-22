<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="SESSION_KEY_USER" class="it.unitn.disi.webarch.memory.models.User" scope="session"/>
<html>
    <head>
        <title>Memory Game</title>
    </head>
    <body>
        <h1><strong><jsp:getProperty name="SESSION_KEY_USER" property="name"/></strong></h1>
    </body>
</html>
