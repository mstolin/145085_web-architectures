<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="reservationSummary"
             class="it.unitn.disi.webarch.mstolin.webapp.models.reservation.ReservationSummary"
             scope="request"/>
<html>
<head>
    <title>Reservation</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
</head>
<body>
    <main>
        <jsp:include page="../HeaderView.jsp"/>
        <div class="d-flex justify-content-center">
            <div class="container bg-white">
                <div class="row">
                    <h2>Reservation Summary</h2>
                </div>

                <div class="row">
                    <div class="card">
                        <div class="card-body">
                            <h5 class="card-title">${reservationSummary.accommodation.getName()}</h5>
                            <c:choose>
                                <c:when test="${reservationSummary.isAccommodationApartment()}">
                                    <h6 class="card-subtitle mb-2 text-muted">Apartment</h6>
                                </c:when>
                                <c:otherwise>
                                    <h6 class="card-subtitle mb-2 text-muted">${reservationSummary.accommodation.getStars()}* Hotel</h6>
                                </c:otherwise>
                            </c:choose>
                        </div>
                        <ul class="list-group list-group-flush">
                            <li class="list-group-item"><strong>Date:</strong> <span>From ${param.startDate} to ${param.endDate}</span></li>
                            <c:if test="${not reservationSummary.isAccommodationApartment()}">
                                <li class="list-group-item"><strong>Number of Guests:</strong> <span>${reservationSummary.getNumberPersons()}</span></li>
                                <li class="list-group-item"><strong>Is half board requested:</strong> <span>${reservationSummary.isHalfBoardRequested()}</span></li>
                            </c:if>
                            <li class="list-group-item"><strong>Total Price:</strong> <span>${reservationSummary.getTotalPrice()}€</span></li>
                        </ul>
                        <div class="card-body">
                            <form method="POST" action="<% config.getServletContext(); %>/reservation/confirm">
                                <input type="hidden" name="accommodationId" value="${param.accommodationId}"/>
                                <input type="hidden" name="numberPersons" value="${param.numberPersons}"/>
                                <input type="hidden" name="startDate" value="${param.startDate}"/>
                                <input type="hidden" name="endDate" value="${param.endDate}"/>
                                <input type="hidden" name="guestName" value="Mock Guest"/>
                                <c:if test="${not empty param.isHalfBoardRequested}" >
                                    <input type="hidden" name="isHalfBoardRequested" value="${param.isHalfBoardRequested}"/>
                                </c:if>
                                <button type="submit" class="btn btn-primary">Confirm Reservation</button>
                                <a class="btn btn-secondary" href="<% config.getServletContext(); %>/results?startDate=${param.startDate}&endDate=${param.endDate}&numberPersons=${param.numberPersons}">Cancel</a>
                            </form>
                        </div>
                    </div>
                </div>

            </div>
        </div>
    </main>
</body>
</html>
