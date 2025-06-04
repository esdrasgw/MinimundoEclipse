<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List,models.Entrega,models.Endereco" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Lista de Entregas</title>
</head>
<body>
	<h2>Lista de Entregas</h2>
	
	<table border="1" cellpadding="5">
		<tr>
			<th>Id da Entrega</th>
			<th>Destinatario</th>
			<th>CPF/CNPJ Destinatario</th>
			<th>Endereço de Entrega</th>
			<th>Remetente</th>
			<th>CPF/CNPJ Remetente</th>
			<th>ID Produto</th>
			<th>Produto</th>
			<th>Foi entregue?</th>
			<th>Editar</th>
			<th>Deletar</th>
		</tr>
		<c:forEach items="${listaEntregas}" var="entrega">
			<tr>
				<td>${entrega.id}</td>
				<td>${entrega.destinatario.razaoSocial}</td>
				<td>${entrega.destinatario.cpfCnpj}</td>
				<td>${entrega.enderecoEntrega}</td>
				<td>${entrega.remetente.razaoSocial}</td>
				<td>${entrega.remetente.cpfCnpj}</td>
				<td>${entrega.produto.idProduto}</td>
				<td>${entrega.produto.nome}</td>
				<c:choose>
					<c:when test="${entrega.produtoEntregue == true}">
						<td>Sim</td>
					</c:when>
					<c:otherwise>
						<td>Não</td>
					</c:otherwise>
				</c:choose>
				<td><a href = "editarEntrega?id=${entrega.id}">Editar Entrega</a></td>
				<td><a href = "deletar?id=${entrega.id}&table=ENTREGA">Deletar Entrega</a></td>
			</tr>
		</c:forEach>
	</table>
	
	<a href = "index.html">Voltar ao início</a>
</body>
</html>