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

import models.EntidadeTipo;
import models.Client.Cliente;
import models.Client.TipoCliente;
import models.Entrega.Entrega;
import models.Product.Produto;

@WebServlet("/registrarEntrega")
public class RegistrarEntrega extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			String cpfCnpj = request.getParameter("cpfCnpj");
			TipoCliente tipoCliente = TipoCliente.valueOf(request.getParameter("tipoCliente"));
			int idProduto = Integer.parseInt(request.getParameter("idProduto"));
			
			DatabaseController db = new DatabaseController();
			Connection con = new ConnectionController().ConnectToDatabase();
			
			Cliente cliente = (Cliente)db.SelectFromParam(con, EntidadeTipo.CLIENTE, "cpfCnpj", cpfCnpj);
			cliente.setTipoCliente(tipoCliente);
			
			Produto produto = (Produto)db.SelectFromParam(con, EntidadeTipo.PRODUTO, "id", String.valueOf(idProduto));
			produto.setId(idProduto);
			
			Entrega entrega = new Entrega(cliente, produto);
			
			db.Insert(con, entrega, EntidadeTipo.ENTREGA);
			response.sendRedirect("listaEntregas");
			
		} catch (NullPointerException e) {
			if (e.getMessage().contains("cpfcnpj"))
			{
				request.setAttribute("mensagemErro", "Cliente não encontrado na base de dados, por favor verifique o CPF/CNPJ");
        		RequestDispatcher rd = request.getRequestDispatcher("/erro.jsp");
        		
        		rd.forward(request, response);
			} 
			
			else if(e.getMessage().contains("produto")) 
			{	
        		request.setAttribute("mensagemErro", "Produto não existe, por favor verifique o ID");
        		RequestDispatcher rd = request.getRequestDispatcher("/erro.jsp");
        		
        		rd.forward(request, response);
        	}
		
		}catch (Exception e) {
			System.out.println(e);
		}
	}

}
