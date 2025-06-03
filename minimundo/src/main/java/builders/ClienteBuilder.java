package builders;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import daos.EnderecoDAO;
import enums.TipoCliente;
import enums.TipoPessoa;
import models.Cliente;
import models.Endereco;

public class ClienteBuilder {
    public static Cliente fromResultSet(ResultSet rs, Connection con) throws SQLException {
        String razaoSocial = rs.getString("razaoSocial");
        String cpfCnpj = rs.getString("cpfCnpj");
        String telefone = rs.getString("telefone");
        String nomeFantasia = rs.getString("nomeFantasia");
        TipoCliente tipoCliente = TipoCliente.valueOf(rs.getString("tipoCliente"));
        TipoPessoa tipoPessoa = TipoPessoa.valueOf(rs.getString("tipoPessoa"));
        int enderecoId = rs.getInt("endereco");

        Endereco endereco = new EnderecoDAO().findById(con, enderecoId);

        Cliente cliente = new Cliente(razaoSocial, cpfCnpj, telefone, nomeFantasia, endereco, tipoCliente, tipoPessoa);
        cliente.setId(rs.getInt("id"));
        return cliente;
    }
}
