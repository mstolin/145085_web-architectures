<%@ page import="it.unitn.disi.webarch.memory.models.Scoreboard" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="SESSION_KEY_USER" class="it.unitn.disi.webarch.memory.models.User" scope="session"/>
<jsp:useBean id="scoreboard" class="it.unitn.disi.webarch.memory.models.Scoreboard" scope="application"/>
<html>
    <head>
        <title>Ranking</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
        <style>
            body {
                padding: 2% 4%;
            }
        </style>
    </head>
    <body>
        <div class="w-100">
            <h1>Welcome <strong><jsp:getProperty name="SESSION_KEY_USER" property="name"/></strong></h1>
            <div>
                <h2>Ranking</h2>
                <c:choose>
                    <c:when test="${scoreboard.isEmpty()}">
                        <p>Empty - No game played yet</p>
                    </c:when>
                    <c:otherwise>
                        <ul>
                        <c:forEach items="${scoreboard.getScores()}" var="score">
                            <li>${score.getKey().getName()} - ${score.getValue()}</li>
                        </c:forEach>
                        </ul>
                    </c:otherwise>
                </c:choose>
            </div>
            <div>
                <!--<button>Play Game</button>-->
                <a href="<% config.getServletContext(); %>/play">Play Game</a>
            </div>
        </div>
    </body>
</html>
