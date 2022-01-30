<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="card">
    <div class="card-body">
        <h5 class="card-title">${param.name}</h5>
        <h6 class="card-subtitle mb-2 text-muted">${param.stars}* Hotel</h6>
    </div>
    <ul class="list-group list-group-flush">
        <li class="list-group-item"><strong>Price per Night:</strong> <span>${param.price}€</span></li>
        <li class="list-group-item"><strong>Daily Half Board Price <em>(Optional)</em>:</strong> <span>${param.halfBoardPrice}€</span></li>
    </ul>
    <div class="card-body">
        <a class="btn btn-primary" href="${pageContext.request.contextPath}/reservation?accommodationId=${param.id}&startDate=${param.startDate}&endDate=${param.endDate}&numberPersons=${param.numberPersons}">Book Hotel for ${param.totalPrice}€</a>
        <a class="btn btn-primary" href="${pageContext.request.contextPath}/reservation?accommodationId=${param.id}&startDate=${param.startDate}&endDate=${param.endDate}&numberPersons=${param.numberPersons}&isHalfBoardRequested=true">Book Hotel for ${param.totalPriceExtraHalfBoard}€ with extra Half Board</a>
    </div>
</div>
