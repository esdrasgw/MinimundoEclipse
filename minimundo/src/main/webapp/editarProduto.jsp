<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List,models.Produto" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<c:url value="/editarProduto" var="linkServletEditarProduto"/>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Atualizar Produto</title>
</head>
<body>
	<form action="${linkServletEditarProduto}" method="post">
		<p>Nome: <input type="text" name="nome" value="${produto.nome}"/></p>
		<p>Descrição: <input type="text" name="descricao" value="${produto.descricao}"/></p>
		<p>Estoque: <input type="text" name="estoque" value="${produto.estoque}"/></p>
		<p>Preço: <input type="text" name="preco" value="${produto.preco}"/></p>
		<p>Peso: <input type="text" name="peso" value="${produto.peso}"/></p>

		<input type="hidden" name="id" value="${produto.idProduto}" />

		<p><input type="submit" value="Enviar" /></p>
	</form>
		
	<a href = "index.html">Voltar ao início</a>
</body>
</html>