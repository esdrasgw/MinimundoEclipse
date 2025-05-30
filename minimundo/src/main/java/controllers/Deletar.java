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

@WebServlet("/deletar")
public class Deletar extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int insertableId = Integer.valueOf(request.getParameter("id"));
		int enderecoId = Integer.valueOf(request.getParameter("id2"));
		EntidadeTipo tableName = EntidadeTipo.valueOf(request.getParameter("table"));
		
		Connection con = new ConnectionController().ConnectToDatabase();
		DatabaseController db = new DatabaseController();
		
		if (tableName == EntidadeTipo.CLIENTE)
		{
			db.Delete(con, tableName, insertableId);
			db.Delete(con, EntidadeTipo.ENDERECO, enderecoId);
			response.sendRedirect("listaClientes");
		}
		
		else if (tableName == EntidadeTipo.PRODUTO)
		{
			db.Delete(con, tableName, insertableId);
			response.sendRedirect("listaProdutos");
		}
	}
}
