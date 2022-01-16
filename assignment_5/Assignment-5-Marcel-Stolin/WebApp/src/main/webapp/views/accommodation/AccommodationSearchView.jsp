<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Search for Accommodations</title>
</head>
<body>
    <div>
        <form method="GET" action="<% config.getServletContext(); %>/results">
            <div>
                <label for="startDate">Start Date</label>
                <input type="date" id="startDate" name="startDate">
            </div>
            <div>
                <label for="endDate">End Date</label>
                <input type="date" id="endDate" name="endDate">
            </div>
            <div>
                <label for="numberPersons">Number of Persons</label>
                <input id="numberPersons" type="number" name="numberPersons" value="0" min="0">
            </div>
            <div>
                <input type="submit" title="Search" />
            </div>
        </form>
    </div>
</body>
</html>
