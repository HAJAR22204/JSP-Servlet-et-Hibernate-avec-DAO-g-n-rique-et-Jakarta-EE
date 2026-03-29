<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="header.jsp" %>
<div class="alert alert-danger mt-4">
    <h4> Une erreur inattendue s'est produite</h4>
    <p>Veuillez vérifier les données saisies ou contacter l'administrateur.</p>
    <a href="<%= request.getContextPath() %>/" class="btn btn-dark">Retour à l'accueil</a>
</div>
<%@ include file="footer.jsp" %>