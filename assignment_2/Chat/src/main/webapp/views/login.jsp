<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Login</title>
    </head>
    <body>
        <form action="<% request.getContextPath(); %>/auth/login" method="POST">

            <label for="username">Username:</label><br />
            <input type="text" id="username" name="username" /><br />

            <label for="password">Password:</label><br />
            <input type="password" id="password" name="password" /><br />

            <input type="submit" value="Login" />
        </form>
    </body>
</html>