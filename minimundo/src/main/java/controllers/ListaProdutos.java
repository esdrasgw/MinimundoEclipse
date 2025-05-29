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

import models.Entidade;
import models.EntidadeTipo;

@WebServlet("/listaProdutos")
public class ListaProdutos extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DatabaseController db = new DatabaseController();
		Connection con = new ConnectionController().ConnectToDatabase(); 
		
		List<Entidade> list = db.SelectAllFromTable(con, EntidadeTipo.PRODUTO);
		
		request.setAttribute("listaProdutos", list);
		
		RequestDispatcher rd = request.getRequestDispatcher("/listaProdutos.jsp");
		rd.forward(request, response);
	}
}
