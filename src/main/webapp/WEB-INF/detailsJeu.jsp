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
<title>Détails du jeu</title>
</head>
<body>
	<div class="container text-center mt-5">
		<div class="card mx-auto" style="width: 30rem">
			<div class="card-body">
				<h5 class="card-title">${jeu.nom}- ${jeu.editeur.nom}</h5>
				<h6 class="card-subtitle mb-2 text-muted">${jeu.modeleEconomique.nom}
					- ${jeu.classification.nom}</h6>
				<p class="card-text">${jeu.description}</p>
				<a href="/admin/jeux">Retour à la liste des jeux</a>
			</div>
		</div>
	</div>
</body>
</html>