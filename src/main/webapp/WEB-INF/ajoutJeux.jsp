<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet">
<title>Jeux ajout</title>
</head>
<body class="container">
<h1>Ajouter un nouveau jeu</h1>

	<form action="" method="post" enctype="multipart/form-data" class="my-4">
	<div class="mb-3">
		<label for="nom" class="form-label">Nom</label>
		<input name="nom" class="form-control"><br>
	</div>

	<div class="mb-3">
		<label for="description" class="form-label">Description</label>
		<textarea name="description" class="form-control"></textarea><br>
	</div>
	
	<div class="mb-3">
		<label for="dateForm" class="form-label">Date de sortie</label>
		<input type="date" name="dateSortie" class="form-control"><br>
	</div>
	
	<div class="mb-3">
		<label for="image" class="form-label">T�l�verser une image</label>
		<input type="file" name="image" accept="image" placeholder="URL" class="form-control"><br>
	</div>
	
	<div class="mb-3">
		<label for="classification_id" class="form-label">Classification</label>
		<select name="classification" class="form-select">
			<option value="0">Merci de choisir une classification</option>
			<c:forEach items="${classifications}" var="classification">
				<option value="${classification.id}">${classification.nom}</option>
			</c:forEach>
		</select><br>
	</div>
	
	<div class="mb-3">
		<label for="editeur_id" class="form-label">Editeur</label> 
		<select name="editeur" class="form-select">
			<option value="0">Merci de choisir un �diteur</option>
			<c:forEach items="${editeurs}" var="editeur">
				<option value="${editeur.id}">${editeur.nom}</option>
			</c:forEach>
		</select><br>
	</div>
	
	<div class="mb-3">
		<label for="genre_id" class="form-label">Genre</label> 
		<select name="genre" class="form-select">
			<option value="0">Merci de choisir un genre</option>
			<c:forEach items="${genres}" var="genre">
				<option value="${genre.id}">${genre.nom}</option>
			</c:forEach>
		</select><br>
	</div>
	
	<div class="mb-3">
		<label for="modeleEconomique_id" class="form-label">Modele Economique</label> 
		<select name="modeleEconomique" class="form-select">
			<option value="0">Merci de choisir un mod�le �conomique</option>
			<c:forEach items="${modeleEconomiques}" var="modeleEconomique">
				<option value="${modeleEconomique.id}">${modeleEconomique.nom}</option>
			</c:forEach>
		</select><br>
	</div>
	
	<div class="mb-3">
		<label for="plateforme_id">Plateforme(s)</label> 
		<select name="plateformes" multiple class="form-select">
			<option value="0">Merci de choisir une plateforme</option>
			<c:forEach items="${plateformes}" var="plateforme">
				<option value="${plateforme.id}">${plateforme.nom}</option>
			</c:forEach>
		</select><br>
	</div>
	 <input class="btn btn-success" type="submit" value="Ajouter">
	</form>
</body>
</html>