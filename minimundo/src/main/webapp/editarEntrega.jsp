<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:url value="/editarEntrega" var="linkServletEditarEntrega"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Nova Entrega</title>
</head>
<body>
	<form action="${linkServletEditarEntrega}" method="post">
		<p>Insira o CPF/CNPJ do Destinatário: <input type="text" name="destinatario" value="${entrega.destinatario.cpfCnpj}"/></p>
		<p>Insira o CPF/CNPJ do Remetente: <input type="text" name="remetente" value="${entrega.remetente.cpfCnpj}"/></p>
		<p>Insira o ID do Produto: <input type="text" name="produto" value="${entrega.produto.idProduto}"/></p>
		Já foi entregue?                   
		<input type="radio" value="true" name="produtoEntregue"/>Sim
		<input type="radio" value="false" name="produtoEntregue"/>Não
		
		<p><input type="submit" value="Enviar"/></p>
		
		<input type="hidden" name="id" value="${entrega.id}" />
	</form>
	
	<a href = "index.html">Voltar ao início</a>
	
</body>
</html>