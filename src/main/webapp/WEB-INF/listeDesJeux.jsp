<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css"
	rel="stylesheet">
<title>Liste des jeux</title>
</head>
<body>
	<jsp:include page="header.jsp"></jsp:include>
	<main class="container my-2">
	<h1>Liste des jeux</h1>
	<table class="table my-3">
		<thead class="table-dark">
			<tr>
				<th scope="col">Image</th>
				<th scope="col">
				<a href="jeux?sort=nom">
				Nom</a>
				<a href="jeux?sort=nom,DESC">&#8595;</a>
				</th>
				<th scope="col">
				<a href="jeux?sort=editeur">
				Editeur</a>
				<a href="jeux?sort=editeur,DESC">&#8595;</a>
				</th>
				<th scope="col">Opérations</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${pageDeJeux.content}" var="jeu">
				<tr>
					<th><img src="../images/${jeu.image}" height="150"></th>
					<td>${jeu.nom}</td>
					<td>${jeu.editeur.nom}</td>
					<td>
					<a href="jeux/details?id=${jeu.id}" class="btn btn-primary">Détails</a>
					<a href="jeux/ajout?id=${jeu.id}"  class="btn btn-warning mx-2">Modifier</a>
					<a href="jeux/supprimer?id=${jeu.id}" class="btn btn-danger" >Supprimer</a>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	
	<a href="jeux/ajout" class="btn btn-success">Ajouter un jeu</a>
	
	<a href="jeux/export" class="btn btn-dark">Export</a>
	
	<a href="/admin" class="btn btn-secondary">Retour au Dashboard</a>
	
	<h2 class = "text-center">
			<c:if test="${!pageDeJeux.first}">
				<a href="jeux?page=0&sort=${sort}">&#x23EE;</a>
				<a href="jeux?page=${pageDeJeux.number-1}&sort=${sort}">&#x23EA;</a>
			</c:if>
			Page ${pageDeJeux.getNumber()+1}
			<c:if test="${!pageDeJeux.last}">
				<a href="jeux?page=${pageDeJeux.number+1}&sort=${sort}">&#x23E9;</a>
				<a href="jeux?page=${pageDeJeux.totalPages - 1}&sort=${sort}">&#x23ED;</a>
			</c:if>
		</h2>
	
	</main>
</body>
</html>