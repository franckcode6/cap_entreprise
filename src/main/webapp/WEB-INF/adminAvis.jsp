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
<title>Liste des avis</title>
</head>
<body class="container">
	<h1>Liste des avis</h1>
	<main class="my-5">
	<table class="table my-4">
		<thead class="table-dark">
			<tr>
				<th scope="col">
				<a href="avis?sort=dateEnvoi">
				Date d'envoi</a>
				<a href="avis?sort=dateEnvoi,DESC">&#8595;</a>
				</th>
				<th scope="col">
				<a href="avis?sort=jeu.nom">
				Nom du jeu</a>
				<a href="avis?sort=jeu.nom,DESC">&#8595;</a>
				</th>
				<th scope="col">
				<a href="avis?sort=joueur.pseudo">
				Pseudo du joueur</a>
				<a href="avis?sort=joueur.pseudo,DESC">&#8595;</a>
				</th>
				<th scope="col">
				<a href="avis?sort=note">
				Note</a>
				<a href="avis?sort=note,DESC">&#8595;</a>
				</th>
				<th scope="col">Image</th>
				<th scope="col">
				<a href="avis?sort=moderateur.pseudo">
				Pseudo du modérateur</a>
				<a href="avis?sort=moderateur.pseudo,DESC">&#8595;</a>
				</th>
				<th scope="col">Opérations</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${avis}" var="avis">
				<tr>
				    <td>${avis.dateEnvoi}</td>
					<td>${avis.jeu.nom}</td>
					<td>${avis.joueur.pseudo}</td>
					<td>${avis.note}</td>
					<td><img src="../images/${avis.jeu.image}" height="150"></td>
					<c:if test="${avis.moderateur.pseudo ne null}">
					<td>${avis.moderateur.pseudo}</td>
					</c:if>
					<c:if test="${avis.moderateur.pseudo eq null}">
					<td>
					<a href="avis/moderation?id=${avis.id}" class="btn btn-primary">Modérer</a>
					</td>
					</c:if>
					<td>
					<a href="../avis/details?id=${avis.id}" class="btn btn-primary">Détails</a>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	</main>
</body>
</html>