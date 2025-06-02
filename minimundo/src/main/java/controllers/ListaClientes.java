package controllers;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import enums.EntidadeTipo;
import models.Entidade;

@WebServlet("/listaClientes")
public class ListaClientes extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DatabaseController db = new DatabaseController();
		Connection con = new ConnectionController().ConnectToDatabase(); 
		
		List<Entidade> listClient = db.SelectAllFromTable(con, EntidadeTipo.CLIENTE);
		List<Entidade> listEndereco = db.SelectAllFromTable(con, EntidadeTipo.ENDERECO);
		
		request.setAttribute("listaEndereco", listEndereco);
		request.setAttribute("listaClientes", listClient);
		
		RequestDispatcher rd = request.getRequestDispatcher("/listaClientes.jsp");
		rd.forward(request, response);
	}
}
