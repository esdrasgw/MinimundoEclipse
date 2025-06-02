package models;

public class Produto implements Entidade {
    int idProduto;
    String nome;
    String descricao;
    int estoque;
    double preco;
    double peso;
    
    public Produto(String nome, String descricao, int estoque, double preco, double peso) 
    {
	    this.nome = nome;
	    this.descricao = descricao;
	    this.estoque = estoque;
	    this.preco = preco;
	    this.peso = peso;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    
    public void setId(int id)
    {
    	this.idProduto = id;
    }
    public void setPeso(double peso) {
        this.peso = peso;
    }
    public void setPreco(double preco) {
        this.preco = preco;
    }
    public void setEstoque(int estoque) {
        this.estoque = estoque;
    }
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getIdProduto() {
        return idProduto;
    }
    public String getNome() {
        return nome;
    }
    public double getPeso() {
        return peso;
    }
    public double getPreco() {
        return preco;
    }
    public int getEstoque() {
        return estoque;
    }
    public String getDescricao() {
        return descricao;
    }
}