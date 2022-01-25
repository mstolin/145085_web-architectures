<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Search for Accommodations</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
</head>
<body class="bg-light">
    <main>
        <jsp:include page="../HeaderView.jsp"/>
        <div class="d-flex justify-content-center">
            <div class="container bg-white">
                <div class="row">
                    <h2>Search for Accommodations</h2>
                </div>
                <div class="row">
                    <form method="GET" action="<% config.getServletContext(); %>/results">
                        <div class="row">
                            <div class="col">
                                <div class="mb-3">
                                    <label for="startDate" class="form-label">From:</label>
                                    <input class="form-control" type="date" id="startDate" name="startDate"/>
                                </div>
                            </div>
                            <div class="col">
                                <div class="mb-3">
                                    <label for="endDate" class="form-label">To:</label>
                                    <input class="form-control" type="date" id="endDate" name="endDate"/>
                                </div>
                            </div>
                        </div>
                        <div class="mb-3">
                            <label for="numberPersons" class="form-label">Number of Guests:</label>
                            <input class="form-control" id="numberPersons" type="number" name="numberPersons" value="0" min="0">
                        </div>
                        <button type="submit" class="btn btn-primary">Search</button>
                    </form>
                </div>
            </div>
        </div>
    </main>
</body>
</html>
