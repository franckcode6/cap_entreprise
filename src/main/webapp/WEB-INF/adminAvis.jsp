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
<body>
	<jsp:include page="header.jsp"></jsp:include>
	<main class="container my-5 min-vh-100">
		<h1>Liste des avis</h1>
		<a href="/admin/avis/aModerer" class="btn btn-outline-dark ">Avis
			A modérer</a> <a href="/admin/avis/moderes" class="btn btn-outline-dark ">Avis
			modérés</a> <a href="/admin/avis" class="btn btn-outline-dark">Tous
			les avis</a>
		<table class="table my-4 table-hover">
			<thead class="table-dark">
				<tr>
					<th>#</th>
					<th scope="col"><a href="avis?sort=dateEnvoi"> Date
							d'envoi</a> <a href="avis?sort=dateEnvoi,DESC">&#8595;</a></th>
					<th scope="col"><a href="avis?sort=jeu.nom"> Nom du jeu</a> <a
						href="avis?sort=jeu.nom,DESC">&#8595;</a></th>
					<th scope="col"><a href="avis?sort=joueur.pseudo"> Pseudo
							du joueur</a> <a href="avis?sort=joueur.pseudo,DESC">&#8595;</a></th>
					<th scope="col"><a href="avis?sort=note"> Note</a> <a
						href="avis?sort=note,DESC">&#8595;</a></th>
					<th scope="col">Image</th>
					<th scope="col"><a href="avis?sort=moderateur.pseudo">
							Pseudo du modérateur</a> <a href="avis?sort=moderateur.pseudo,DESC">&#8595;</a>
					</th>
					<th scope="col">Opérations</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${pages.content}" var="avis" varStatus="compte">
					<tr>
						<th scope="row">${compte.count}/${pages.content.size()}</th>
						<td>${avis}</td>
						<td>${avis.jeu.nom}</td>
						<td>${avis.joueur.pseudo}</td>
						<td>${avis.note}</td>
						<td><img src="/images/${avis.jeu.image}" height="100"></td>
						<c:if test="${avis.moderateur ne null}">
							<td><strong>${avis.moderateur.pseudo}</strong></td>
						</c:if>
						<c:if test="${avis.moderateur eq null}">
							<td><a href="/admin/avis/moderation?id=${avis.id}"
								class="btn btn-outline-danger">Modérer</a></td>
						</c:if>
						<td><a href="/avis/details?id=${avis.id}"
							class="btn btn-primary">Détails</a> <a
							href="/admin/avis/supprimer?id=${avis.id}" class="btn btn-danger">Supprimer</a>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<a href="/admin" class="btn btn-secondary">Retour au Dashboard</a>
		<h2 class="text-center">
			<c:if test="${!pages.first}">
				<a href="avis?page=0&sort=${sort}">&#x23EE;</a>
				<a href="avis?page=${pages.number-1}&sort=${sort}">&#x23EA;</a>
			</c:if>
			Page ${pages.getNumber()+1}
			<c:if test="${!pages.last}">
				<a href="avis?page=${pages.number+1}&sort=${sort}">&#x23E9;</a>
				<a href="avis?page=${pages.totalPages - 1}&sort=${sort}">&#x23ED;</a>
			</c:if>
		</h2>
	</main>
	<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>