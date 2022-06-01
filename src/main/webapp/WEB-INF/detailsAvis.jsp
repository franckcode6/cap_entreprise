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
<title>Détails de l'avis</title>
</head>
<body>
	<div class="container text-center mt-5">
		<div class="card mx-auto" style="width: 30rem">
			<div class="card-body">
				<h5 class="card-title">${avis.jeu.nom}</h5><img src="../images/${avis.jeu.image}" height="150">
				<h6 class="card-subtitle mb-2 text-muted">${avis.note}/20
					- ${avis.description}</h6>
				<p class="card-text">${avis.joueur.pseudo}- ${avis.dateEnvoi}</p>
				<p class="card-text">${avis.moderateur.pseudo}- ${avis.dateModeration}</p>
				<a href="/avis">Retour à la liste des avis</a>
			</div>
		</div>
	</div>
</body>
</html>