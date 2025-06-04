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

@WebServlet("/registrarProduto")
public class RegistrarProduto extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
    	try (Connection con = new ConnectionController().connectToDatabase()) {

    		String nome = request.getParameter("nome");
	        String descricao = request.getParameter("descricao");
	        int estoque = Integer.parseInt(request.getParameter("estoque"));
	        double preco = Double.parseDouble(request.getParameter("preco"));
	        double peso = Double.parseDouble(request.getParameter("peso"));
	        
	        EntidadeDAO<Produto> produtoDAO = DAOFactory.getDAO(EntidadeTipo.PRODUTO);
			Produto produto = new Produto(nome, descricao, estoque, preco, peso);
			
			produtoDAO.insert(con, produto);
			
			response.sendRedirect("listaProdutos");
			
	    } catch (NumberFormatException e) {
	    	
	    	if (e.getMessage().contains(","))
	    	{
	    		request.setAttribute("mensagemErro", "Por favor use '.' ao invés de ',' para os decimais.");
	    		RequestDispatcher rd = request.getRequestDispatcher("/erro.jsp");
	    		
	    		rd.forward(request, response);
	    	}
	    	
    		request.setAttribute("mensagemErro", "Os campos Estoque, Preço e Peso só aceitam números");
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
