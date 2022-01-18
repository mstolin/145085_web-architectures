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
        <p>
            <strong>Daily Half Board Price:</strong>
            <span>${param.halfBoardPrice}€</span>
        </p>
    </div>
    <div>
        <a href="/reservation?accommodationId=${param.id}&startDate=${param.startDate}&endDate=${param.endDate}&numberPersons=${param.numberPersons}">Book Hotel for ${param.totalPrice}€</a>
        <a href="/reservation?accommodationId=${param.id}&startDate=${param.startDate}&endDate=${param.endDate}&numberPersons=${param.numberPersons}&isExtraHalfBoardRequested=true">Book Hotel for ${param.totalPriceExtraHalfBoard}€ with extra Half Board</a>
    </div>
</div>
