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
<title>Dashboard</title>
</head>
<body class="container">
	<h1>Dashboard</h1>
	<main class="mt-5">
		<div class="d-grid gap-2 col-6 mx-auto">
			<a href="/admin/jeux" class="btn btn-info mb-2 text-white" type="button">Liste des jeux</a>
			<a href="/admin/jeux/ajout" class="btn btn-success mb-2" type="button">Ajouter un jeu</a>
			<a class="btn btn-primary mb-2" type="button">Liste des avis</a>
			<a class="btn btn-danger mb-2" type="button">Avis � mod�rer</a>
			<a href="/deconnexion" class="btn btn-secondary" type="button">D�connexion</a>
		</div>
	</main>
</body>
</html>