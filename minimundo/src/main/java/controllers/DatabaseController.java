package controllers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import models.Entidade;
import models.EntidadeTipo;
import models.Client.Cliente;
import models.Client.Documento;
import models.Client.TipoCliente;
import models.Client.TipoPessoa;
import models.Endereco.Endereco;
import models.Entrega.Entrega;
import models.Product.Produto;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DatabaseController {
	
	public Entidade CreateInsertable(Connection con, EntidadeTipo tipoEntidade, ResultSet rs) throws SQLException
	{
		switch (tipoEntidade) {
		case CLIENTE:
			String razaoSocial = rs.getString("razaoSocial");
			String cpfCnpj = rs.getString("cpfCnpj");
			String telefone = rs.getString("telefone"); 
			Endereco endereco = (Endereco)SelectFromParam(con, EntidadeTipo.ENDERECO, "id", String.valueOf(rs.getInt("id"))); 
			String nomeFantasia = rs.getString("nomeFantasia");
			TipoCliente tipoCliente = TipoCliente.valueOf(rs.getString("tipoCliente")); 
			TipoPessoa tipoPessoa = TipoPessoa.valueOf(rs.getString("tipoPessoa"));
        	
			Cliente cliente = new Cliente(razaoSocial, cpfCnpj, telefone, nomeFantasia, endereco, tipoCliente, tipoPessoa);
			cliente.setId(rs.getInt("id"));
			return cliente;
			
		case PRODUTO:
			String name = rs.getString("nome");
    		String descricao = rs.getString("descricao");
    		int estoque = rs.getInt("estoque");
    		double preco = rs.getDouble("preco");
    		double peso = rs.getDouble("peso");

    		Produto produto = new Produto(name, descricao, estoque, preco, peso);
    		produto.setId(rs.getInt("id"));
    		return produto;
    		
		case ENDERECO:
			String logradouro = rs.getString("logradouro");
			int numero = rs.getInt("numero");
			String bairro = rs.getString("bairro");
			String cidade = rs.getString("cidade");
			String UF = rs.getString("uf");
			String pais = rs.getString("pais");
			int cep = rs.getInt("cep");
			String complemento = rs.getString("complemento");
			String pontoDeReferencia = rs.getString("pontoDeReferencia");
			
			Endereco enderecoObj = new Endereco(logradouro, numero, bairro, cidade, UF, pais, cep, complemento, pontoDeReferencia);
			enderecoObj.setId(rs.getInt("id"));
			
			return enderecoObj;
			
		case ENTREGA:
			
			cliente = (Cliente)SelectFromParam(con, EntidadeTipo.CLIENTE, "id", String.valueOf(rs.getInt(1)));
			produto = (Produto)SelectFromParam(con, EntidadeTipo.PRODUTO, "id", String.valueOf(rs.getInt(1)));
			
			Entrega entregaObj = new Entrega(cliente, produto);
			return entregaObj;
			
		default: return null;	
		
		}
	}
	
	public Entidade SelectFromParam(Connection con, EntidadeTipo tableName, String paramName, String param)
	{		
		try {
			String query = "SELECT * FROM " + tableName + " WHERE " + paramName + " = " + "'" + param + "'";
			Statement statement = con.createStatement();
			
			ResultSet rs = statement.executeQuery(query);
			
			while (rs.next())
			{
				return CreateInsertable(con, tableName, rs);
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return null;
	}

    public List<Entidade> SelectAllFromTable(Connection con, EntidadeTipo tableName)
    {
        List<Entidade> list = new ArrayList<>();

        try {
            String query = "SELECT * FROM " + tableName + " ORDER BY 1";
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(query);
            
            while (rs.next()) 
            {
            	list.add(CreateInsertable(con, tableName, rs));
            }
            return list;
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }

    public void Update(Connection con, int id, EntidadeTipo tipoEntidade, Entidade entidade)
    {
        try {
        	String query = "";
        	
        	switch (tipoEntidade) {
        	case CLIENTE:
        		query = "UPDATE Cliente SET razaoSocial = ?, nomeFantasia = ?, cpfCnpj = ?, telefone = ?, endereco = ?, TipoCliente = ?, TipoPessoa = ? WHERE id = ?;";
        		Cliente client = (Cliente)entidade;
                PreparedStatement statement = con.prepareStatement(query);
                
                statement.setString(1, client.getRazaoSocial());
                statement.setString(2, client.getNomeFantasia());
                statement.setString(3, client.getCpfCnpj());
                statement.setString(4, client.getTelefone());
                statement.setInt(5, client.getEndereco().getId());
                statement.setObject(6, client.getTipoCliente().name(), java.sql.Types.OTHER);
                statement.setObject(7, client.getTipoPessoa().name(), java.sql.Types.OTHER);
                statement.setInt(8, id);
                statement.execute();
                
                break;
                
        	case PRODUTO:
        		query = "UPDATE Produto SET nome = ?, descricao = ?, estoque = ?, preco = ?, peso = ? WHERE id = ?;";
                Produto product = (Produto)entidade;
        		statement = con.prepareStatement(query);
                
                statement.setString(1, product.getNome());
                statement.setString(2, product.getDescricao());
                statement.setInt(3, product.getEstoque());
                statement.setDouble(4, product.getPreco());
                statement.setDouble(5, product.getPeso());
                statement.setInt(6, id);
                statement.execute();
                
                break;
                
        	case ENDERECO:
        		query = "UPDATE Endereco SET logradouro = ?, numero = ?, bairro = ?, cidade = ?, UF = ?, pais = ?, cep = ?, complemento = ?, pontoDeReferencia = ? WHERE id = ?;";
        		Endereco endereco = (Endereco)entidade;
        		statement = con.prepareStatement(query);
        		
        		statement.setString(1, endereco.getLogradouro());
        		statement.setInt(2, endereco.getNumero());
        		statement.setString(3, endereco.getBairro());
        		statement.setString(4, endereco.getCidade());
        		statement.setString(5, endereco.getUF());
        		statement.setString(6, endereco.getPais());
        		statement.setInt(7, endereco.getCep());
        		statement.setString(8, endereco.getComplemento());
        		statement.setString(9, endereco.getPontoDeReferencia());
        		statement.setInt(10, id);
        		
        		statement.execute();
        		break;
        		
        	case ENTREGA:
        		break;
        	}
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    public void Insert(Connection con, Entidade insertable, EntidadeTipo insertableType) throws Exception
    {
    	try {
    		String query = null;
        	PreparedStatement statement;
        	ResultSet rs;
    		
    		switch(insertableType)
    		{
    		case CLIENTE:
    			Cliente client = (Cliente)insertable;
    			
    			if (client.getTipoPessoa() == TipoPessoa.FISICA && client.getCpfCnpj().length() != 11) {
                    System.out.println("CPF inválido, por favor verifique o CPF.");
                    return;
                } else if (client.getTipoPessoa() == TipoPessoa.JURIDICA && client.getCpfCnpj().length() != 14) {
                    System.out.println("CNPJ inválido, por favor verifique o CNPJ.");
                    return;
                }
    			
    			query = "INSERT INTO Cliente (razaoSocial, cpfCnpj, telefone, endereco, nomeFantasia, tipoCliente, tipoPessoa) VALUES (?, ?, ?, ?, ?, ?, ?) RETURNING id;";
    			
    			
    			statement = con.prepareStatement(query);
    			statement.setString(1, client.getRazaoSocial());
    			statement.setString(2, client.getCpfCnpj());
    			statement.setString(3, client.getTelefone());
    			statement.setInt(4, client.getEndereco().getId());
    			statement.setString(5, client.getNomeFantasia());
    			statement.setObject(6, client.getTipoCliente().name(), java.sql.Types.OTHER);
    			statement.setObject(7, client.getTipoPessoa().name(), java.sql.Types.OTHER);

    			statement.execute();
        		rs = statement.getResultSet();
        		rs.next();
        		client.setId(rs.getInt(1));
        		
        		break;
    			
    		case PRODUTO:
    			
    			Produto product = (Produto)insertable;
    			query = "INSERT INTO Produto (nome, descricao, estoque, preco, peso) VALUES (?, ?, ?, ?, ?) RETURNING id;";
    			
    			statement = con.prepareStatement(query);
    			statement.setString(1, product.getNome());
                statement.setString(2, product.getDescricao());
                statement.setInt(3, product.getEstoque());
                statement.setDouble(4, product.getPreco());
                statement.setDouble(5, product.getPeso());
                
                statement.execute();
        		rs = statement.getResultSet();
        		rs.next();
        		product.setId(rs.getInt(1));
        		
        		break;
                
    		case ENDERECO:
    			
    			Endereco endereco = (Endereco)insertable;
    			query = "INSERT INTO Endereco (logradouro, numero, bairro, cidade, UF, pais, cep, complemento, pontoDeReferencia) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?) RETURNING id;";
    			
    			statement = con.prepareStatement(query);
    			
    			statement.setString(1, endereco.getLogradouro());
        		statement.setInt(2, endereco.getNumero());
        		statement.setString(3, endereco.getBairro());
        		statement.setString(4, endereco.getCidade());
        		statement.setString(5, endereco.getUF());
        		statement.setString(6, endereco.getPais());
        		statement.setInt(7, endereco.getCep());
        		statement.setString(8, endereco.getComplemento());
        		statement.setString(9, endereco.getPontoDeReferencia());
    			
        		statement.execute();
        		rs = statement.getResultSet();
        		rs.next();
        		endereco.setId(rs.getInt(1));
        		
        		break;
        		
    		case ENTREGA:
    			
    			Entrega entrega = (Entrega)insertable;
    			
    			if (entrega.getCliente().getTipoCliente() == TipoCliente.DESTINATARIO)
    			{
    				query = "UPDATE Cliente SET tipoCliente = ? WHERE id = ?;";
    				statement = con.prepareStatement(query);
    				statement.setObject(1, TipoCliente.DESTINATARIO, java.sql.Types.OTHER);
    				statement.setInt(2, entrega.getCliente().getId());
    				
        			query = "INSERT INTO Entrega(destinatario, produto, enderecoEntrega) VALUES (?, ?, ?) RETURNING id;";
        			statement = con.prepareStatement(query);
        			statement.setString(1, entrega.getCliente().getCpfCnpj());
        			statement.setInt(2, entrega.getProduto().getIdProduto());
        			statement.setInt(3, entrega.getCliente().getEndereco().getId());
        			
    			}
    			else 
    			{
    				query = "UPDATE Cliente SET tipoCliente = ? WHERE id = ?;";
    				statement = con.prepareStatement(query);
    				statement.setObject(1, TipoCliente.REMETENTE, java.sql.Types.OTHER);
    				statement.setInt(2, entrega.getCliente().getId());
    				
    				statement.execute();
    				
    				query = "INSERT INTO Entrega(remetente, produto) VALUES ('?', ?) RETURNING id;";
    				statement = con.prepareStatement(query);
        			statement.setString(1, entrega.getCliente().getCpfCnpj());
        			statement.setInt(2, entrega.getProduto().getIdProduto());
    			}
    			
    			statement.execute();
    			rs = statement.getResultSet();
    			rs.next();
    			entrega.setId(rs.getInt(1));
    			
    			break;
    			
    			default: return;
    		}
         	
    	} catch (Exception e) {
    		System.out.println(e);
    		
    		throw e;
    	}
		return;
    }
    
    
    public void Delete(Connection con, EntidadeTipo entidadeTipo, int id)
    {
    	String query;
    	try {
    		query = "DELETE FROM " + entidadeTipo + " WHERE id = ?;";
    		PreparedStatement statement = con.prepareStatement(query);
    		statement.setInt(1, id);
    		statement.execute();
    	} catch (Exception e) {
    		System.out.println(e);
    	}
    }
}
