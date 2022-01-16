<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="accommodationListModel"
             class="it.unitn.disi.webarch.mstolin.webapp.models.AccommodationListModel"
             scope="session"/>
<jsp:useBean id="searchResult"
             class="it.unitn.disi.webarch.mstolin.webapp.models.accommodation.AccommodationSearchResult"
             scope="request"/>
<html>
<head>
    <title>Accommodations</title>
</head>
<body>
<h1>Available Accommodations</h1>
<ul>
    <c:forEach items="${searchResult.getAccommodations()}" var="accommodation">
        <li>
            <a href="<% config.getServletContext(); %>/accommodation/${accommodation.getId()}">${accommodation.getName()}</a>
        </li>
    </c:forEach>
</ul>
</body>
</html>
