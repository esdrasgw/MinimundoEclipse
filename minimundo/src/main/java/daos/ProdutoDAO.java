package daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import builders.ProdutoBuilder;
import interfaces.EntidadeDAO;
import models.Produto;

public class ProdutoDAO implements EntidadeDAO<Produto> {
	@Override
    public Produto findById(Connection con, int id) throws SQLException {
        String query = "SELECT * FROM Produto WHERE id = ?";
        try (PreparedStatement stmt = con.prepareStatement(query)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return ProdutoBuilder.fromResultSet(rs, con);
            }
        }
        return null;
    }

    @Override
    public List<Produto> findAll(Connection con) throws SQLException {
        List<Produto> produtos = new ArrayList<>();
        String query = "SELECT * FROM Produto ORDER BY id";
        try (Statement stmt = con.createStatement()) {
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
            	produtos.add(ProdutoBuilder.fromResultSet(rs, con));
            }
        }
        return produtos;
    }

    @Override
    public void insert(Connection con, Produto produto) throws SQLException {

		String query = "INSERT INTO Produto (nome, descricao, estoque, preco, peso) VALUES (?, ?, ?, ?, ?) RETURNING id;";
        try (PreparedStatement stmt = con.prepareStatement(query)) {
        	stmt.setString(1, produto.getNome());
        	stmt.setString(2, produto.getDescricao());
        	stmt.setInt(3, produto.getEstoque());
        	stmt.setDouble(4, produto.getPreco());
        	stmt.setDouble(5, produto.getPeso());

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
            	produto.setId(rs.getInt(1));
            }
        }
    }

    @Override
    public void update(Connection con, int id, Produto produto) throws SQLException {
		String query = "UPDATE Produto SET nome = ?, descricao = ?, estoque = ?, preco = ?, peso = ? WHERE id = ?;";
        try (PreparedStatement stmt = con.prepareStatement(query)) {
        	stmt.setString(1, produto.getNome());
        	stmt.setString(2, produto.getDescricao());
        	stmt.setInt(3, produto.getEstoque());
        	stmt.setDouble(4, produto.getPreco());
        	stmt.setDouble(5, produto.getPeso());
        	stmt.setInt(6, id);
        	stmt.execute();
        }
    }

    @Override
    public void delete(Connection con, int id) throws SQLException {
        String query = "DELETE FROM Produto WHERE id = ?";
        try (PreparedStatement stmt = con.prepareStatement(query)) {
            stmt.setInt(1, id);
            stmt.execute();
        }
    }
}
