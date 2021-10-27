<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Welcome</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
        <style>
            body {
                padding: 2% 4%;
            }
        </style>
    </head>
    <body>
        <div class="w-100">
            <h1>Welcome, unknown friend!</h1>
            <form action="<% config.getServletContext(); %>/welcome" method="POST">
                <label for="username">Enter your name:</label>
                <input type="text" name="username" id="username" />
                <input type="submit" value="Let's go!" />
            </form>
        </div>
    </body>
</html>