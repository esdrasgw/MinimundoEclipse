package builders;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import models.Endereco;

public class EnderecoBuilder {
	public static Endereco fromResultSet(ResultSet rs, Connection con) throws SQLException {
		String logradouro = rs.getString("logradouro");
		int numero = rs.getInt("numero");
		String bairro = rs.getString("bairro");
		String cidade = rs.getString("cidade");
		String UF = rs.getString("uf");
		String pais = rs.getString("pais");
		int cep = rs.getInt("cep");
		String complemento = rs.getString("complemento");
		String pontoDeReferencia = rs.getString("pontoDeReferencia");
		
		Endereco endereco = new Endereco(logradouro, numero, bairro, cidade, UF, pais, cep, complemento, pontoDeReferencia);
		endereco.setId(rs.getInt("id"));
		
		return endereco;
		
	}
}
