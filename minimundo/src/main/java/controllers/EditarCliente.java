package controllers;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import enums.EntidadeTipo;
import enums.TipoCliente;
import enums.TipoPessoa;
import models.Cliente;
import models.Endereco;

@WebServlet("/editarCliente")
public class EditarCliente extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int clientId = Integer.valueOf(request.getParameter("id"));

		Connection con = new ConnectionController().ConnectToDatabase();
		DatabaseController db = new DatabaseController();
		
		Cliente clienteGet = (Cliente)db.SelectFromParam(con, EntidadeTipo.CLIENTE, "id", String.valueOf(clientId));
		clienteGet.setId(clientId);
				
		request.setAttribute("cliente", clienteGet);
		request.setAttribute("endereco", clienteGet.getEndereco());
		request.getRequestDispatcher("/editarCliente.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			int idClient = Integer.parseInt(request.getParameter("id"));
			
			String razaoSocial = request.getParameter("razaoSocial");
			TipoPessoa tipoPessoa = TipoPessoa.valueOf(request.getParameter("tipoPessoa"));
			String cpfCnpj = request.getParameter("cpfCnpj");
			String telefone = request.getParameter("telefone");
			String nomeFantasia = request.getParameter("nomeFantasia");
			TipoCliente tipoCliente = TipoCliente.valueOf(request.getParameter("tipoCliente"));
			
			String logradouro = request.getParameter("logradouro");
	        int numero = Integer.valueOf(request.getParameter("numero"));
	        String bairro = request.getParameter("bairro");
	        String cidade = request.getParameter("cidade");
	        String uf = request.getParameter("uf");
	        String pais = request.getParameter("pais");
	        int cep = Integer.valueOf(request.getParameter("cep"));
	        String complemento = request.getParameter("complemento");
	        String pontoDeReferencia = request.getParameter("pontoDeReferencia");
			
			DatabaseController db = new DatabaseController();
			Connection con = new ConnectionController().ConnectToDatabase();
			
			Cliente oldCliente = (Cliente)db.SelectFromParam(con, EntidadeTipo.CLIENTE, "id", String.valueOf(idClient));
			
			Endereco enderecoUpdate = new Endereco(logradouro, numero, bairro, cidade, uf, pais, cep, complemento, pontoDeReferencia);
			enderecoUpdate.setId(oldCliente.getEndereco().getId());
			
			Cliente clienteUpdate = new Cliente(razaoSocial, cpfCnpj, telefone, nomeFantasia, enderecoUpdate, tipoCliente, tipoPessoa);
			clienteUpdate.setId(idClient);
			
			db.Update(con, enderecoUpdate.getId(), EntidadeTipo.ENDERECO, enderecoUpdate);
			db.Update(con, idClient, EntidadeTipo.CLIENTE, clienteUpdate);

			response.sendRedirect("listaClientes");

		} catch (NumberFormatException e) {
			response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Erro ao converter os parâmetros: " + e.getMessage());
		} catch (Exception e) {
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Erro interno do servidor: " + e.getMessage());
		}
	}

}
