<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Reservation</title>
    <script type="text/javascript">
        function calculateHotelPrice(nights, pricePerNight, persons, extraHalfBoard) {

        }
        function calculateApartmentPrice(nights, pricePerNight, finalCleaningFee) {

        }
        window.addEventListener('DOMloaded', function () {

        });
    </script>
</head>
<body>
    <div>
        <div>
            <h2>Make a Reservation</h2>
        </div>
        <div>
            <form method="POST" action="<% config.getServletContext(); %>/create">
                <input type="hidden" name="accommodationId" value="${param.accommodationId}"/>
                <div>
                    <label for="guestName">
                        Your Name:
                    </label>
                    <input type="text" name="guestName" id="guestName" value="Mock Guest" disabled/>
                </div>
                <div>
                    <label for="numberPersons">
                        Number of Persons:
                    </label>
                    <input type="number" min="0" name="numberPersons" id="numberPersons" value="${param.numberPersons}" disabled/>
                </div>
                <div>
                    <label for="startDate">
                        Start Date:
                    </label>
                    <input type="date" name="startDate" id="startDate" value="${param.startDate}" disabled/>
                </div>
                <div>
                    <label for="endDate">
                        End Date:
                    </label>
                    <input type="date" name="endDate" id="endDate" value="${param.endDate}" disabled/>
                </div>
                <div>
                    <label for="halfBoard">
                        Is Half-Board requested:
                    </label>
                    <input type="checkbox" name="halfBoard" id="halfBoard"/>
                </div>
                <div>
                    <p>
                        <span>Total Price:</span>
                        <strong>0.00</strong>
                    </p>
                    <input type="submit" value="Confirm Reservation"/>
                </div>
            </form>
        </div>
    </div>
</body>
</html>
