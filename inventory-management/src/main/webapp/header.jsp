<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Bibliothèque en ligne</title>
    <link rel="stylesheet"
          href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
</head>
<body>
<nav class="navbar navbar-expand-md navbar-dark bg-dark">
    <div class="container">
        <a class="navbar-brand" href="<%= request.getContextPath() %>/"> Ma Bibliothèque</a>
        <div class="navbar-nav ml-auto">
            <div class="nav-item dropdown">
                <a class="nav-link dropdown-toggle" href="#" data-toggle="dropdown">Auteurs</a>
                <div class="dropdown-menu">
                    <a class="dropdown-item" href="<%= request.getContextPath() %>/authors">Liste des auteurs</a>
                    <a class="dropdown-item" href="<%= request.getContextPath() %>/author-form">Ajouter un auteur</a>
                </div>
            </div>
            <div class="nav-item dropdown">
                <a class="nav-link dropdown-toggle" href="#" data-toggle="dropdown">Livres</a>
                <div class="dropdown-menu">
                    <a class="dropdown-item" href="<%= request.getContextPath() %>/books">Liste des livres</a>
                    <a class="dropdown-item" href="<%= request.getContextPath() %>/book-form">Ajouter un livre</a>
                </div>
            </div>
            <div class="nav-item dropdown">
                <a class="nav-link dropdown-toggle" href="#" data-toggle="dropdown">Catégories</a>
                <div class="dropdown-menu">
                    <a class="dropdown-item" href="<%= request.getContextPath() %>/categories">Liste des catégories</a>
                    <a class="dropdown-item" href="<%= request.getContextPath() %>/category-form">Ajouter une catégorie</a>
                </div>
            </div>
        </div>
    </div>
</nav>
<div class="container mt-4">