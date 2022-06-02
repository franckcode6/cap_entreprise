<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head> <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet">
<meta charset="ISO-8859-1"></head>

<body >
	<jsp:include page="header.jsp"></jsp:include>
<main class="container"> 
	<c:if test="${param.notification ne null}">
		<h2>${param.notification}</h2>
	</c:if>

	<form class="mb-3" action="" method="post">
		<div class="mb-3">
			<label class="form-label text-uppercase fs-3 " for="pseudo">Pseudo</label> <input
				class="form-control" name="pseudo" placeHolder="pseudo" required>
		</div>

		<div class="mb-3">
			<label class="form-label text-uppercase fs-3" for="motDePasse">Mot de passe</label> <input
				class="form-control" type="password" name="motDePasse"
				placeHolder="Mot de Passe" required>
		</div>
		<div class="d-flex justify-content-between">
			<input class="btn btn-success" type="submit" value="Connexion">
			<a class="btn btn-dark" href="inscription">S'inscrire</a>
		</div>
	</form>
</main>
<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>