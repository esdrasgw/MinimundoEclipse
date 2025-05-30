package controllers;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.EntidadeTipo;
import models.Product.Produto;

@WebServlet("/registrarProduto")
public class RegistrarProduto extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
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
			
			Produto produto = new Produto(nome, descricao, estoque, preco, peso);
			
			db.Insert(con, produto, EntidadeTipo.PRODUTO);
			
			response.sendRedirect("listaProdutos");
			
	    } catch (IllegalArgumentException e) {
	    	if (e.getMessage().contains("estoque") || e.getMessage().contains("preco") || e.getMessage().contains("peso")) {
        		
        		request.setAttribute("mensagemErro", "Os campos Estoque, Preço e Peso só aceitam números");
        		RequestDispatcher rd = request.getRequestDispatcher("/erro.jsp");
        		
        		rd.forward(request, response);
        	}
		}
		
		catch (Exception e)
		{
	    	System.out.println(e);
		}
	}
}
