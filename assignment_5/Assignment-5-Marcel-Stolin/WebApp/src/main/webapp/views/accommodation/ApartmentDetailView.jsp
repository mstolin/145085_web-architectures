<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="card">
    <div class="card-body">
        <h5 class="card-title">${param.name}</h5>
        <h6 class="card-subtitle mb-2 text-muted">Apartment</h6>
    </div>
    <ul class="list-group list-group-flush">
        <li class="list-group-item"><strong>Price per Night:</strong> <span>${param.price}€</span></li>
        <li class="list-group-item"><strong>Max. Persons:</strong> <span>${param.maxPersons}</span></li>
    </ul>
    <div class="card-body">
        <a class="btn btn-primary" href="${pageContext.request.contextPath}/reservation?accommodationId=${param.id}&startDate=${param.startDate}&endDate=${param.endDate}&numberPersons=${param.numberPersons}">Book Apartment for ${param.totalPrice}€</a>
    </div>
</div>
