<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List,models.Cliente,models.Endereco" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Lista de Clientes</title>
</head>
<body>
	<c:if test="${ not empty cliente }">
		Cliente ${cliente.razaoSocial} cadastrado!
	</c:if>
	
	<h2>Lista de Clientes:</h2>

	<table border="1" cellpadding="5">
		<tr>
			<th>Razão Social</th>
			<th>CPF/CNPJ</th>
			<th>Telefone</th>
			<th>Nome Fantasia</th>
			<th>Tipo Pessoa</th>
			<th>Tipo Cliente</th>
			<th>Editar</th>
			<th>Deletar</th>
		</tr>
		<c:forEach items="${listaClientes}" var="cliente" varStatus="i">
			<tr>
				<td>${cliente.razaoSocial}</td>
				<td>${cliente.cpfCnpj}</td>
				<td>${cliente.telefone}</td>
				<td>${cliente.nomeFantasia}</td>
				<td>${cliente.tipoPessoa}</td>
				<td>${cliente.tipoCliente}</td>
				<td><a href = "editarCliente?id=${cliente.id}">Editar Cliente</a></td>
				<td><a href = "deletar?id=${cliente.id}&id2=${listaEndereco.get(i.index).getId()}&table=CLIENTE">Deletar Cliente</a></td>
			</tr>
		</c:forEach>
	</table>
	<table border="1" cellpadding="5">
		<tr>
			<th>Logradouro</th>
			<th>Numero</th>
			<th>Bairro</th>
			<th>Cidade</th>
			<th>UF</th>
			<th>Pais</th>
			<th>Cep</th>
			<th>Complemento</th>
			<th>Ponto De Referencia</th>
		</tr>
		<c:forEach items="${listaEndereco}" var="endereco">
			<tr>
				<td>${endereco.logradouro}</td>
				<td>${endereco.numero}</td>
				<td>${endereco.bairro}</td>
				<td>${endereco.cidade}</td>
				<td>${endereco.UF}</td>
				<td>${endereco.pais}</td>
				<td>${endereco.cep}</td>
				<td>${endereco.complemento}</td>
				<td>${endereco.pontoDeReferencia}</td>
			</tr>			
		</c:forEach> 
	</table>
	
	<a href = "index.html">Voltar ao início</a>
	
</body>
</html>
