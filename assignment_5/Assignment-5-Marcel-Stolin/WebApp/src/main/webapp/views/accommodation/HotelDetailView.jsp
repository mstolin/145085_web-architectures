<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div>
    <div>
        <h3>${param.name} <em>(${param.stars}* Hotel)</em></h3>
    </div>
    <div>
        <p>
            <strong>Price per Night:</strong>
            <span>${param.price}€</span>
        </p>
    </div>
    <div>
        <a href="/reservation?accommodationId=${param.id}&startDate=${param.startDate}&endDate=${param.endDate}&numberPersons=${param.numberPersons}">Book
            Hotel</a>
    </div>
</div>
