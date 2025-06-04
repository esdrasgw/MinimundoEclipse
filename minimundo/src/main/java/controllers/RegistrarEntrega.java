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

import daos.ClienteDAO;
import daos.DAOFactory;
import enums.EntidadeTipo;
import interfaces.EntidadeDAO;
import models.Cliente;
import models.Endereco;
import models.Entrega;
import models.Produto;

@WebServlet("/registrarEntrega")
public class RegistrarEntrega extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
    	try (Connection con = new ConnectionController().connectToDatabase()) {
			
			String destinatarioCpfCnpj = request.getParameter("destinatario");
			String remetenteCpfCnpj = request.getParameter("remetente");
			int produtoId = Integer.parseInt(request.getParameter("produto"));
			boolean produtoEntregue = Boolean.parseBoolean(request.getParameter("produtoEntregue"));
			
			EntidadeDAO<Cliente> dao = DAOFactory.getDAO(EntidadeTipo.CLIENTE);
			ClienteDAO clienteDAO = null;
			if (dao instanceof ClienteDAO) {
				clienteDAO = (ClienteDAO) dao;
			}
			EntidadeDAO<Produto> produtoDAO = DAOFactory.getDAO(EntidadeTipo.PRODUTO);
			EntidadeDAO<Entrega> entregaDAO = DAOFactory.getDAO(EntidadeTipo.ENTREGA);
			
			Cliente destinatario = clienteDAO.findByCpfCnpj(con, destinatarioCpfCnpj);
			Cliente remetente = clienteDAO.findByCpfCnpj(con, remetenteCpfCnpj);
			Endereco endereco = destinatario.getEndereco();
			Produto produto = produtoDAO.findById(con, produtoId);
			
			Entrega entrega = new Entrega(destinatario, remetente, produto, endereco, produtoEntregue);
			
			entregaDAO.insert(con, entrega);
			
			response.sendRedirect("listaEntregas");
			
		} catch (NullPointerException e) {
			if (e.getMessage().contains("destinatario")) {
				request.setAttribute("mensagemErro", "O CPF/CNPJ do Destinatario não foi encontrado");
			}
			else if (e.getMessage().contains("getRemetente")) {
				request.setAttribute("mensagemErro", "O CPF/CNPJ do Remetente não foi encontrado");
			}
			else if (e.getMessage().contains("getProduto")) {
				request.setAttribute("mensagemErro", "O ID do produto não foi encontrado");
			}
			else 
			{
	    		request.setAttribute("mensagemErro", "Todos os campos são necessários");
			}
    		RequestDispatcher rd = request.getRequestDispatcher("/erro.jsp");
    		rd.forward(request, response);
  		
		} catch (IllegalArgumentException e) {
	    	if (e.getMessage().contains("string")) {
        		
        		request.setAttribute("mensagemErro", "Os campos CPF/CNPJ e ID do Produto só aceitam números");
        		RequestDispatcher rd = request.getRequestDispatcher("/erro.jsp");
        		
        		rd.forward(request, response);
        	
        	} else {
        		request.setAttribute("mensagemErro", e.getMessage());
        		RequestDispatcher rd = request.getRequestDispatcher("/erro.jsp");
        		
        		rd.forward(request, response);
        	}
		} catch (Exception e) {
			request.setAttribute("mensagemErro", "Erro inesperado " + e.getMessage());
    		RequestDispatcher rd = request.getRequestDispatcher("/erro.jsp");
    		
    		rd.forward(request, response);
		}
	}
}