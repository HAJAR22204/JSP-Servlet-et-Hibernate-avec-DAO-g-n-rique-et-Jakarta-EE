<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="header.jsp" %>
<div class="jumbotron">
    <h1 class="display-4">Bienvenue dans Ma Bibliothèque</h1>
    <p class="lead">Gérez vos auteurs et vos livres.</p>
    <hr class="my-4">
    <div class="row">
        <div class="col-md-6">
            <div class="card mb-4">
                <div class="card-header bg-dark text-white"> Auteurs</div>
                <div class="card-body">
                    <a class="btn btn-dark" href="<%= request.getContextPath() %>/authors">Voir les auteurs</a>
                    <a class="btn btn-success" href="<%= request.getContextPath() %>/author-form">Ajouter</a>
                </div>
            </div>
        </div>
        <div class="col-md-6">
            <div class="card mb-4">
                <div class="card-header bg-warning text-dark"> Livres</div>
                <div class="card-body">
                    <a class="btn btn-warning text-dark" href="<%= request.getContextPath() %>/books">Voir les livres</a>
                    <a class="btn btn-success" href="<%= request.getContextPath() %>/book-form">Ajouter</a>
                </div>
            </div>
        </div>
    </div>
</div>
<%@ include file="footer.jsp" %>