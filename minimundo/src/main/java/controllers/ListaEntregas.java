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

import daos.DAOFactory;
import enums.EntidadeTipo;
import interfaces.EntidadeDAO;
import models.Entrega;

@WebServlet("/listaEntregas")
public class ListaEntregas extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try (Connection con = new ConnectionController().connectToDatabase()) {
			
			EntidadeDAO<Entrega> entregaDAO = DAOFactory.getDAO(EntidadeTipo.ENTREGA);
			
			List<Entrega> listaEntregas = entregaDAO.findAll(con);
			
			request.setAttribute("listaEntregas", listaEntregas);
			
			RequestDispatcher rd = request.getRequestDispatcher("/listaEntregas.jsp");
			rd.forward(request, response);	
		} catch (Exception e) {
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Erro ao buscar: " + e.getMessage());
		}
	}
}