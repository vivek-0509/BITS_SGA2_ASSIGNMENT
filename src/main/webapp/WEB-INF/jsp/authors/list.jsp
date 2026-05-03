<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Authors</title>
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
        <h2>Authors</h2>
        <a class="btn btn-primary" href="<c:url value='/authors/new' />">+ Add Author</a>
    </div>

    <c:if test="${not empty success}">
        <div class="alert alert-success">${success}</div>
    </c:if>
    <c:if test="${not empty error}">
        <div class="alert alert-error">${error}</div>
    </c:if>

    <table>
        <thead>
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Nationality</th>
            <th>Birth Year</th>
            <th>Books</th>
            <th>Actions</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="a" items="${authors}">
            <tr>
                <td>${a.id}</td>
                <td><c:out value="${a.name}" /></td>
                <td><c:out value="${a.nationality}" /></td>
                <td>${a.birthYear}</td>
                <td>${a.books.size()}</td>
                <td><a class="btn btn-edit" href="<c:url value='/authors/${a.id}/edit' />">Edit</a></td>
            </tr>
        </c:forEach>
        <c:if test="${empty authors}">
            <tr><td colspan="6" style="text-align:center;color:#888;">No authors yet.</td></tr>
        </c:if>
        </tbody>
    </table>
</div>
</body>
</html>
