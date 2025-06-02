package controllers;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import enums.EntidadeTipo;

@WebServlet("/deletar")
public class Deletar extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int insertableId = Integer.valueOf(request.getParameter("id"));
		EntidadeTipo tableName = EntidadeTipo.valueOf(request.getParameter("table"));
		int enderecoId = 0;
		
		Connection con = new ConnectionController().ConnectToDatabase();
		DatabaseController db = new DatabaseController();
		
		if (tableName == EntidadeTipo.CLIENTE)
		{
			enderecoId = Integer.valueOf(request.getParameter("id2"));
			db.Delete(con, tableName, insertableId);
			db.Delete(con, EntidadeTipo.ENDERECO, enderecoId);
			response.sendRedirect("listaClientes");
		}
		
		else if (tableName == EntidadeTipo.PRODUTO)
		{
			db.Delete(con, tableName, insertableId);
			response.sendRedirect("listaProdutos");
		}

		else if (tableName == EntidadeTipo.ENTREGA)
		{
			db.Delete(con, tableName, insertableId);
			response.sendRedirect("listaEntregas");
		}
	}
}
