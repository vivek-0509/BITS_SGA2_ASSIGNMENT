<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="form" uri="jakarta.tags.form" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Edit Author</title>
    <link rel="stylesheet" href="<c:url value='/css/style.css' />">
</head>
<body>
<div class="navbar">
    <h1>Library Management</h1>
    <a href="<c:url value='/' />">Home</a>
    <a href="<c:url value='/authors' />">Authors</a>
    <a href="<c:url value='/books' />">Books</a>
</div>

<div class="container">
    <div class="page-header">
        <h2>Edit Author</h2>
        <a class="btn btn-secondary" href="<c:url value='/authors' />">Back</a>
    </div>

    <form:form method="post" modelAttribute="author"
               action="${pageContext.request.contextPath}/authors/${author.id}">
        <div>
            <label>ID</label>
            <input type="text" value="${author.id}" disabled />
        </div>
        <div>
            <label for="name">Name</label>
            <form:input path="name" id="name" />
            <form:errors path="name" cssClass="error-text" />
        </div>
        <div>
            <label for="nationality">Nationality</label>
            <form:input path="nationality" id="nationality" />
        </div>
        <div>
            <label for="birthYear">Birth Year</label>
            <form:input type="number" path="birthYear" id="birthYear" />
        </div>
        <div>
            <button type="submit" class="btn btn-primary">Update Author</button>
        </div>
    </form:form>
</div>
</body>
</html>
