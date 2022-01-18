<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div>
    <div>
        <h3>${param.name} <em>(Apartment)</em></h3>
    </div>
    <div>
        <p>
            <strong>Max. Persons:</strong>
            <span>${param.maxPersons}</span>
        </p>
        <p>
            <strong>Price per Night:</strong>
            <span>${param.price}€</span>
        </p>
    </div>
    <div>
        <a href="/reservation?accommodationId=${param.id}&startDate=${param.startDate}&endDate=${param.endDate}&numberPersons=${param.numberPersons}">Book Apartment for ${param.totalPrice}€</a>
    </div>
</div>
