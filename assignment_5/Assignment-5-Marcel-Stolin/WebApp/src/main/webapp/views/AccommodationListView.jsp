<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="accommodationListModel"
             class="it.unitn.disi.webarch.mstolin.webapp.models.AccommodationListModel"
             scope="session"/>
<html>
<head>
    <title>Accommodations</title>
</head>
<body>

<form method="POST" action="<% config.getServletContext(); %>">
    <label>Start Date</label>
    <input type="date" id="startDate" name="startDate">
    <label>End Date</label>
    <input type="date" id="endDate" name="endDate">
    <label>Max. Persons</label>
    <input id="numberPersons" type="number" name="numberPersons" value="0" min="0">
    <input type="submit" title="Search" />
</form>

<h1>Available Accommodations</h1>
<ul>
    <c:forEach items="${accommodationListModel.getAllAccommodations()}" var="accommodation">
        <li>
            <a href="<% config.getServletContext(); %>/accommodation/${accommodation.getId()}">${accommodation.getName()}</a>
        </li>
    </c:forEach>
</ul>
</body>
</html>
