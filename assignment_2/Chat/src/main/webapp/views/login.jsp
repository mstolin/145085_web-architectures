<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Login</title>
    </head>
    <body>
        <form action="<% request.getContextPath(); %>/auth/login" method="POST">

            <label for="username">Username:</label>
            <input type="text" id="username" name="username" />

            <label for="password">Password:</label>
            <input type="password" id="password" name="password" />

            <input type="submit" value="Login" />
        </form>
    </body>
</html>