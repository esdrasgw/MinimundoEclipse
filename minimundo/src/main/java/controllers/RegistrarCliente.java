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
import enums.TipoCliente;
import enums.TipoPessoa;
import interfaces.EntidadeDAO;
import models.Cliente;
import models.Endereco;

@WebServlet("/registrarCliente")
public class RegistrarCliente extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
    	try (Connection con = new ConnectionController().connectToDatabase()) {
    		
	    	String razaoSocial = request.getParameter("razaoSocial");
	        TipoPessoa tipoPessoa = TipoPessoa.valueOf(request.getParameter("tipoPessoa"));
	        String cpfCnpj = request.getParameter("cpfCnpj");
	        String telefone = request.getParameter("telefone");
	        TipoCliente tipoCliente = TipoCliente.NULO;
	        String nomeFantasia = request.getParameter("nomeFantasia");
	        
	        String logradouro = request.getParameter("logradouro");
	        int numero = Integer.valueOf(request.getParameter("numero"));
	        String bairro = request.getParameter("bairro");
	        String cidade = request.getParameter("cidade");
	        String uf = request.getParameter("uf");
	        String pais = request.getParameter("pais");
	        int cep = Integer.valueOf(request.getParameter("cep"));
	        String complemento = request.getParameter("complemento");
	        String pontoDeReferencia = request.getParameter("pontoDeReferencia");
	        
	        EntidadeDAO<Endereco> enderecoDAO = DAOFactory.getDAO(EntidadeTipo.ENDERECO);
	        EntidadeDAO<Cliente> clienteDAO = DAOFactory.getDAO(EntidadeTipo.CLIENTE);
        
        	Endereco endereco = new Endereco(logradouro, numero, bairro, cidade, pais, uf, cep, complemento, pontoDeReferencia);
            Cliente cliente = new Cliente(razaoSocial, cpfCnpj, telefone, nomeFantasia, endereco, tipoCliente, tipoPessoa);
                
            enderecoDAO.insert(con, endereco);
            clienteDAO.insert(con, cliente);

            response.sendRedirect("listaClientes");

        } catch(NumberFormatException e)
        {
    		request.setAttribute("mensagemErro", "Os campos Número e CEP só aceitam números");
    		RequestDispatcher rd = request.getRequestDispatcher("/erro.jsp");
    		
    		rd.forward(request, response);
        } catch(SQLException e) {
        	if (e.getMessage().contains("cpfcnpj")) {
        		
        		request.setAttribute("mensagemErro", "CPF ou CNPJ já existe");
        		RequestDispatcher rd = request.getRequestDispatcher("/erro.jsp");
        		
        		rd.forward(request, response);
        	}

        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Erro interno ao registrar cliente.");
        }
    }
}
