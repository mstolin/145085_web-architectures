<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
        <c:when test="${searchResult.getResults().size() > 0}">
            <ul>
                <c:forEach items="${searchResult.getResults()}" var="result">
                    <li>
                        <c:choose>
                            <c:when test="${result.accommodation.getClass().name == 'it.unitn.disi.webarch.mstolin.dao.accommodation.ApartmentEntity'}">
                                <jsp:include page="ApartmentDetailView.jsp" >
                                    <jsp:param name="id" value="${result.accommodation.getId()}"/>
                                    <jsp:param name="name" value="${result.accommodation.getName()}"/>
                                    <jsp:param name="maxPersons" value="${result.accommodation.getMaxPersons()}"/>
                                    <jsp:param name="price" value="${result.accommodation.getPrice()}"/>
                                    <jsp:param name="totalPrice" value="${result.getTotalPrice()}"/>
                                </jsp:include>
                            </c:when>
                            <c:when test="${result.accommodation.getClass().name == 'it.unitn.disi.webarch.mstolin.dao.accommodation.HotelEntity'}">
                                <jsp:include page="HotelDetailView.jsp" >
                                    <jsp:param name="id" value="${result.accommodation.getId()}"/>
                                    <jsp:param name="name" value="${result.accommodation.getName()}"/>
                                    <jsp:param name="stars" value="${result.accommodation.getStars()}"/>
                                    <jsp:param name="price" value="${result.accommodation.getPrice()}"/>
                                    <jsp:param name="halfBoardPrice" value="${result.accommodation.getExtraHalfBoard()}"/>
                                    <jsp:param name="totalPrice" value="${result.getTotalPrice()}"/>
                                    <jsp:param name="totalPriceExtraHalfBoard" value="${result.getTotalPriceExtraHalfBoard()}"/>
                                </jsp:include>
                            </c:when>
                        </c:choose>
                    </li>
                </c:forEach>
            </ul>
        </c:when>
        <c:otherwise>
            <p>Sorry, no accommodations available</p>
        </c:otherwise>
    </c:choose>
</body>
</html>
