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
	<div class="form-wrapper">
	<form action="${linkServletEditarEntrega}" method="post">
		<p>Insira o CPF/CNPJ do Destinatário: <input type="text" name="destinatario" value="${entrega.destinatario.cpfCnpj}"/></p>
		<p>Insira o CPF/CNPJ do Remetente: <input type="text" name="remetente" value="${entrega.remetente.cpfCnpj}"/></p>
		<p>Selecione o produto 
			<select name="produto">
				<c:forEach items="${listaProdutos}" var="produto">
					<option value="${produto.idProduto}" selected="${entrega.produto.idProduto}">${produto.nome}</option>
				</c:forEach>
			</select>
		</p>
		Produto Entregue?
		<input type="radio" value="true" name="produtoEntregue"/>Sim
		<input type="radio" value="false" name="produtoEntregue"/>Não
		
		<p><input type="submit" value="Enviar"/></p>
		
		<input type="hidden" name="id" value="${entrega.id}" />
		
		<a href = "index.html">Voltar ao início</a>
	</form>
	</div>
	
</body>
</html>