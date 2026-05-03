<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="form" uri="jakarta.tags.form" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Add Book</title>
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
        <h2>Add Book</h2>
        <a class="btn btn-secondary" href="<c:url value='/books' />">Back</a>
    </div>

    <c:if test="${not empty error}">
        <div class="alert alert-error">${error}</div>
    </c:if>

    <form:form method="post" modelAttribute="book" action="${pageContext.request.contextPath}/books">
        <div>
            <label for="title">Title</label>
            <form:input path="title" id="title" />
            <form:errors path="title" cssClass="error-text" />
        </div>
        <div>
            <label for="isbn">ISBN</label>
            <form:input path="isbn" id="isbn" />
        </div>
        <div>
            <label for="publishedYear">Published Year</label>
            <form:input type="number" path="publishedYear" id="publishedYear" />
        </div>
        <div>
            <label for="genre">Genre</label>
            <form:input path="genre" id="genre" />
        </div>
        <div>
            <label for="authorId">Author</label>
            <select name="authorId" id="authorId" required>
                <option value="">-- Select Author --</option>
                <c:forEach var="a" items="${authors}">
                    <option value="${a.id}"><c:out value="${a.name}" /></option>
                </c:forEach>
            </select>
        </div>
        <div>
            <button type="submit" class="btn btn-primary">Save Book</button>
        </div>
    </form:form>
</div>
</body>
</html>
