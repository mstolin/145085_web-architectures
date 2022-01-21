<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Reservation Confirmation</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
</head>
<body>
    <main>
        <jsp:include page="../HeaderView.jsp"/>
        <div class="d-flex justify-content-center">
            <div class="container bg-white">
                <div class="row">
                    <div class="alert alert-success" role="alert">
                        <h2 class="alert-heading">Reservation Confirmation</h2>
                        <p>Your reservation is confirmed.</p>
                        <hr>
                        <p class="mb-0">Go to <a href="<% config.getServletContext(); %>/reservations" class="alert-link">My Reservation</a> or go back to <a href="/" class="alert-link">Search</a></p>
                    </div>
                </div>
            </div>
        </div>
    </main>
</body>
</html>
