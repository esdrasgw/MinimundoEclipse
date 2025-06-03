package daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import builders.EnderecoBuilder;
import interfaces.EntidadeDAO;
import models.Endereco;

public class EnderecoDAO implements EntidadeDAO<Endereco> {
	@Override
    public Endereco findById(Connection con, int id) throws SQLException {
        String query = "SELECT * FROM Endereco WHERE id = ?";
        try (PreparedStatement stmt = con.prepareStatement(query)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return EnderecoBuilder.fromResultSet(rs, con);
            }
        }
        return null;
    }

    @Override
    public List<Endereco> findAll(Connection con) throws SQLException {
        List<Endereco> enderecos = new ArrayList<>();
        String query = "SELECT * FROM Endereco ORDER BY id";
        try (Statement stmt = con.createStatement()) {
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
            	enderecos.add(EnderecoBuilder.fromResultSet(rs, con));
            }
        }
        return enderecos;
    }

    @Override
    public void insert(Connection con, Endereco endereco) throws SQLException {
        
        String query = "INSERT INTO Endereco (logradouro, numero, bairro, cidade, UF, pais, cep, complemento, pontoDeReferencia) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?) RETURNING id";
        try (PreparedStatement stmt = con.prepareStatement(query)) {
            stmt.setString(1, endereco.getLogradouro());
            stmt.setInt(2, endereco.getNumero());
            stmt.setString(3, endereco.getBairro());
            stmt.setString(4, endereco.getCidade());
            stmt.setString(5, endereco.getUF());
            stmt.setString(6, endereco.getPais());
            stmt.setInt(7, endereco.getCep());
            stmt.setString(8, endereco.getComplemento());
            stmt.setString(9, endereco.getPontoDeReferencia());

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                endereco.setId(rs.getInt(1));
            }
        }
    }

    @Override
    public void update(Connection con, int id, Endereco endereco) throws SQLException {
        String query = "UPDATE Endereco SET logradouro = ?, numero = ?, bairro = ?, cidade = ?, UF = ?, pais = ?, cep = ?, complemento = ?, pontodereferencia = ? WHERE id = ?";
        try (PreparedStatement stmt = con.prepareStatement(query)) {
        	stmt.setString(1, endereco.getLogradouro());
            stmt.setInt(2, endereco.getNumero());
            stmt.setString(3, endereco.getBairro());
            stmt.setString(4, endereco.getCidade());
            stmt.setString(5, endereco.getUF());
            stmt.setString(6, endereco.getPais());
            stmt.setInt(7, endereco.getCep());
            stmt.setString(8, endereco.getComplemento());
            stmt.setString(9, endereco.getPontoDeReferencia());
            stmt.setInt(10, id);
            stmt.execute();
        }
    }

    @Override
    public void delete(Connection con, int id) throws SQLException {
        String query = "DELETE FROM Endereco WHERE id = ?";
        try (PreparedStatement stmt = con.prepareStatement(query)) {
            stmt.setInt(1, id);
            stmt.execute();
        }
    }
	
}
