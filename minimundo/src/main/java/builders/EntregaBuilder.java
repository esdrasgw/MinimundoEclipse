package builders;

import daos.ClienteDAO;
import daos.ProdutoDAO;
import models.Cliente;
import models.Entrega;
import models.Produto;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EntregaBuilder {
    public static Entrega fromResultSet(ResultSet rs, Connection con) throws SQLException {
        int id = rs.getInt("id");
        String destinatarioCpfCnpj = rs.getString("destinatario");
        String remetenteCpfCnpj = rs.getString("remetente");
        int produtoId = rs.getInt("produto");
        boolean produtoEntregue = rs.getBoolean("produtoEntregue");

        Cliente destinatario = new ClienteDAO().findByCpfCnpj(con, destinatarioCpfCnpj);
        Cliente remetente = new ClienteDAO().findByCpfCnpj(con, remetenteCpfCnpj);
        Produto produto = new ProdutoDAO().findById(con, produtoId);

        Entrega entrega = new Entrega(destinatario, remetente, produto, destinatario.getEndereco(), produtoEntregue);
        entrega.setId(id);
        return entrega;
    }
}
