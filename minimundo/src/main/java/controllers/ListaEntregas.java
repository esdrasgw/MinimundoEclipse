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

import models.Entidade;
import models.EntidadeTipo;
import models.Client.Cliente;
import models.Client.TipoCliente;
import models.Entrega.Entrega;

@WebServlet("/listaEntregas")
public class ListaEntregas extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DatabaseController db = new DatabaseController();
		Connection con = new ConnectionController().ConnectToDatabase(); 
		
		try {
			List<Entidade> listEntregas = db.SelectAllFromTable(con, EntidadeTipo.ENTREGA);
			List<Entidade> listCliente = db.SelectAllFromTable(con, EntidadeTipo.CLIENTE);
			
			List<Entidade> listDestinatarios = new ArrayList<Entidade>();
			List<Entidade> listRemetente = new ArrayList<Entidade>();
			
			for (int i = 0; i < listCliente.size(); i++)
			{
				Cliente cliente = (Cliente)listCliente.get(i);
				
				if (cliente.getTipoCliente() == TipoCliente.DESTINATARIO) listDestinatarios.add(cliente);
				else if (cliente.getTipoCliente() == TipoCliente.REMETENTE) listRemetente.add(cliente);
			}
			
			request.setAttribute("listaEntregas", listEntregas);
			request.setAttribute("listaDestinatarios", listDestinatarios);
			request.setAttribute("listaRemetente", listRemetente);
			
			RequestDispatcher rd = request.getRequestDispatcher("/listaEntregas.jsp");
			rd.forward(request, response);
			
			} catch (NullPointerException e){
					
				request.setAttribute("mensagemErro", "Erro");
				RequestDispatcher rd = request.getRequestDispatcher("/erro.jsp");
				
				rd.forward(request, response);
				
				System.out.println(e.getMessage());
        	}
		}
	
	}
