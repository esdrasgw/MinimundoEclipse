<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:url value="/registrarProduto" var="linkServletNovoProduto"/>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Registrar Novo Produto</title>
</head>
<body>
	<form action="${linkServletNovoProduto}" method="post">
		<p>Nome: <input type="text" name="nome" /></p>
		<p>Descrição: <input type="text" name="descricao" /></p>
		<p>Estoque: <input type="text" name="estoque" /></p>
		<p>Preço: <input type="text" name="preco" /></p>
		<p>Peso: <input type="text" name="peso" /></p>

		<p><input type="submit" value="Enviar" /></p>
		
	</form>
	
	<a href = "index.html">Voltar ao início</a>
</body>
</html>
