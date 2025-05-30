<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Erro</title>
</head>
<body>
	<p>"${mensagemErro}"</p>
	
	<form action="index.html">
		<input type="submit" value="Voltar ao Início"/>
	</form>
</body>
</html>