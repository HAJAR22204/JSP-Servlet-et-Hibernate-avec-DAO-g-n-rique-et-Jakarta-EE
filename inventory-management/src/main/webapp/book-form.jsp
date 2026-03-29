<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="header.jsp" %>
<c:choose>
    <c:when test="${book != null}">
        <h2>Modifier le livre</h2>
        <form action="<%= request.getContextPath() %>/update-book" method="post">
        <input type="hidden" name="id" value="<c:out value='${book.id}'/>"/>
    </c:when>
    <c:otherwise>
        <h2> Nouveau livre</h2>
        <form action="<%= request.getContextPath() %>/create-book" method="post">
    </c:otherwise>
</c:choose>

<div class="form-group">
    <label>Titre</label>
    <input type="text" class="form-control" name="title"
           value="<c:out value='${book.title}'/>" required>
</div>
<div class="form-group">
    <label>ISBN</label>
    <input type="text" class="form-control" name="isbn"
           value="<c:out value='${book.isbn}'/>" required>
</div>
<div class="form-group">
    <label>Genre</label>
    <input type="text" class="form-control" name="genre"
           value="<c:out value='${book.genre}'/>" required>
</div>
<div class="form-group">
    <label>Année de publication</label>
    <input type="number" class="form-control" name="publicationYear"
           min="1000" max="2024" placeholder="ex: 2001"
           value="<c:out value='${book.publicationYear}'/>" required>
</div>
<div class="form-group">
    <label>Exemplaires disponibles</label>
    <input type="number" class="form-control" name="availableCopies"
           min="0" value="<c:out value='${book.availableCopies}'/>" required>
</div>

<!-- Sélection de catégorie -->
<div class="form-group">
    <label>Catégorie</label>
    <select class="form-control" name="categoryId">
        <option value="">-- Sans catégorie --</option>
        <c:forEach var="cat" items="${categories}">
            <option value="<c:out value='${cat.id}'/>"
                    <c:if test="${book.category != null && book.category.id == cat.id}">selected</c:if>>
                <c:out value="${cat.name}"/>
            </option>
        </c:forEach>
    </select>
</div>

<button type="submit" class="btn btn-primary"> Enregistrer</button>
<a href="<%= request.getContextPath() %>/books" class="btn btn-secondary">Annuler</a>
</form>
<%@ include file="footer.jsp" %>