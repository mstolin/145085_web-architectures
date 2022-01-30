<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Get My Reservations</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
</head>
<body class="bg-light">
    <main>
        <jsp:include page="../HeaderView.jsp"/>
        <div class="d-flex justify-content-center">
            <div class="container bg-white">
                <div class="row">
                    <h2>Get My Reservations</h2>
                </div>
                <div class="row">
                    <form method="POST" action="${pageContext.request.contextPath}/reservations">
                        <div class="mb-3">
                            <label for="firstName" class="form-label">First Name:</label>
                            <input class="form-control" type="text" id="firstName" name="firstName" placeholder="First Name">
                        </div>
                        <div class="mb-3">
                            <label for="lastName" class="form-label">Last Name:</label>
                            <input class="form-control" type="text" id="lastName" name="lastName" placeholder="Last Name">
                        </div>
                        <button type="submit" class="btn btn-primary">Search</button>
                    </form>
                </div>
            </div>
        </div>
    </main>
</body>
</html>
