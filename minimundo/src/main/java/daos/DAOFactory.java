package daos;

import enums.EntidadeTipo;
import interfaces.EntidadeDAO;
import models.Entidade;

public class DAOFactory {

    @SuppressWarnings("unchecked")
    public static <T extends Entidade> EntidadeDAO<T> getDAO(EntidadeTipo tipo) {
        switch (tipo) {
            case CLIENTE:
                return (EntidadeDAO<T>) new ClienteDAO();
            case PRODUTO:
                return (EntidadeDAO<T>) new ProdutoDAO();
            case ENDERECO:
                return (EntidadeDAO<T>) new EnderecoDAO();
            case ENTREGA:
                return (EntidadeDAO<T>) new EntregaDAO();
            default:
                throw new IllegalArgumentException("Tipo de entidade desconhecido: " + tipo);
        }
    }
}
