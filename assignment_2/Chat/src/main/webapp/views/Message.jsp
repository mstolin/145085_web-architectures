<%@ page import="it.unitn.disi.webarch.chat.models.room.Message" %>
<%
    Message message = (Message)request.getAttribute("message");
%>

<div>
    <p><strong><%= message.getUser() %></strong> at <em><%= message.getTimestamp() %></em></p>
    <p><%= message.getMessage() %></p>
</div>
