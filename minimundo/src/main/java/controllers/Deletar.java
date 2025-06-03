package controllers;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import daos.DAOFactory;
import enums.EntidadeTipo;
import interfaces.EntidadeDAO;

@WebServlet("/deletar")
public class Deletar extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try (Connection con = new ConnectionController().connectToDatabase()) {
			int entidadeId = Integer.parseInt(request.getParameter("id"));
			EntidadeTipo tipoEntidade = EntidadeTipo.valueOf(request.getParameter("table"));

			EntidadeDAO<?> daoPrincipal = DAOFactory.getDAO(tipoEntidade);
			daoPrincipal.delete(con, entidadeId);

			switch (tipoEntidade) {
				case CLIENTE:
					int enderecoId = Integer.parseInt(request.getParameter("id2"));
					EntidadeDAO<?> enderecoDAO = DAOFactory.getDAO(EntidadeTipo.ENDERECO);
					enderecoDAO.delete(con, enderecoId);
					response.sendRedirect("listaClientes");
					break;

				case PRODUTO:
					response.sendRedirect("listaProdutos");
					break;

				case ENTREGA:
					response.sendRedirect("listaEntregas");
					break;

				default:
					response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Tipo de entidade inválido.");
			}

		} catch (Exception e) {
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Erro ao deletar entidade: " + e.getMessage());
		}
	}
}
