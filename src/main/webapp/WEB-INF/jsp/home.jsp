<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Library Management System</title>
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
        <h2>Welcome to the Library</h2>
    </div>

    <p>This Spring Boot application demonstrates CRUD operations on two related JPA entities: <strong>Author</strong>
        and <strong>Book</strong>, linked via a <code>@OneToMany</code> / <code>@ManyToOne</code> relationship.</p>

    <div class="home-cards">
        <a class="home-card" href="<c:url value='/authors' />">
            <h3>Authors</h3>
            <p>Browse, add, or update authors in the library.</p>
        </a>
        <a class="home-card" href="<c:url value='/books' />">
            <h3>Books</h3>
            <p>View books with their authors (inner join), add or edit titles.</p>
        </a>
        <a class="home-card" href="<c:url value='/h2-console' />" target="_blank">
            <h3>H2 Console</h3>
            <p>Inspect the in-memory database directly.</p>
        </a>
    </div>
</div>
</body>
</html>
