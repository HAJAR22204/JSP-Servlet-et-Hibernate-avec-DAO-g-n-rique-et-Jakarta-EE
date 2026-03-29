<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="header.jsp" %>
<h2> Liste des Auteurs</h2><hr>
<a href="<%= request.getContextPath() %>/author-form" class="btn btn-success mb-3">+ Ajouter un auteur</a>
<table class="table table-bordered table-hover">
    <thead class="thead-dark">
    <tr>
        <th>ID</th><th>Nom complet</th><th>Nationalité</th>
        <th>Année de naissance</th><th>Email</th><th>Actions</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="author" items="${authors}">
        <tr>
            <td><c:out value="${author.id}"/></td>
            <td><c:out value="${author.fullName}"/></td>
            <td><c:out value="${author.nationality}"/></td>
            <td><c:out value="${author.birthYear}"/></td>
            <td><c:out value="${author.email}"/></td>
            <td>
                <a href="<%= request.getContextPath() %>/author-form?action=edit&id=<c:out value='${author.id}'/>"
                   class="btn btn-sm btn-primary">Modifier</a>
                <a href="<%= request.getContextPath() %>/delete-author?id=<c:out value='${author.id}'/>"
                   class="btn btn-sm btn-danger"
                   onclick="return confirm('Supprimer cet auteur ?')"> Supprimer</a>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<%@ include file="footer.jsp" %>