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
    <c:choose>
        <c:when test="${searchResult.getAccommodations().size() > 0}">
            <ul>
                <c:forEach items="${searchResult.getAccommodations()}" var="accommodation">
                    <li>
                        <c:choose>
                            <c:when test="${accommodation.getClass().name == 'it.unitn.disi.webarch.mstolin.dao.accommodation.ApartmentEntity'}">
                                <jsp:include page="ApartmentDetailView.jsp" >
                                    <jsp:param name="id" value="${accommodation.getId()}"/>
                                    <jsp:param name="name" value="${accommodation.getName()}"/>
                                    <jsp:param name="maxPersons" value="${accommodation.getMaxPersons()}"/>
                                    <jsp:param name="price" value="${accommodation.getPrice()}"/>
                                </jsp:include>
                            </c:when>
                            <c:when test="${accommodation.getClass().name == 'it.unitn.disi.webarch.mstolin.dao.accommodation.HotelEntity'}">
                                <jsp:include page="HotelDetailView.jsp" >
                                    <jsp:param name="id" value="${accommodation.getId()}"/>
                                    <jsp:param name="name" value="${accommodation.getName()}"/>
                                    <jsp:param name="stars" value="${accommodation.getStars()}"/>
                                    <jsp:param name="price" value="${accommodation.getPrice()}"/>
                                </jsp:include>
                            </c:when>
                        </c:choose>
                    </li>
                </c:forEach>
            </ul>
        </c:when>
        <c:otherwise>
            <p>Sorry, no accommodation available</p>
        </c:otherwise>
    </c:choose>
</body>
</html>
