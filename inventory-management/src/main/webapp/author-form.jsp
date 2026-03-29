<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="header.jsp" %>

<c:choose>
    <c:when test="${author.id != null}">
        <h2> Modifier l'auteur</h2>
        <form action="<%= request.getContextPath() %>/update-author" method="post">
        <input type="hidden" name="id" value="<c:out value='${author.id}'/>"/>
    </c:when>
    <c:otherwise>
        <h2> Nouvel auteur</h2>
        <form action="<%= request.getContextPath() %>/create-author" method="post">
    </c:otherwise>
</c:choose>

<c:if test="${not empty errors}">
    <div class="alert alert-danger">
        <strong>Veuillez corriger les erreurs suivantes :</strong>
        <ul>
            <c:forEach var="err" items="${errors}">
                <li><c:out value="${err.value}"/></li>
            </c:forEach>
        </ul>
    </div>
</c:if>

<div class="form-group">
    <label>Nom complet</label>
    <input type="text" class="form-control <c:if test='${not empty errors.fullName}'>is-invalid</c:if>"
           name="fullName" value="<c:out value='${author.fullName}'/>" required>
    <c:if test="${not empty errors.fullName}">
        <div class="invalid-feedback"><c:out value="${errors.fullName}"/></div>
    </c:if>
</div>

<div class="form-group">
    <label>Nationalité</label>
    <input type="text" class="form-control <c:if test='${not empty errors.nationality}'>is-invalid</c:if>"
           name="nationality" value="<c:out value='${author.nationality}'/>" required>
    <c:if test="${not empty errors.nationality}">
        <div class="invalid-feedback"><c:out value="${errors.nationality}"/></div>
    </c:if>
</div>

<div class="form-group">
    <label>Année de naissance</label>
    <input type="number" class="form-control <c:if test='${not empty errors.birthYear}'>is-invalid</c:if>"
           name="birthYear" min="1900" max="2024" placeholder="ex: 1975"
           value="<c:out value='${author.birthYear}'/>" required>
    <c:if test="${not empty errors.birthYear}">
        <div class="invalid-feedback"><c:out value="${errors.birthYear}"/></div>
    </c:if>
</div>

<div class="form-group">
    <label>Email de contact</label>
    <input type="email" class="form-control <c:if test='${not empty errors.email}'>is-invalid</c:if>"
           name="email" value="<c:out value='${author.email}'/>" required>
    <c:if test="${not empty errors.email}">
        <div class="invalid-feedback"><c:out value="${errors.email}"/></div>
    </c:if>
</div>

<button type="submit" class="btn btn-primary"> Enregistrer</button>
<a href="<%= request.getContextPath() %>/authors" class="btn btn-secondary">Annuler</a>
</form>
<%@ include file="footer.jsp" %>