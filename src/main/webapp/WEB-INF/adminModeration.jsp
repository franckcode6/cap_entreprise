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
<title>Modération</title>
</head>
<body>
	<jsp:include page="header.jsp"></jsp:include>
	<main class="container min-vh-100">
		<div class="container text-center mt-5">
			<div class="card mx-auto" style="width: 30rem">
				<div class="card-body">
					<h5 class="card-title">${avis.jeu.nom}</h5>
					<img src="/images/${avis.jeu.image}" height="150">
					<h6 class="card-subtitle mb-2 text-muted">${avis.note}/20-
						${avis.description}</h6>
					<p class="card-text">${avis.joueur.pseudo}-${avis.dateEnvoi}</p>
					<p class="card-text">${avis.moderateur.pseudo}-
						${avis.dateModeration}</p>
					<a href="admin/avis">Retour à la liste des avis</a>
				</div>
			</div>
		</div>
		<form method="post" class="mt-4">

			<div class="mb-3">
				<label for="description" class="form-label">Description</label>
				<textarea name="description" class="form-control">${avis.description}</textarea>
				<br>
			</div>

			<div class="mb-3">
				<label class="form-label">Note</label> <input type="number"
					step="any" value="${avis.note}" name="note" class="form-control"><br>
			</div>

			<div class="mb-3">
				<label class="form-label">Jeu</label> <select name="jeu"
					class="form-select">
					<c:forEach items="${jeux}" var="jeu">
						<option value="${jeu.id}">${avis.jeu.nom}</option>
					</c:forEach>
				</select><br>
			</div>
			<input class="btn btn-danger" type="submit" value="Ajouter">
			<a href="/admin/avis" class="btn btn-dark">Retour à la liste des
				avis</a> <a href="/admin" class="btn btn-secondary">Retour au
				Dashboard</a>
		</form>
	</main>
	<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>