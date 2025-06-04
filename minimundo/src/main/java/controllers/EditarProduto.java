package controllers;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import daos.DAOFactory;
import enums.EntidadeTipo;
import interfaces.EntidadeDAO;
import models.Produto;

@WebServlet("/editarProduto")
public class EditarProduto extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try (Connection con = new ConnectionController().connectToDatabase()) {
			int productId = Integer.valueOf(request.getParameter("id"));
			
			EntidadeDAO<Produto> produtoDAO = DAOFactory.getDAO(EntidadeTipo.PRODUTO);
			Produto produto = produtoDAO.findById(con, productId);
			produto.setId(productId);
			
			request.setAttribute("produto", produto);
			request.getRequestDispatcher("/editarProduto.jsp").forward(request, response);
		} catch (SQLException e) {
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Erro ao buscar o produto: " + e.getMessage());
		}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try (Connection con = new ConnectionController().connectToDatabase()) {
			int idProduct = Integer.parseInt(request.getParameter("id"));
						
			String nome = request.getParameter("nome");
			String descricao = request.getParameter("descricao");
			int estoque = Integer.parseInt(request.getParameter("estoque"));
			double preco = Double.parseDouble(request.getParameter("preco"));
			double peso = Double.parseDouble(request.getParameter("peso"));

			EntidadeDAO<Produto> produtoDAO = DAOFactory.getDAO(EntidadeTipo.PRODUTO);
			
			Produto produtoUpdate = new Produto(nome, descricao, estoque, preco, peso);
			produtoUpdate.setId(idProduct);

			produtoDAO.update(con, idProduct, produtoUpdate);

			response.sendRedirect("listaProdutos");

		} catch (NumberFormatException e) {
			request.setAttribute("mensagemErro", "Verifique os campos e tente novamente");
    		RequestDispatcher rd = request.getRequestDispatcher("/erro.jsp");	

    		rd.forward(request, response);
    	} catch (IllegalArgumentException e) {
			request.setAttribute("mensagemErro", "Verifique os campos e tente novamente");
    		RequestDispatcher rd = request.getRequestDispatcher("/erro.jsp");
    		
    		rd.forward(request, response);
		} catch (Exception e) {
			request.setAttribute("mensagemErro", "Erro inesperado " + e.getMessage());
    		RequestDispatcher rd = request.getRequestDispatcher("/erro.jsp");
    		
    		rd.forward(request, response);
    	}
	}

}
