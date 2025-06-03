<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="models.Cliente" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:url value="/editarCliente" var="linkServletEditarCliente"/>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Editar Cliente</title>
</head>
<body>
	<form action="${linkServletEditarCliente}" method="post">
		<p>Razão Social: <input type="text" name="razaoSocial" value="${cliente.razaoSocial}"/></p>
		<p>Nome Fantasia: <input type="text" name="nomeFantasia" value="${cliente.nomeFantasia}"/></p>
		<p>Pessoa Física ou Jurídica? <select name="tipoPessoa">
			<option value="FISICA" selected>Pessoa Física </option> 
			<option value="JURIDICA">Pessoa Jurídica</option>
		</select></p>
		<p>CPF/CNPJ: <input type=text name="cpfCnpj" value="${cliente.cpfCnpj}"/></p>
		<p>Telefone: <input type=text name="telefone" value="${cliente.telefone}"/></p>
		<p>Logradouro: <input type=text name="logradouro" value="${endereco.logradouro}"/></p>
		<p>Numero: <input type=text name="numero" value="${endereco.numero}"/></p>
		<p>Bairro: <input type=text name="bairro" value="${endereco.bairro}"/></p>
		<p>Cidade: <input type=text name="cidade" value="${endereco.cidade}"/></p>
		<p>UF: <input type=text name="uf" value="${endereco.UF}"/></p>
		<p>País: <input type=text name="pais" value="${endereco.pais}"/></p>
		<p>Cep: <input type=text name="cep" value="${endereco.cep}"/></p>
		<p>Complemento: <input type=text name="complemento" value="${endereco.complemento}"/></p>
		<p>Ponto de Referência: <input type=text name="pontoDeReferencia" value="${endereco.pontoDeReferencia}"/></p>
		
		<input type="hidden" name="id" value="${cliente.id}" />
		
		<p><input type="submit" value="Enviar"/></p>
	</form>
	
	<a href = "index.html">Voltar ao início</a>
</body>
</html>