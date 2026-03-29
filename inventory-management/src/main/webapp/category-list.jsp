<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="header.jsp" %>
<h2> Catégories</h2><hr>
<a href="<%= request.getContextPath() %>/category-form" class="btn btn-success mb-3">+ Ajouter une catégorie</a>
<table class="table table-bordered table-hover">
    <thead class="thead-dark">
    <tr>
        <th>ID</th>
        <th>Nom</th>
        <th>Description</th>
        <th>Livres disponibles</th>
        <th>Actions</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="cat" items="${categories}">
        <tr>
            <td><c:out value="${cat.id}"/></td>
            <td><strong><c:out value="${cat.name}"/></strong></td>
            <td><c:out value="${cat.description}"/></td>
            <td>
                <c:choose>
                    <c:when test="${not empty cat.books}">
                        <c:forEach var="book" items="${cat.books}">
                <span class="badge badge-warning text-dark mr-1">
                  <c:out value="${book.title}"/>
                </span>
                        </c:forEach>
                    </c:when>
                    <c:otherwise>
                        <span class="text-muted">Aucun livre</span>
                    </c:otherwise>
                </c:choose>
            </td>
            <td>
                <a href="<%= request.getContextPath() %>/category-form?action=edit&id=<c:out value='${cat.id}'/>"
                   class="btn btn-sm btn-primary">Modifier</a>
                <a href="<%= request.getContextPath() %>/delete-category?id=<c:out value='${cat.id}'/>"
                   class="btn btn-sm btn-danger"
                   onclick="return confirm('Supprimer cette catégorie ?')">Supprimer️</a>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<%@ include file="footer.jsp" %>