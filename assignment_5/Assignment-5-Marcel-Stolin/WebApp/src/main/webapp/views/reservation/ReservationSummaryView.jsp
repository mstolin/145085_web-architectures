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
                            <li class="list-group-item"><strong>Number of Guests:</strong> <span>${reservationSummary.getNumberPersons()}</span></li>
                            <c:if test="${reservationSummary.isAccommodationApartment()}">
                                <li class="list-group-item"><strong>Max. Guests:</strong> <span>${reservationSummary.accommodation.getMaxPersons()}</span></li>
                                <li class="list-group-item"><strong>Final Cleaning Fee:</strong> <span>${reservationSummary.accommodation.getFinalCleaningFee()}€</span></li>
                            </c:if>
                            <c:if test="${not reservationSummary.isAccommodationApartment()}">
                                <li class="list-group-item"><strong>Is Half-Board requested:</strong> <span>${reservationSummary.isHalfBoardRequested()}</span></li>
                                <c:if test="${reservationSummary.isHalfBoardRequested()}">
                                    <li class="list-group-item"><strong>Daily Half-Board Price:</strong> <span>${reservationSummary.accommodation.getExtraHalfBoard()}€</span></li>
                                </c:if>
                            </c:if>
                            <li class="list-group-item"><strong>Total Price:</strong> <span>${reservationSummary.getTotalPrice()}€</span></li>
                        </ul>
                        <div class="card-body">
                            <form method="POST" action="<% config.getServletContext(); %>/reservation/confirm">
                                <input type="hidden" name="accommodationId" value="${param.accommodationId}"/>
                                <input type="hidden" name="numberPersons" value="${param.numberPersons}"/>
                                <input type="hidden" name="startDate" value="${param.startDate}"/>
                                <input type="hidden" name="endDate" value="${param.endDate}"/>
                                <c:if test="${not empty param.isHalfBoardRequested}" >
                                    <input type="hidden" name="isHalfBoardRequested" value="${param.isHalfBoardRequested}"/>
                                </c:if>
                                <div class="row">
                                    <div class="col">
                                        <div class="mb-3">
                                            <label for="firstName" class="form-label">First Name:</label>
                                            <input class="form-control" placeholder="First Name" type="text" id="firstName" name="firstName" value="Mock"/>
                                        </div>
                                    </div>
                                    <div class="col">
                                        <div class="mb-3">
                                            <label for="lastName" class="form-label">Last Name:</label>
                                            <input class="form-control" placeholder="Last Name" type="text" id="lastName" name="lastName" value="Guest"/>
                                        </div>
                                    </div>
                                </div>
                                <div class="mb-3">
                                    <label for="creditCard" class="form-label">Credit Card:</label>
                                    <input class="form-control" type="text" id="creditCard" name="creditCard" value="4000-1234-5678-9010"/>
                                </div>
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
