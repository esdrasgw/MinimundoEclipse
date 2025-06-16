<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List,models.Produto" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Lista de Produtos</title>
<style>
	table {
		border-collapse: separate;
	}
	table th {
		padding: 5px;
	}
</style>
</head>
<body>
	<c:if test="${ not empty produto }">
		Produto ${produto.nome} cadastrado!
	</c:if>
	
	<h2>Lista de Produtos:</h2>

	<table border="1">
		<tr>
			<th>Id</th>
			<th>Nome</th>
			<th>Descrição</th>
			<th>Estoque</th>
			<th>Preço</th>
			<th>Peso</th>
			<th>Editar</th>
			<th>Deletar</th>
		</tr>
			
		<c:forEach items="${listaProdutos}" var="produto">
			<tr>
				<td>${produto.idProduto}</td>
				<td>${produto.nome}</td>
				<td>${produto.descricao}</td>
				<td>${produto.estoque}</td>
				<td>R$ ${produto.preco}</td>
				<td>${produto.peso} kg</td>
  				<td><a href = "editarProduto?id=${produto.idProduto}">Editar</a></td>
  				<td><a href = "deletar?id=${produto.idProduto}&table=PRODUTO">Deletar</a></td>
  				
			</tr>
		</c:forEach>
	</table>
	
	<p><a href = "registrarProduto.jsp">Registrar novo produto</a></p>
	<p><a href = "index.html">Voltar ao início</a></p>
</body>
</html>
