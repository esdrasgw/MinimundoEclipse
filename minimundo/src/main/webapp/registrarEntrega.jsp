<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:url value="/registrarEntrega" var="linkServletNovaEntrega"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Nova Entrega</title>
</head>
<body>
	<form action="${linkServletNovaEntrega}" method="post">
		<p>Insira o CPF/CNPJ do Destinatário: <input type="text" name="destinatario"/></p>
		<p>Insira o CPF/CNPJ do Remetente: <input type="text" name="remetente"/></p>
		<p>Insira o ID do Produto: <input type="text" name="produto"/></p>
	
		<p><input type="submit" value="Enviar"/></p>		
	</form>
	
	<a href = "index.html">Voltar ao início</a>
	
</body>
</html>