<%
    String username = session.getAttribute("username").toString();
%>
<div>
    <p>Your are logged in as <strong><%= username %></strong> - <a href="<% request.getContextPath(); %>/auth/logout">Logout</a></p>
</div>
