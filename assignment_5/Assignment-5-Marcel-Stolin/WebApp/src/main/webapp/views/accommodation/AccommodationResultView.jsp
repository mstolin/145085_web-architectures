<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="searchResult"
             class="it.unitn.disi.webarch.mstolin.webapp.models.accommodation.AccommodationSearchResult"
             scope="request"/>
<html>
<head>
    <title>Accommodations</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
</head>
<body>
    <main>
        <jsp:include page="../HeaderView.jsp"/>
        <div class="d-flex justify-content-center">
            <div class="container bg-white">
                <div class="row">
                    <h2>Available Accommodations</h2>
                </div>
                <div class="row">
                    <c:choose>
                        <c:when test="${searchResult.getResults().size() > 0}">
                            <c:forEach items="${searchResult.getResults()}" var="result">
                                <div style="width: 50%; padding:0.5rem;">
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
                                </div>
                            </c:forEach>
                        </c:when>
                        <c:otherwise>
                            <div class="alert alert-warning">
                                Sorry, no accommodations available for the given date and number of persons.
                                <a href="${pageContext.request.contextPath}" class="alert-link">Go back</a>
                            </div>
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>
        </div>
    </main>
</body>
</html>
