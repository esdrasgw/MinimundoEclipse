CREATE TYPE TipoPessoa AS ENUM ('FISICA', 'JURIDICA');
CREATE TYPE Documento AS ENUM ('CPF', 'CNPJ');

CREATE TABLE Produto (
	id SERIAL PRIMARY KEY,
	nome VARCHAR(255),
	descricao VARCHAR(255),
	estoque INT,
	preco NUMERIC(9,2),
	peso NUMERIC(9,2)
);


CREATE TABLE Endereco (
	id SERIAL PRIMARY KEY,
	logradouro varchar(255),
	numero INT,
	bairro varchar(255),
	cidade varchar(255),
	UF varchar(255),
	pais varchar(255),
	cep INT,
	complemento varchar(255),
	pontoDeReferencia varchar(255)
);


CREATE TABLE Cliente (
	id SERIAL PRIMARY KEY,
	razaoSocial varchar(255),
	cpfCnpj varchar(255) UNIQUE,
	telefone varchar(9),
	endereco INTEGER REFERENCES Endereco(id),
	nomeFantasia varchar(255),
	tipoPessoa TipoPessoa
);

CREATE TABLE Entrega (
	id SERIAL PRIMARY KEY,
	destinatario varchar(255) REFERENCES Cliente(cpfCnpj) ON DELETE CASCADE,
	remetente varchar(255) REFERENCES Cliente(cpfCnpj) ON DELETE CASCADE,
	produto INT REFERENCES Produto(id) ON DELETE CASCADE,
	quantidadeComprada INT,
	enderecoEntrega INT REFERENCES Endereco(id) ON DELETE CASCADE,
	enderecoRemetente INT REFERENCES Endereco(id) ON DELETE CASCADE,
	produtoEntregue BOOLEAN
);

