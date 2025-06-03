package controllers;

import java.io.IOException;
import java.sql.Connection;

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
			
			Entrega entrega = new Entrega(destinatario, remetente, produto, endereco);
			
			entregaDAO.insert(con, entrega);
			
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