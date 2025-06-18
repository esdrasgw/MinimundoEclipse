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
	<h2>Lista de Entregas</h2>
	
		<table border="1">
		<tr>
			<th>Id da Entrega</th>
			<th>Destinatario</th>
			<th>CPF/CNPJ Destinatario</th>
			<th>Endereço de Entrega</th>
			<th>Remetente</th>
			<th>CPF/CNPJ Remetente</th>
			<th>Endereço de Saída do Produto</th>
			<th>ID Produto</th>
			<th>Produto</th>
			<th>Quantidade Comprada</th>
			<th>Entregue?</th>
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
				<td>${entrega.enderecoRemetente}</td>
				<td>${entrega.produto.idProduto}</td>
				<td>${entrega.produto.nome}</td>
				<td>${entrega.quantidadeComprada}</td>
				<c:choose>
					<c:when test="${entrega.produtoEntregue == true}">
						<td>Sim</td>
					</c:when>
					<c:otherwise>
						<td>Não. <a href = "entregar?id=${entrega.id}">Entregar?</a></td>
					</c:otherwise>
				</c:choose>
				<td><a href = "editarEntrega?id=${entrega.id}">Editar</a></td>
				<td><a href = "deletar?id=${entrega.id}&table=ENTREGA">Deletar</a></td>
			</tr>
		</c:forEach>
	</table>
	
	<p><a href = "registrarEntrega">Registrar nova entrega</a></p>
	<p><a href = "index.html">Voltar ao início</a></p>
</body>
</html>