<%@ page import="java.util.List" %>
<%@ page import="it.unitn.disi.webarch.chat.models.room.Message" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="activeRoom" class="it.unitn.disi.webarch.chat.models.room.Room" scope="request" />
<html>
<head>
    <title>Room - <jsp:getProperty name="activeRoom" property="name"/></title>
    <meta http-equiv="refresh" content="15">
</head>
<body>
    <jsp:include page="../Banner.jsp" />
    <div>
        <h1><jsp:getProperty name="activeRoom" property="name"/></h1>
        <p>
            <a href="<% request.getContextPath(); %>/room/<jsp:getProperty name="activeRoom" property="name"/>">Reload this room</a>
             or
            <a href="<% request.getContextPath(); %>/user">Leave room</a>
        </p>
        <form action="<% request.getContextPath(); %>/room/<jsp:getProperty name="activeRoom" property="name"/>" method="post">
            <label for="message">Message:</label>
            <input type="text" id="message" name="message" />
            <input type="submit" value="Send Message" />
        </form>
        <div>
            <%
                List<Message> messages = activeRoom.getMessages();
                for(Message message: messages) {
            %>
                <div>
                    <p><em><%= message.getUser() %> at <%= message.getFormattedDate() %>:</em></p>
                    <p><%= message.getMessage() %></p>
                </div>
            <% } %>
        </div>
    </div>
</body>
</html>
