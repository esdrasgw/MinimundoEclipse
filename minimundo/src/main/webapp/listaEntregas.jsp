<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List,models.Client.Cliente,models.Endereco.Endereco,models.Product.Produto,models.Entrega.Entrega" %> 
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
			<th>Destinatário</th>
			<th>Remetente</th>
			<th>Produto</th>
			<th>Endereço de Entrega</th>
		</tr>
		<c:forEach items="${listaEntregas}" var="entrega" varStatus = "i">
			<tr>
				<td>
				<c:if test="${listaDestinatarios.size() > 0}">
					<c:forEach items="${listaDestinatarios.get(i.index).getCpfCnpj()}">
					</c:forEach></c:if>
				</td>
				<td>
				<c:if test="${listaRemetente.size() > 0}">
					<c:forEach items="${listaRemetente.get(i.index).getCpfCnpj()}">
					</c:forEach></c:if></td>
				<td>${entrega.produto}</td>
				<td>${entrega.endereco}</td>
				<td><a href = "editarEntrega?id=${entrega.id}">Editar Entrega</a></td>
				<td><a href = "deletarEntrega?id=${entrega.id}&table=ENTREGA">Deletar Entrega</a></td>
			</tr>
		</c:forEach>
	</table>
	
	<a href = "index.html">Voltar ao início</a>
	
</body>
</html>