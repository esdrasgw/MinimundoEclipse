<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:url value="/registrarEntrega" var="linkServletNovaEntrega"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Nova Entrega</title>
</head>
<body>
<a href = "index.html">Voltar ao início</a>
<a href = "listaEntregas">Lista de entregas</a>
	<form action="${linkServletNovaEntrega}" method="post">
		<p>Insira o CPF/CNPJ do Destinatário: <input type="text" name="destinatario"/></p>
		<p>Insira o CPF/CNPJ do Remetente: <input type="text" name="remetente"/></p>
		<p>Selecione o produto 
			<select name="produto">
				<c:forEach items="${listaProdutos}" var="produto">
					<option value="${produto.idProduto}">${produto.nome}</option>
				</c:forEach>
			</select>
		</p>
		<p>Quantidade comprada: <input type="number" name="quantidadeComprada"/></p>
		Produto Entregue?             
		<input type="radio" value="true" name="produtoEntregue"/>Sim
		<input checked type="radio" value="false" name="produtoEntregue"/>Não
		
		<p><input type="submit" value="Enviar"/></p>
		
	</form>
</body>
</html>