package controllers;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.EntidadeTipo;
import models.Client.Cliente;
import models.Client.TipoCliente;
import models.Client.TipoPessoa;
import models.Endereco.Endereco;

@WebServlet("/registrarCliente")
public class RegistrarCliente extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
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
        
        try {
        	Endereco endereco = new Endereco(logradouro, numero, bairro, cidade, pais, uf, cep, complemento, pontoDeReferencia);
            Cliente client = new Cliente(razaoSocial, cpfCnpj, telefone, nomeFantasia, endereco, tipoCliente, tipoPessoa);
            Connection con = new ConnectionController().ConnectToDatabase();
            DatabaseController db = new DatabaseController();
                
            db.Insert(con, endereco, EntidadeTipo.ENDERECO);
            
            db.Insert(con, client, EntidadeTipo.CLIENTE);

            response.sendRedirect("listaClientes");

        } catch (IllegalArgumentException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Tipo de pessoa ou cliente inválido.");

        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Erro interno ao registrar cliente.");
        }
    }
}
