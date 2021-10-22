<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Welcome</title>
    </head>
    <body>
        <h1>Welcome</h1>
        <form action="<% config.getServletContext(); %>/welcome" method="POST">
            <label for="username">Username:</label>
            <input type="text" name="username" id="username" />
        </form>
    </body>
</html>