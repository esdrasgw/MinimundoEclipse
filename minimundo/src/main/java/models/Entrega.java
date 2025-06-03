package models;

public class Entrega extends Entidade {
	int id;
	Cliente destinatario;
	Cliente remetente;
	Produto produto;
	Endereco enderecoEntrega;
	
	public Entrega(Cliente destinatario, Cliente remetente, Produto produto, Endereco enderecoEntrega) {
		this.destinatario = destinatario;
		this.remetente = remetente;
		this.produto = produto;
		this.enderecoEntrega = enderecoEntrega;
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
