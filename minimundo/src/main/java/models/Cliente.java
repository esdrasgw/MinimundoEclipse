package models;

import enums.TipoCliente;
import enums.TipoPessoa;

public class Cliente implements Entidade {
	
	int id;
	String razaoSocial;
    String cpfCnpj;
    String telefone;
    Endereco endereco;
    String nomeFantasia;
    TipoCliente tipoCliente;
    TipoPessoa tipoPessoa;

    public Cliente(String razaoSocial, String cpfCnpj, String telefone, String nomeFantasia, Endereco endereco, TipoCliente tipoCliente, TipoPessoa tipoPessoa) {
       	
    	this.razaoSocial = razaoSocial;
        this.cpfCnpj = cpfCnpj;
        this.telefone = telefone;
        this.endereco = endereco;
        this.nomeFantasia = nomeFantasia;
        this.tipoCliente = tipoCliente;
        this.tipoPessoa = tipoPessoa;
    }
    
    //#region Setters
    
    public void setId(int id) {
        this.id = id;
    }
    public void setNome(String nome) {
        this.razaoSocial = nome;
    }
    public void setCpfCnpj(String cpfCnpj)
    {
    	this.cpfCnpj = cpfCnpj;
    }
    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }
    public void setNomeFantasia(String nomeFantasia) {
    	this.nomeFantasia = nomeFantasia;
    }
    public void setTipoCliente(TipoCliente tipoCliente) {
        this.tipoCliente = tipoCliente;
    }
    public void setTipoPessoa(TipoPessoa tipoPessoa) {
        this.tipoPessoa = tipoPessoa;
    }

    //#endregion

    //#region Getters
    
    public int getId() {
        return id;
    }
    public String getRazaoSocial() {
        return razaoSocial;
    }
    public String getCpfCnpj() {
    	return cpfCnpj;
    }
    public String getTelefone() {
        return telefone;
    }
    public Endereco getEndereco() {
        return endereco;
    }
    public String getNomeFantasia()
    {
    	return nomeFantasia;
    }
    public TipoCliente getTipoCliente() {
        return tipoCliente;
    }
    public TipoPessoa getTipoPessoa() {
        return tipoPessoa;
    }
}