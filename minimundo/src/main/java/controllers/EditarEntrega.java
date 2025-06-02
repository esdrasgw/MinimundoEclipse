package controllers;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import enums.EntidadeTipo;
import models.Cliente;
import models.Endereco;
import models.Entrega;
import models.Produto;

@WebServlet("/editarEntrega")
public class EditarEntrega extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int entregaId = Integer.valueOf(request.getParameter("id"));
		
		Connection con = new ConnectionController().ConnectToDatabase();
		DatabaseController db = new DatabaseController();
		
		Entrega entregaGet = (Entrega)db.SelectFromParam(con, EntidadeTipo.ENTREGA, "id", String.valueOf(entregaId));
		entregaGet.setId(entregaId);
		
		request.setAttribute("entrega", entregaGet);
		request.getRequestDispatcher("/editarEntrega.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			DatabaseController db = new DatabaseController();
			Connection con = new ConnectionController().ConnectToDatabase();
			
			int idEntrega = Integer.parseInt(request.getParameter("id"));
			
			String destinatarioCpfCnpj = request.getParameter("destinatario");
			String remetenteCpfCnpj = request.getParameter("remetente");
			
			int produtoId = Integer.parseInt(request.getParameter("produto"));
			
			Cliente destinatario = (Cliente)db.SelectFromParam(con, EntidadeTipo.CLIENTE, "cpfCnpj", destinatarioCpfCnpj);
			Cliente remetente = (Cliente)db.SelectFromParam(con, EntidadeTipo.CLIENTE, "cpfCnpj", remetenteCpfCnpj);
			Endereco endereco = destinatario.getEndereco();
			Produto produto = (Produto)db.SelectFromParam(con, EntidadeTipo.PRODUTO, "id", String.valueOf(produtoId));
						
			Entrega entregaUpdate = new Entrega(destinatario, remetente, produto, endereco);
			entregaUpdate.setId(idEntrega);
			
			db.Update(con, idEntrega, EntidadeTipo.ENTREGA, entregaUpdate);
			
			response.sendRedirect("listaEntregas");
			
		} catch (NullPointerException e) {
			if (e.getMessage().contains("destinatario")) {
				request.setAttribute("mensagemErro", "O CPF/CNPJ do Destinatario não foi encontrado");
			}
			else if (e.getMessage().contains("remetente"))
			{
				request.setAttribute("mensagemErro", "O CPF/CNPJ do Remetente não foi encontrado");
			}
			else if (e.getMessage().contains("produto"))
			{
				request.setAttribute("mensagemErro", "O ID do produto não foi encontrado");
			}
			
			RequestDispatcher rd = request.getRequestDispatcher("/erro.jsp");
    		rd.forward(request, response);
		}
	}
}
