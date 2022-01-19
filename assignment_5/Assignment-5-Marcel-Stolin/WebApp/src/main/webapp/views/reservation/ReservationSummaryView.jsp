<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="reservationSummary"
             class="it.unitn.disi.webarch.mstolin.webapp.models.reservation.ReservationSummary"
             scope="request"/>
<html>
<head>
    <title>Reservation</title>
</head>
<body>
    <div>
        <div>
            <h2>Make a Reservation</h2>
        </div>
        <div>
            <p>
                <c:choose>
                    <c:when test="${reservationSummary.isAccommodationApartment()}">
                        <strong>Apartment:</strong>
                        <span>${reservationSummary.accommodation.getName()}</span>
                    </c:when>
                    <c:otherwise>
                        <strong>Hotel:</strong>
                        <span>${reservationSummary.accommodation.getName()}(${reservationSummary.accommodation.getStars()}*)</span>
                    </c:otherwise>
                </c:choose>
            </p>
            <p>
                <strong>Date:</strong>
                <span>From ${param.startDate} to ${param.endDate}</span>
            </p>
            <c:if test="${not reservationSummary.isAccommodationApartment()}">
                <p>
                    <strong>Number of Guests:</strong>
                    <span>${reservationSummary.getNumberPersons()}</span>
                </p>
                <p>
                    <strong>Is half board requested:</strong>
                    <span>${reservationSummary.isHalfBoardRequested()}</span>
                </p>
            </c:if>
            <p>
                <strong>Total Price:</strong>
                <span>${reservationSummary.getTotalPrice()}</span>
            </p>
        </div>
        <div>
            <form method="POST" action="<% config.getServletContext(); %>/create">
                <input type="hidden" name="accommodationId" value="${param.accommodationId}"/>
                <input type="hidden" name="numberPersons" value="${param.numberPersons}"/>
                <input type="hidden" name="startDate" value="${param.startDate}"/>
                <input type="hidden" name="endDate" value="${param.endDate}"/>
                <c:if test="${not empty param.isHalfBoardRequested}" >
                    <input type="hidden" name="isHalfBoardRequested" value="${param.isHalfBoardRequested}"/>
                </c:if>
                <label for="guestName">
                    Your Name:
                </label>
                <input type="text" name="guestName" id="guestName" value="Mock Guest" disabled/>
                <input type="submit" value="Confirm Reservation"/>
            </form>
        </div>
    </div>
</body>
</html>
