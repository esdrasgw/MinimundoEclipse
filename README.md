# 📦 Projeto Minimundo

Nesse projeto, foi desenvolvida uma simulação de um sistema de entregas. 
Nele é possível 
- *Criar*,
- *Editar*,
- *Listar*,
- *Deletar*  

**Clientes e seus Endereços, Produtos e Entregas**.

---

## 🔧 Tecnologias Utilizadas

- Java 8
- Apache Tomcat 9
- PostgreSQL 16.8 usando DBeaver 24.3.4
- JDBC
- JSTL
- Eclipse IDE

---

## 🚀 Como executar o projeto

- Clone o repositório

Crie uma base de dados no postgres.
- *CREATE DATABASE 'nomedobanco';* (Foi utilizado 'postgres' como nome do banco para a criação do projeto)

Execute o script *'PostgresMinimundo.sql'* no postgres para a criação das tabelas e dos tipos.

Configure a conexão com o banco no arquivo java/controllers/ConnectionController.java nos campos  
- **URL** (URL padrão utilizando 'postgres' como nome do banco).
- **USER** (Seu usuário, padrão sendo 'postgres').
- **PASSWORD** (Sua senha, padrão sendo '1234').

Faça o deploy no TomCat usando sua IDE 
  
### Inicie o TomCat e acesse: **'localhost:8080/minimundo'**
