package models.Entrega;

import models.Entidade;
import models.Client.Cliente;
import models.Endereco.Endereco;
import models.Product.Produto;

public class Entrega implements Entidade {
	int id;
	Cliente cliente;
	Produto produto;
	Endereco enderecoEntrega;
	
	public Entrega (Cliente cliente, Produto produto)
	{
		this.cliente = cliente;
		this.produto = produto;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Cliente getCliente() {
		 return cliente;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public Endereco getEnderecoEntrega() {
		return enderecoEntrega;
	}

	public void setEnderecoEntrega(Endereco enderecoEntrega) {
		this.enderecoEntrega = enderecoEntrega;
	}
}
