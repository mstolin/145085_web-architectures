<jsp:useBean id="activeUser" class="it.unitn.disi.webarch.chat.models.user.User" scope="session"/>
<div>
    <p>Your are logged in as <strong><jsp:getProperty name="user" property="name"/></strong> - <a href="<% request.getContextPath(); %>/auth/logout">Logout</a></p>
</div>
