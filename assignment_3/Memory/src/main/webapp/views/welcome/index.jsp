<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Welcome</title>
    </head>
    <body>
        <h1>Welcome, unknown friend!</h1>
        <form action="<% config.getServletContext(); %>/welcome" method="POST">
            <label for="username">Enter your name:</label>
            <input type="text" name="username" id="username" />
            <input type="submit" value="Let's go!" />
        </form>
    </body>
</html>