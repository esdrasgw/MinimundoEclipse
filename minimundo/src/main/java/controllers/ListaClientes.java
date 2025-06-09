package controllers;

import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;
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
import models.Cliente;
import models.ClienteEndereco;
import models.Endereco;

@WebServlet("/listaClientes")
public class ListaClientes extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try (Connection con = new ConnectionController().connectToDatabase()) {
			
			EntidadeDAO<Endereco> enderecoDAO = DAOFactory.getDAO(EntidadeTipo.ENDERECO);
			EntidadeDAO<Cliente> clienteDAO = DAOFactory.getDAO(EntidadeTipo.CLIENTE);
			
			List<Cliente> listaClientes = clienteDAO.findAll(con);
			List<ClienteEndereco> listaClienteEndereco = new ArrayList<>();
			
			for (Cliente cliente : listaClientes) {
				Endereco endereco = enderecoDAO.findById(con, cliente.getEndereco().getId());
				listaClienteEndereco.add(new ClienteEndereco(cliente, endereco));
			}
			
			request.setAttribute("listaClientesEnderecos", listaClienteEndereco);
			
			RequestDispatcher rd = request.getRequestDispatcher("/listaClientes.jsp");
			rd.forward(request, response);
			
		} catch (Exception e) {
			request.setAttribute("mensagemErro", "Erro inesperado " + e.getMessage());
    		RequestDispatcher rd = request.getRequestDispatcher("/erro.jsp");
    		
    		rd.forward(request, response);
   		}
	}
}
