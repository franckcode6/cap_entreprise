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
<body class="container">
	<h1>Liste des jeux</h1>
	<table class="table my-4">
		<thead class="table-dark">
			<tr>
				<th scope="col">Image</th>
				<th scope="col">Nom</th>
				<th scope="col">Editeur</th>
				<th scope="col">Opétations</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${jeux}" var="jeu">
				<tr>
					<th><img src="../images/${jeu.image}" height="150"></th>
					<td>${jeu.nom}</td>
					<td>${jeu.editeur.nom}</td>
					<td>
					<a class="btn btn-primary">Détails</a>
					<a class="btn btn-warning mx-2">Modifier</a>
					<a href="jeux/supprimer?ID=${jeu.id}" class="btn btn-danger" >Supprimer</a>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</body>
</html>