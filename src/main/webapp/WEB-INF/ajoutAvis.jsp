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
<title>TITRE</title>
</head>
<body>
<jsp:include page="header.jsp"></jsp:include>
	<main class="container my-5">
	<h1>Ajouter un avis</h1>
	<form method="post" class="mt-4">

		<div class="mb-3">
			<label for="description" class="form-label">Description</label>
			<textarea name="description" class="form-control"></textarea>
			<br>
		</div>

		<div class="mb-3">
			<label class="form-label">Note</label> <input type="number"
				step="any" placeholder="../20" name="note" class="form-control"><br>
		</div>

		<div class="mb-3">
			<label class="form-label">Jeu</label> <select name="jeu"
				class="form-select">
				<c:forEach items="${jeux}" var="jeu">
					<option value="${jeu.id}">${jeu.nom}</option>
				</c:forEach>
			</select><br>
		</div>
		<input class="btn btn-danger" type="submit" value="Ajouter">
		<a href="/avis" class="btn btn-secondary">Retour à la liste</a>
	</form>
	</main>
</body>
</html>