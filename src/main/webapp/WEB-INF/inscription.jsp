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
	
	<main class="container mt-5">
	<form class="mt-5" action="inscription" method="post">

		<div class="mb-3">
			<label class="form-label">Pseudo</label> <input class="form-control"
				name="pseudo">
		</div>

		<div class="mb-3">
			<label class="form-label">Email</label>
			 <input class="form-control"
				type="email" name="email">
		</div>
		
		<div class="mb-3">
			<label class="form-label">Mot de passe</label> <input
				class="form-control" type="password" name="motDePasse">
		</div>
		
		<div class="mb-3">
			<label class="form-label">Date de Naissance</label> <input
				class="form-control" type="date" name="dateDeNaissance">
		</div>
		
		<input class="btn btn-success" type="submit" value="Inscription">
	</form>
	</main>
</body>
</html>