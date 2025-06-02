package controllers;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import enums.EntidadeTipo;
import models.Cliente;
import models.Endereco;
import models.Entrega;
import models.Produto;

@WebServlet("/registrarEntrega")
public class RegistrarEntrega extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			DatabaseController db = new DatabaseController();
			Connection con = new ConnectionController().ConnectToDatabase();
			
			String destinatarioCpfCnpj = request.getParameter("destinatario");
			String remetenteCpfCnpj = request.getParameter("remetente");
			int produtoId = Integer.parseInt(request.getParameter("produto"));
			
			Cliente destinatario = (Cliente)db.SelectFromParam(con, EntidadeTipo.CLIENTE, "cpfCnpj", destinatarioCpfCnpj);
			Cliente remetente = (Cliente)db.SelectFromParam(con, EntidadeTipo.CLIENTE, "cpfCnpj", remetenteCpfCnpj);
			Endereco endereco = destinatario.getEndereco();
			Produto produto = (Produto)db.SelectFromParam(con, EntidadeTipo.PRODUTO, "id", String.valueOf(produtoId));
			
			Entrega entrega = new Entrega(destinatario, remetente, produto, endereco);
			
			db.Insert(con, entrega, EntidadeTipo.ENTREGA);
			
			response.sendRedirect("listaEntregas");
			
		} catch (NullPointerException e) {

    		request.setAttribute("mensagemErro", "Todos os campos são necessários");
    		RequestDispatcher rd = request.getRequestDispatcher("/erro.jsp");
    		
    		rd.forward(request, response);
		} catch (IllegalArgumentException e) {
	    	if (e.getMessage().contains("produto")) {
        		
        		request.setAttribute("mensagemErro", "O campo ID do Produto só aceita números");
        		RequestDispatcher rd = request.getRequestDispatcher("/erro.jsp");
        		
        		rd.forward(request, response);
        	}
		}  catch (Exception e) {
			request.setAttribute("mensagemErro", e.getMessage());
    		RequestDispatcher rd = request.getRequestDispatcher("/erro.jsp");
    		
    		rd.forward(request, response);
		}
	}
}