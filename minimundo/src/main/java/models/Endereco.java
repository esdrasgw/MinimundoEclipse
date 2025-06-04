package models;

public class Endereco extends Entidade {
	int id;
	String logradouro;
	int numero;
	String bairro;
	String cidade;
	String UF;
	String pais;
	int cep;
	String complemento;
	String pontoDeReferencia;

	public Endereco(String logradouro, int numero, String bairro, String cidade, String UF, String pais, int cep,
			String complemento, String pontoDeReferencia) {
		
		this.logradouro = logradouro;
		this.numero = numero;
		this.bairro = bairro;
		this.cidade = cidade;
		this.UF = UF;
		this.pais = pais;
		this.cep = cep;
		this.complemento = complemento;
		this.pontoDeReferencia = pontoDeReferencia;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getUF() {
		return UF;
	}

	public void setUF(String UF) {
		this.UF = UF;
	}

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	public int getCep() {
		return cep;
	}

	public void setCep(int cep) {
		this.cep = cep;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getPontoDeReferencia() {
		return pontoDeReferencia;
	}

	public void setPontoDeReferencia(String pontoDeReferencia) {
		this.pontoDeReferencia = pontoDeReferencia;
	}
	
	@Override
	public String toString() {	
	    return logradouro + " - " + numero + ", " + complemento + ", " + bairro + ", " + cidade + ", " + pontoDeReferencia + " - " + cep  + ", UF:" + UF + " - " + pais;
	}
}
