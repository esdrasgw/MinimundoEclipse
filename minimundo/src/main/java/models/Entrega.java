package models;

public class Entrega extends Entidade {
	int id;
	Cliente destinatario;
	Cliente remetente;
	Produto produto;
	int quantidadeComprada;
	Endereco enderecoEntrega;
	Endereco enderecoRemetente;
	boolean produtoEntregue;
	
	public Entrega(Cliente destinatario, Cliente remetente, Produto produto, int quantidadeComprada, Endereco enderecoEntrega, Endereco enderecoRemetente, boolean produtoEntregue) {
		this.destinatario = destinatario;
		this.remetente = remetente;
		this.produto = produto;
		this.quantidadeComprada = quantidadeComprada;
		this.enderecoEntrega = enderecoEntrega;
		this.enderecoRemetente = enderecoRemetente;
		this.produtoEntregue = produtoEntregue;
	}
	
	public int getQuantidadeComprada() {
		return quantidadeComprada;
	}
	
	public void setQuantidadeComprada(int quantidadeComprada) {
		this.quantidadeComprada = quantidadeComprada;
	}

	public boolean getProdutoEntregue() {
		return produtoEntregue;
	}

	public void setProdutoEntregue(boolean entregue) {
		this.produtoEntregue = entregue;
	}
	
	public Endereco getEnderecoRemetente() {
		return enderecoRemetente;
	}
	
	public void setEnderecoRemetente(Endereco enderecoRemetente) {
		this.enderecoRemetente = enderecoRemetente;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Cliente getDestinatario() {
		return destinatario;
	}

	public void setDestinatario(Cliente destinatario) {
		this.destinatario = destinatario;
	}

	public Cliente getRemetente() {
		return remetente;
	}

	public void setRemetente(Cliente remetente) {
		this.remetente = remetente;
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
