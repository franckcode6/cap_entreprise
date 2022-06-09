<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css"
	rel="stylesheet">
<title>Inscription</title>
</head>
<body>
	<jsp:include page="header.jsp"></jsp:include>

	<main class="container mt-5 min-vh-100">
		<form class="mt-5" action="inscription" method="post">

			<div class="mb-3">
				<label class="form-label"><strong>Pseudo</strong></label> <input class="form-control"
					name="pseudo">
			</div>

			<div class="mb-3">
				<label class="form-label"><strong>Email</strong></label> <input class="form-control"
					type="email" name="email">
			</div>

			<div class="mb-3">
				<label class="form-label"><strong>Mot de passe</strong></label> <input
					class="form-control" type="password" name="motDePasse">
			</div>

			<div class="mb-3">
				<label class="form-label"><strong>Date de Naissance</strong></label> <input
					class="form-control" type="date" name="dateDeNaissance">
			</div>

			<input class="btn btn-success" type="submit" value="Inscription">
			<a href="/" class="btn btn-secondary">Retour à la connexion</a>
		</form>
	</main>
	<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>