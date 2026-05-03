<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Books</title>
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
        <h2>Books (Inner Join with Authors)</h2>
        <a class="btn btn-primary" href="<c:url value='/books/new' />">+ Add Book</a>
    </div>

    <c:if test="${not empty success}">
        <div class="alert alert-success">${success}</div>
    </c:if>
    <c:if test="${not empty error}">
        <div class="alert alert-error">${error}</div>
    </c:if>

    <p style="color:#666;font-size:13px;margin-bottom:10px;">
        Rows below come from a custom JPQL <strong>INNER JOIN</strong> between Book and Author.
    </p>

    <table>
        <thead>
        <tr>
            <th>Book ID</th>
            <th>Title</th>
            <th>ISBN</th>
            <th>Year</th>
            <th>Genre</th>
            <th>Author</th>
            <th>Nationality</th>
            <th>Actions</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="r" items="${rows}">
            <tr>
                <td>${r.bookId}</td>
                <td><c:out value="${r.title}" /></td>
                <td><c:out value="${r.isbn}" /></td>
                <td>${r.publishedYear}</td>
                <td><c:out value="${r.genre}" /></td>
                <td><c:out value="${r.authorName}" /></td>
                <td><c:out value="${r.nationality}" /></td>
                <td><a class="btn btn-edit" href="<c:url value='/books/${r.bookId}/edit' />">Edit</a></td>
            </tr>
        </c:forEach>
        <c:if test="${empty rows}">
            <tr><td colspan="8" style="text-align:center;color:#888;">No books yet.</td></tr>
        </c:if>
        </tbody>
    </table>
</div>
</body>
</html>
