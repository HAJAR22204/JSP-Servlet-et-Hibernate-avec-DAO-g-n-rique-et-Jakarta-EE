<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="header.jsp" %>
<c:choose>
    <c:when test="${category != null}">
        <h2> Modifier la catégorie</h2>
        <form action="<%= request.getContextPath() %>/update-category" method="post">
        <input type="hidden" name="id" value="<c:out value='${category.id}'/>"/>
    </c:when>
    <c:otherwise>
        <h2> Nouvelle catégorie</h2>
        <form action="<%= request.getContextPath() %>/create-category" method="post">
    </c:otherwise>
</c:choose>
<div class="form-group">
    <label>Nom de la catégorie</label>
    <input type="text" class="form-control" name="name"
           value="<c:out value='${category.name}'/>" required>
</div>
<div class="form-group">
    <label>Description</label>
    <textarea class="form-control" name="description" rows="3"><c:out value='${category.description}'/></textarea>
</div>
<button type="submit" class="btn btn-primary"> Enregistrer</button>
<a href="<%= request.getContextPath() %>/categories" class="btn btn-secondary">Annuler</a>
</form>
<%@ include file="footer.jsp" %>
