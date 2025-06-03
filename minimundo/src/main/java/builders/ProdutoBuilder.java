package builders;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import models.Produto;

public class ProdutoBuilder {
	public static Produto fromResultSet(ResultSet rs, Connection con) throws SQLException {
		String name = rs.getString("nome");
		String descricao = rs.getString("descricao");
		int estoque = rs.getInt("estoque");
		double preco = rs.getDouble("preco");
		double peso = rs.getDouble("peso");

		Produto produto = new Produto(name, descricao, estoque, preco, peso);
		produto.setId(rs.getInt("id"));
		
		return produto;
	}
}
