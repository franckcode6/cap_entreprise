<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h1>Ajout de jeu</h1>

	<form action="" method="post" enctype="multipart/form-data">
		<label for="nom">Nom</label><input name="nom"><br>

		<label for="description">Description</label>
		<textarea name="description"></textarea><br>
		
		<label for="dateForm">Date de sortie</label>
		<input type="date" name="dateSortie"><br>
		
		<input type="file" name="image" accept="image" placeholder="URL"><br>

		<label for="classification_id">Classification</label>
		<select name="classification">
			<option value="0">Merci de choisir une classification</option>
			<c:forEach items="${classifications}" var="classification">
				<option value="${classification.id}">${classification.nom}</option>
			</c:forEach>
		</select><br>
		
		<label for="editeur_id">Editeur</label> 
		<select name="editeur">
			<option value="0">Merci de choisir un éditeur</option>
			<c:forEach items="${editeurs}" var="editeur">
				<option value="${editeur.id}">${editeur.nom}</option>
			</c:forEach>
		</select><br>
		
		<label for="genre_id">Genre</label> 
		<select name="genre">
			<option value="0">Merci de choisir un genre</option>
			<c:forEach items="${genres}" var="genre">
				<option value="${genre.id}">${genre.nom}</option>
			</c:forEach>
		</select><br>
		
		<label for="modeleEconomique_id">Modele Economique</label> 
		<select name="modeleEconomique">
			<option value="0">Merci de choisir un modèle économique</option>
			<c:forEach items="${modeleEconomiques}" var="modeleEconomique">
				<option value="${modeleEconomique.id}">${modeleEconomique.nom}</option>
			</c:forEach>
		</select><br>
		
		<label for="plateforme_id">Plateforme(s)</label> 
		<select name="plateformes" multiple >
			<option value="0">Merci de choisir une plateforme</option>
			<c:forEach items="${plateformes}" var="plateforme">
				<option value="${plateforme.id}">${plateforme.nom}</option>
			</c:forEach>
		</select><br>
		
	 <input class="button" type="submit" value="Ajouter">
	</form>
</body>
</html>