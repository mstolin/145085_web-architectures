<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="model"
             class="it.unitn.disi.webarch.mstolin.webapp.models.reservation.ReservationListModel"
             scope="request"/>
<html>
<head>
    <title>My Reservations</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
</head>
<body>
    <main>
        <jsp:include page="../HeaderView.jsp"/>
        <div class="container">
            <h2>Your Reservations</h2>
            <div class="list-group">
                <c:choose>
                    <c:when test="${model.getReservationEntities().size() > 0}">
                        <c:forEach items="${model.getReservationEntities()}" var="reservation">
                            <div class="list-group-item">
                                <div class="d-flex w-100 justify-content-between">
                                    <h3 class="mb-1">${reservation.getAccommodation().getName()}</h3>
                                </div>
                                <p class="mb-1">
                                    <strong>Date:</strong>
                                    <span>From ${reservation.getStartDate()} to ${reservation.getEndDate()}</span>
                                </p>
                                <p class="mb-1">
                                    <strong>Price:</strong>
                                    <span>${reservation.getTotalPrice()}€</span>
                                </p>
                            </div>
                        </c:forEach>
                    </c:when>
                    <c:otherwise>
                        <div class="list-group-item list-group-item-warning">
                            You haven`t made any reservations yet
                        </div>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
    </main>
</body>
</html>
