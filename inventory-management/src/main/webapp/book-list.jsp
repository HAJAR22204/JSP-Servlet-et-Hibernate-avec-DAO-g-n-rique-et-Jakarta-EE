<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="header.jsp" %>
<h2> Catalogue des Livres</h2><hr>
<div class="row mb-3">
    <div class="col-md-6">
        <a href="<%= request.getContextPath() %>/book-form" class="btn btn-success">+ Ajouter un livre</a>
    </div>
    <div class="col-md-6">
        <form action="<%= request.getContextPath() %>/books" method="get" class="form-inline float-right">
            <input type="text" class="form-control mr-2" name="keyword"
                   placeholder="Titre, genre, ISBN..." value="${keyword}">
            <button type="submit" class="btn btn-warning"> Rechercher</button>
        </form>
    </div>
</div>
<table class="table table-bordered table-hover">
    <thead class="thead-dark">
    <tr>
        <th>ID</th>
        <th>Titre</th>
        <th>ISBN</th>
        <th>Genre</th>
        <th>Année</th>
        <th>Exemplaires</th>
        <th>Catégorie</th>
        <th>Actions</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="book" items="${books}">
        <tr>
            <td><c:out value="${book.id}"/></td>
            <td><c:out value="${book.title}"/></td>
            <td><c:out value="${book.isbn}"/></td>
            <td><c:out value="${book.genre}"/></td>
            <td><c:out value="${book.publicationYear}"/></td>
            <td><c:out value="${book.availableCopies}"/></td>
            <td>
                <c:choose>
                    <c:when test="${book.category != null}">
              <span class="badge badge-info">
                <c:out value="${book.category.name}"/>
              </span>
                    </c:when>
                    <c:otherwise>
                        <span class="badge badge-secondary">Sans catégorie</span>
                    </c:otherwise>
                </c:choose>
            </td>
            <td>
                <a href="<%= request.getContextPath() %>/book-form?action=edit&id=<c:out value='${book.id}'/>"
                   class="btn btn-sm btn-primary">Modifier</a>
                <a href="<%= request.getContextPath() %>/delete-book?id=<c:out value='${book.id}'/>"
                   class="btn btn-sm btn-danger"
                   onclick="return confirm('Supprimer ce livre ?')">Supprimer</a>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<%@ include file="footer.jsp" %>