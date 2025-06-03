package interfaces;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import daos.ClienteDAO;
import daos.EnderecoDAO;
import daos.EntregaDAO;
import daos.ProdutoDAO;
import enums.EntidadeTipo;
import models.Entidade;

public interface EntidadeDAO<T extends Entidade> {
	public static EntidadeDAO<?> getDAO(EntidadeTipo tipo) {
        switch (tipo) {
            case CLIENTE:
                return new ClienteDAO();
            case ENDERECO:
            	return new EnderecoDAO();
            case PRODUTO:
                return new ProdutoDAO();
            case ENTREGA:
                return new EntregaDAO();
            default:
                throw new IllegalArgumentException("Tipo não suportado: " + tipo);
        }
    }
    void insert(Connection con, T entidade) throws SQLException;
    void update(Connection con, int id, T entidade) throws SQLException;
    void delete(Connection con, int id) throws SQLException;
    T findById(Connection con, int id) throws SQLException;
    List<T> findAll(Connection con) throws SQLException;
}
