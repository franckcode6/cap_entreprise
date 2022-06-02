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
<title>Jeux ajout</title>
</head>
<body>
	<jsp:include page="header.jsp"></jsp:include>
	<main class="container my-5">
		<c:if test="${jeu eq null}">
			<h1>Ajouter un nouveau jeu</h1>
		</c:if>
		<c:if test="${jeu ne null}">
			<h1>Modifier ${jeu.nom}</h1>
		</c:if>
		<form action="" method="post" enctype="multipart/form-data"
			class="my-4">
			<input type="hidden" name="id" value="${jeu.id}"><br>

			<div class="mb-3">
				<label for="nom" class="form-label">Nom</label> <input name="nom"
					class="form-control" value="${jeu.nom}"><br>
			</div>

			<div class="mb-3">
				<label for="description" class="form-label">Description</label>
				<textarea name="description" class="form-control">${jeu.description}</textarea>
				<br>
			</div>

			<div class="mb-3">
				<label for="dateForm" class="form-label">Date de sortie</label> <input
					type="date" name="dateSortie" class="form-control"
					value="${jeu.dateSortie}"><br>
			</div>

			<div class="mb-3">
				<label for="image" class="form-label">Téléverser une image</label> <input
					type="file" name="image" accept="image" placeholder="URL"
					class="form-control"><br>
			</div>

			<div class="mb-3">
				<label for="classification_id" class="form-label">Classification</label>
				<select name="classification" class="form-select">
					<c:if test="${jeu eq null}">
						<option value="0">Merci de choisir une classification</option>
					</c:if>
					<c:if test="${jeu ne null}">
						<option value="${jeu.classification.id}">${jeu.classification.nom}</option>
					</c:if>
					<c:forEach items="${classifications}" var="classification">
						<option value="${classification.id}">${classification.nom}</option>
					</c:forEach>
				</select><br>
			</div>

			<div class="mb-3">
				<label for="editeur_id" class="form-label">Editeur</label> <select
					name="editeur" class="form-select">
					<c:if test="${jeu eq null}">
						<option value="0">Merci de choisir un éditeur</option>
					</c:if>
					<c:if test="${jeu ne null}">
						<option value="${jeu.editeur.id}">${jeu.editeur.nom}</option>
					</c:if>
					<c:forEach items="${editeurs}" var="editeur">
						<option value="${editeur.id}">${editeur.nom}</option>
					</c:forEach>
				</select><br>
			</div>

			<div class="mb-3">
				<label for="genre_id" class="form-label">Genre</label> <select
					name="genre" class="form-select">
					<c:if test="${jeu eq null}">
						<option value="0">Merci de choisir un genre</option>
					</c:if>
					<c:if test="${jeu ne null}">
						<option value="${jeu.genre.id}">${jeu.genre.nom}</option>
					</c:if>
					<c:forEach items="${genres}" var="genre">
						<option value="${genre.id}">${genre.nom}</option>
					</c:forEach>
				</select><br>
			</div>

			<div class="mb-3">
				<label for="modeleEconomique_id" class="form-label">Modele
					Economique</label> <select name="modeleEconomique" class="form-select">
					<c:if test="${jeu eq null}">
						<option value="0">Merci de choisir un modèle économique</option>
					</c:if>
					<c:if test="${jeu ne null}">
						<option value="${jeu.modeleEconomique.id}">${jeu.modeleEconomique.nom}</option>
					</c:if>
					<c:forEach items="${modeleEconomiques}" var="modeleEconomique">
						<option value="${modeleEconomique.id}">${modeleEconomique.nom}</option>
					</c:forEach>
				</select><br>
			</div>

			<c:if test="${jeu eq null}">
				<div class="mb-3">
					<label for="plateforme_id">Plateforme(s)</label> <select
						name="plateformes" multiple class="form-select">
						<c:forEach items="${plateformes}" var="plateforme">
							<option value="${plateforme.id}">${plateforme.nom}</option>
						</c:forEach>
					</select><br>
				</div>
			</c:if>
			<c:if test="${jeu eq null}">
				<input class="btn btn-success" type="submit" value="Ajouter">
			</c:if>
			<c:if test="${jeu ne null}">
				<input class="btn btn-warning" type="submit" value="Modifier">
			</c:if>
			<a href="/admin/jeux" class="btn btn-secondary">Retour à la liste</a>
		</form>
	</main>
	<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>