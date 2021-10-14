<jsp:useBean id="activeUser" class="it.unitn.disi.webarch.chat.models.user.User" scope="session"/>
<div>
    <p>Your are logged in as
        <strong><jsp:getProperty name="activeUser" property="name"/></strong>
        <%
            boolean isAdmin = activeUser.getName().equals("admin");
            if (isAdmin) {
        %>
         (<a href="<% request.getContextPath(); %>/admin">Go to admin page</a>)
        <% } %>
        - <a href="<% request.getContextPath(); %>/auth/logout">Logout</a>
    </p>
</div>
