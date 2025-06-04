package controllers;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

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

@WebServlet("/listaProdutos")
public class ListaProdutos extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try (Connection con = new ConnectionController().connectToDatabase()) {
			
			EntidadeDAO<Produto> produtoDAO = DAOFactory.getDAO(EntidadeTipo.PRODUTO);
			
			List<Produto> listaProdutos = produtoDAO.findAll(con);
			
			request.setAttribute("listaProdutos", listaProdutos);
			
			RequestDispatcher rd = request.getRequestDispatcher("/listaProdutos.jsp");
			rd.forward(request, response);	
		} catch (Exception e) {
			request.setAttribute("mensagemErro", "Erro inesperado " + e.getMessage());
    		RequestDispatcher rd = request.getRequestDispatcher("/erro.jsp");
    		
    		rd.forward(request, response);
   		}
	}
}
