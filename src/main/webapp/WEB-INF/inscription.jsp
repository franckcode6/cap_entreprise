<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Inscription</title>
</head>
<body>
	<h1>Inscription</h1>
	<form action="inscription" method="post">
		<label>Pseudo</label><input name="pseudo"><br> 
		<label>Email</label><input type="email" name="email"><br> 
		<label>Mot de passe</label><input type="password" name="motDePasse"><br> 
		<label>Date de Naissance</label><input type="date" name="dateDeNaissance"><br> 
		 <input class ="button" type="submit" value="inscription">
	</form>
</body>
</html>