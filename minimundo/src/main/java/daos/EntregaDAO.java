package daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import builders.EntregaBuilder;
import enums.TipoCliente;
import interfaces.EntidadeDAO;
import models.Entrega;

public class EntregaDAO implements EntidadeDAO<Entrega> {
	
	@Override
    public Entrega findById(Connection con, int id) throws SQLException {
        String query = "SELECT * FROM Entrega WHERE id = ?";
        try (PreparedStatement stmt = con.prepareStatement(query)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return EntregaBuilder.fromResultSet(rs, con);
            }
        }
        return null;
    }
	
    @Override
    public List<Entrega> findAll(Connection con) throws SQLException {
        List<Entrega> entregas = new ArrayList<>();
        String query = "SELECT * FROM Entrega ORDER BY id";
        try (Statement stmt = con.createStatement()) {
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                entregas.add(EntregaBuilder.fromResultSet(rs, con));
            }
        }
        return entregas;
    }

    @Override
    public void insert(Connection con, Entrega entrega) throws SQLException {
    	
        String query = "INSERT INTO Entrega (destinatario, remetente, produto, enderecoEntrega, enderecoRemetente, produtoEntregue) VALUES (?, ?, ?, ?, ?, ?) RETURNING id;";

        try (PreparedStatement stmt = con.prepareStatement(query)) {
        	stmt.setString(1, entrega.getDestinatario().getCpfCnpj());
        	stmt.setString(2, entrega.getRemetente().getCpfCnpj());
        	stmt.setInt(3, entrega.getProduto().getIdProduto());
        	stmt.setInt(4, entrega.getEnderecoEntrega().getId());
        	stmt.setInt(5, entrega.getEnderecoRemetente().getId());
        	stmt.setBoolean(6, entrega.getProdutoEntregue());

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                entrega.setId(rs.getInt(1));
            }
            
//            query = "UPDATE Cliente SET tipoCliente = ? WHERE id = ?;";
//            try (PreparedStatement statementDestinatario = con.prepareStatement(query)) {
//            	statementDestinatario.setObject(1, TipoCliente.DESTINATARIO, java.sql.Types.OTHER);
//            	statementDestinatario.setInt(2, entrega.getDestinatario().getId());
//            	statementDestinatario.executeUpdate();
//            }
//            
//            query = "UPDATE Cliente SET tipoCliente = ? WHERE id = ?;";
//            try (PreparedStatement statementRemetente = con.prepareStatement(query)) {
//            	statementRemetente.setObject(1, TipoCliente.REMETENTE, java.sql.Types.OTHER);
//            	statementRemetente.setInt(2, entrega.getRemetente().getId());
//            	statementRemetente.executeUpdate();
//            }
        }
    }

    @Override
    public void update(Connection con, int id, Entrega entrega) throws SQLException {
		String query = "UPDATE Entrega SET destinatario = ?, remetente = ?, produto = ?, enderecoEntrega = ?, enderecoRemetente = ?, produtoEntregue = ? WHERE id = ?;";
        try (PreparedStatement stmt = con.prepareStatement(query)) {
        	stmt.setString(1, entrega.getDestinatario().getCpfCnpj());
        	stmt.setString(2, entrega.getRemetente().getCpfCnpj());
        	stmt.setInt(3, entrega.getProduto().getIdProduto());
        	stmt.setInt(4, entrega.getEnderecoEntrega().getId());
        	stmt.setInt(5, entrega.getEnderecoRemetente().getId());
        	stmt.setBoolean(6, entrega.getProdutoEntregue());
        	stmt.setInt(7, id);
    		
        	stmt.execute();
        }
        
//        query = "UPDATE Cliente SET tipoCliente = ? WHERE id = ?;";
//        try (PreparedStatement statementDestinatario = con.prepareStatement(query)) {
//        	statementDestinatario.setObject(1, TipoCliente.DESTINATARIO, java.sql.Types.OTHER);
//        	statementDestinatario.setInt(2, entrega.getDestinatario().getId());
//        	statementDestinatario.executeUpdate();
//        }
//        
//        query = "UPDATE Cliente SET tipoCliente = ? WHERE id = ?;";
//        try (PreparedStatement statementRemetente = con.prepareStatement(query)) {
//        	statementRemetente.setObject(1, TipoCliente.REMETENTE, java.sql.Types.OTHER);
//        	statementRemetente.setInt(2, entrega.getRemetente().getId());
//        	statementRemetente.executeUpdate();
//        }
    }

    @Override
    public void delete(Connection con, int id) throws SQLException {
    	
    	Entrega entrega = findById(con, id);
    	
    	String query = "DELETE FROM Entrega WHERE id = ?";
        try (PreparedStatement stmt = con.prepareStatement(query)) {
            stmt.setInt(1, id);
            stmt.execute();
        }
        
//        query = "UPDATE Cliente SET tipoCliente = ? WHERE id = ?;";
//        try (PreparedStatement statementDestinatario = con.prepareStatement(query)) {
//        	statementDestinatario.setObject(1, TipoCliente.NULO, java.sql.Types.OTHER);
//        	statementDestinatario.setInt(2, entrega.getDestinatario().getId());
//        	statementDestinatario.executeUpdate();
//        }
//        
//        query = "UPDATE Cliente SET tipoCliente = ? WHERE id = ?;";
//        try (PreparedStatement statementRemetente = con.prepareStatement(query)) {
//        	statementRemetente.setObject(1, TipoCliente.NULO, java.sql.Types.OTHER);
//        	statementRemetente.setInt(2, entrega.getRemetente().getId());
//        	statementRemetente.executeUpdate();
//        }
    }

}
