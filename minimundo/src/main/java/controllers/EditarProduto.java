package controllers;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.EntidadeTipo;
import models.Product.Produto;

@WebServlet("/editarProduto")
public class EditarProduto extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int productId = Integer.valueOf(request.getParameter("id"));

		Connection con = new ConnectionController().ConnectToDatabase();
		DatabaseController db = new DatabaseController();
		
		Produto produtoGet = (Produto)db.SelectFromParam(con, EntidadeTipo.PRODUTO, "id", String.valueOf(productId));
		produtoGet.setId(productId);
		
		request.setAttribute("produto", produtoGet);
		request.getRequestDispatcher("/editarProduto.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			int idProduct = Integer.parseInt(request.getParameter("id"));
						
			String nome = request.getParameter("nome");
			String descricao = request.getParameter("descricao");

			if (nome == null || nome.isEmpty() || descricao == null || descricao.isEmpty()) {
				response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Nome e descrição são obrigatórios.");
				return;
			}

			int estoque = Integer.parseInt(request.getParameter("estoque"));
			double preco = Double.parseDouble(request.getParameter("preco"));
			double peso = Double.parseDouble(request.getParameter("peso"));

			DatabaseController db = new DatabaseController();
			Connection con = new ConnectionController().ConnectToDatabase();

			Produto produtoUpdate = new Produto(nome, descricao, estoque, preco, peso);
			produtoUpdate.setId(idProduct);

			db.Update(con, idProduct, EntidadeTipo.PRODUTO,produtoUpdate);

			response.sendRedirect("listaProdutos");

		} catch (NumberFormatException e) {
			response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Erro ao converter os parâmetros: " + e.getMessage());
		} catch (Exception e) {
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Erro interno do servidor: " + e.getMessage());
		}
	}

}
