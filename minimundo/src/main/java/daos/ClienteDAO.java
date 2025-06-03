package daos;

import builders.ClienteBuilder;
import enums.TipoPessoa;
import interfaces.EntidadeDAO;
import models.Cliente;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO implements EntidadeDAO<Cliente> {

    @Override
    public Cliente findById(Connection con, int id) throws SQLException {
        String query = "SELECT * FROM Cliente WHERE id = ?";
        try (PreparedStatement stmt = con.prepareStatement(query)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return ClienteBuilder.fromResultSet(rs, con);
            }
        }
        return null;
    }
    
    public Cliente findByCpfCnpj(Connection con, String cpfCnpj) throws SQLException {
    	String query = "SELECT * FROM Cliente WHERE cpfCnpj = ?";
    	try (PreparedStatement stmt = con.prepareStatement(query)){
    		stmt.setString(1, cpfCnpj);
    		ResultSet rs = stmt.executeQuery();
    		if (rs.next()) {
    			return ClienteBuilder.fromResultSet(rs, con);
    		}
    	}
    	return null;
    }

    @Override
    public List<Cliente> findAll(Connection con) throws SQLException {
        List<Cliente> clientes = new ArrayList<>();
        String query = "SELECT * FROM Cliente ORDER BY id";
        try (Statement stmt = con.createStatement()) {
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                clientes.add(ClienteBuilder.fromResultSet(rs, con));
            }
        }
        return clientes;
    }

    @Override
    public void insert(Connection con, Cliente client) throws SQLException {
        if (client.getTipoPessoa() == TipoPessoa.FISICA && client.getCpfCnpj().length() != 11) {
            throw new IllegalArgumentException("CPF inválido.");
        } else if (client.getTipoPessoa() == TipoPessoa.JURIDICA && client.getCpfCnpj().length() != 14) {
            throw new IllegalArgumentException("CNPJ inválido.");
        }

        String query = "INSERT INTO Cliente (razaoSocial, cpfCnpj, telefone, endereco, nomeFantasia, tipoCliente, tipoPessoa) VALUES (?, ?, ?, ?, ?, ?, ?) RETURNING id";
        try (PreparedStatement stmt = con.prepareStatement(query)) {
            stmt.setString(1, client.getRazaoSocial());
            stmt.setString(2, client.getCpfCnpj());
            stmt.setString(3, client.getTelefone());
            stmt.setInt(4, client.getEndereco().getId());
            stmt.setString(5, client.getNomeFantasia());
            stmt.setObject(6, client.getTipoCliente().name(), Types.OTHER);
            stmt.setObject(7, client.getTipoPessoa().name(), Types.OTHER);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                client.setId(rs.getInt(1));
            }
        }
    }

    @Override
    public void update(Connection con, int id, Cliente client) throws SQLException {
        String query = "UPDATE Cliente SET razaoSocial = ?, nomeFantasia = ?, cpfCnpj = ?, telefone = ?, endereco = ?, tipoCliente = ?, tipoPessoa = ? WHERE id = ?";
        try (PreparedStatement stmt = con.prepareStatement(query)) {
            stmt.setString(1, client.getRazaoSocial());
            stmt.setString(2, client.getNomeFantasia());
            stmt.setString(3, client.getCpfCnpj());
            stmt.setString(4, client.getTelefone());
            stmt.setInt(5, client.getEndereco().getId());
            stmt.setObject(6, client.getTipoCliente().name(), Types.OTHER);
            stmt.setObject(7, client.getTipoPessoa().name(), Types.OTHER);
            stmt.setInt(8, id);
            stmt.execute();
        }
    }

    @Override
    public void delete(Connection con, int id) throws SQLException {
        String query = "DELETE FROM Cliente WHERE id = ?";
        try (PreparedStatement stmt = con.prepareStatement(query)) {
            stmt.setInt(1, id);
            stmt.execute();
        }
    }
}
