<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<c:url value="/registrarEntrega" var="linkServletNovaEntrega"/>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Nova Entrega</title>
</head>
<body>
	<form action="${linkServletNovaEntrega}" method="post">
		<p>Insira seu CPF ou CNPJ <input type="text" name="cpfCnpj"/></p>		
		<p>Está recebendo ou enviando? <select name="tipoCliente">
			<option value="DESTINATARIO">Recebendo </option>
			<option value="REMETENTE">Enviando </option>
		</select></p>
		<p>Insira o ID do seu produto: <input type="text" name="idProduto"/></p>
		
		<p><input type="submit" value="Enviar"/></p>
	</form>
	
	<a href = "index.html">Voltar ao início</a>
</body>
</html>