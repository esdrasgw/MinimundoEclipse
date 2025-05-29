<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:url value="/registrarCliente" var="linkServletNovoCliente"/>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Registrar Novo Cliente</title>
</head>
<body>
	<form action="${linkServletNovoCliente}" method="post">
		<p>Razão Social: <input type="text" name="razaoSocial"/></p>
		<p>Nome Fantasia: <input type="text" name="nomeFantasia"/></p>
		<p>Pessoa Física ou Jurídica? <select name="tipoPessoa">
			<option value="FISICA">Pessoa Física </option> 
			<option value="JURIDICA">Pessoa Jurídica</option>
		</select></p>
		<p>CPF/CNPJ: <input type=text name="cpfCnpj"/></p>
		<p>Telefone: <input type=text name="telefone"/></p>
		<p>Logradouro: <input type=text name="logradouro"/></p>
		<p>Numero: <input type=text name="numero"/></p>
		<p>Bairro: <input type=text name="bairro"/></p>
		<p>Cidade: <input type=text name="cidade"/></p>
		<p>UF: <input type=text name="uf"/></p>
		<p>País: <input type=text name="pais"/></p>
		<p>Cep: <input type=text name="cep"/></p>
		<p>Complemento: <input type=text name="complemento"/></p>
		<p>Ponto de Referência: <input type=text name="pontoDeReferencia"/></p>
		<p><input type="submit" value="Enviar"/></p>
	</form>
	
	<a href = "index.html">Voltar ao início</a>
</body>
</html>