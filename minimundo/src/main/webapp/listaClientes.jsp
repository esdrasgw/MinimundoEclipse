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

	<table border="1">
    <thead>
        <tr>
            <th>Razão Social</th>
            <th>CPF/CNPJ</th>
            <th>Telefone</th>
            <th>Nome Fantasia</th>
            <th>Tipo Pessoa</th>
            <th>Tipo Cliente</th>
            <th>Logradouro</th>
            <th>Número</th>
            <th>Bairro</th>
            <th>Cidade</th>
            <th>UF</th>
            <th>País</th>
            <th>CEP</th>
            <th>Complemento</th>
            <th>Ponto de Referência</th>
            <th>Editar</th>
            <th>Deletar</th>
        </tr>
    </thead>
    <tbody>
        <c:forEach items="${listaClientesEnderecos}" var="lista">
		    <tr>
		        <td>${lista.cliente.razaoSocial}</td>
		        <td>${lista.cliente.cpfCnpj}</td>
		        <td>${lista.cliente.telefone}</td>
		        <td>${lista.cliente.nomeFantasia}</td>
		        <td>${lista.cliente.tipoPessoa}</td>
		        <td>${lista.cliente.tipoCliente}</td>
		
		        <td>${lista.endereco.logradouro}</td>
		        <td>${lista.endereco.numero}</td>
		        <td>${lista.endereco.bairro}</td>
		        <td>${lista.endereco.cidade}</td>
		        <td>${lista.endereco.UF}</td>
		        <td>${lista.endereco.pais}</td>
		        <td>${lista.endereco.cep}</td>
		        <td>${lista.endereco.complemento}</td>
		        <td>${lista.endereco.pontoDeReferencia}</td>
		
		        <td><a href="editarCliente?id=${lista.cliente.id}">Editar</a></td>
		        <td><a href="deletar?id=${lista.cliente.id}&table=CLIENTE&id2=${lista.endereco.id}">Deletar</a></td>
		    </tr>
		</c:forEach>
    </tbody>
</table>
	
	<a href = "index.html">Voltar ao início</a>
	
</body>
</html>
