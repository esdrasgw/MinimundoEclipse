package controllers;

import java.io.IOException;
import java.sql.Connection;

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

@WebServlet("/entregar")
public class Entregar extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try (Connection con = new ConnectionController().connectToDatabase()) {
			int entregaId = Integer.valueOf(request.getParameter("id"));
			
			EntidadeDAO<Entrega> entregaDAO = DAOFactory.getDAO(EntidadeTipo.ENTREGA);
			Entrega entrega = entregaDAO.findById(con, entregaId);
			
			entrega.setProdutoEntregue(true);
			entregaDAO.update(con, entregaId, entrega);
			
			response.sendRedirect("listaEntregas");
			
		} catch (Exception e) {
			request.setAttribute("mensagemErro", "Erro ao entregar " + e.getMessage());
			RequestDispatcher rd = request.getRequestDispatcher("/erro.jsp");
    		
    		rd.forward(request, response);
		}
	}

}
