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
        
        	Endereco endereco = new Endereco(logradouro, numero, bairro, cidade, uf, pais, cep, complemento, pontoDeReferencia);
            Cliente cliente = new Cliente(razaoSocial, cpfCnpj, telefone, nomeFantasia, endereco, tipoPessoa);
                
            enderecoDAO.insert(con, endereco);
            clienteDAO.insert(con, cliente);

            response.sendRedirect("listaClientes");
    	} catch (NullPointerException e){
    		request.setAttribute("mensagemErro", "Todos os campos são necessários");
    		RequestDispatcher rd = request.getRequestDispatcher("/erro.jsp");
    		
    		rd.forward(request, response);
    		
        } catch(NumberFormatException e) {
        	if (e.getMessage().contains("\"\"")) {
        		request.setAttribute("mensagemErro", "Todos os campos são necessários");
        		RequestDispatcher rd = request.getRequestDispatcher("/erro.jsp");
        		
        		rd.forward(request, response);	
        	} else {
	    		request.setAttribute("mensagemErro", "Os campos Número e CEP só aceitam números.");
	    		RequestDispatcher rd = request.getRequestDispatcher("/erro.jsp");
	    		
	    		rd.forward(request, response);
    		}
        } catch (IllegalArgumentException e) {
			request.setAttribute("mensagemErro", "Verifique os campos e tente novamente" + e + e.getMessage());
    		RequestDispatcher rd = request.getRequestDispatcher("/erro.jsp");
    		
    		rd.forward(request, response);	
        } catch(SQLException e) {
        	if (e.getMessage().contains("cpfcnpj")) {
        		
        		request.setAttribute("mensagemErro", "CPF ou CNPJ já existe");
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
