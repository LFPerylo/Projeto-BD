![image](https://github.com/user-attachments/assets/9a074de0-dc64-4a39-b60b-e53fa8003aed)# 🎉 Projeto de Banco de Dados - Sistema de Gerenciamento de Festas Infantis

Este projeto foi desenvolvido com **Java (Spring Boot)** e **MySQL**, com foco na criação de um sistema CRUD completo, sem uso de frameworks de persistência como JPA ou Hibernate. Toda a comunicação com o banco é feita por meio de comandos SQL puros via **JDBC**.

---

## 📁 Clonando o Projeto

```bash
git clone https://github.com/LFPerylo/Projeto-BD.git
cd Projeto-BD
```

---

## 📚 Documentação do Banco de Dados

### 🧠 Modelos

- [Modelo Conceitual](documentacao/Conceitual_Projeto_Final.png)
- [Modelo Lógico](documentacao/Logico_Projeto_Final.png)

### 📜 Scripts SQL

- [Script de Criação das Tabelas](documentacao/Script-Projeto.sql)
- [Script de Inserção de Dados](documentacao/Script-insercao-projeto.sql)

---

## ⚙️ Pré-requisitos

Antes de executar o projeto, verifique se possui os seguintes requisitos instalados:

- ✅ Java 17 (recomendado: Amazon Corretto)
- ✅ MySQL Server (rodando na porta `3306`)
- ✅ Maven configurado no PATH

---

## 🔐 Configuração do Banco (.env)

Como o projeto utiliza variáveis de ambiente para manter os dados seguros, você deve criar manualmente um arquivo `.env` na raiz do projeto:

> 📂 Exemplo: `.env`

```env
DB_URL=jdbc:mysql://localhost:3306/projeto-bd?useSSL=false&serverTimezone=UTC
DB_USERNAME=root
DB_PASSWORD=sua_senha
```

> ☑️ Um modelo de referência está disponível em [.env.example](.env.example)

---

## 🧪 Testando a Conexão com o Banco

O projeto inclui uma classe para teste de conexão:

```java
src/main/java/com/SpringBD/TesteConexao.java
```

> ✅ Ao executar essa classe, será exibida a mensagem de sucesso ou falha de conexão com o banco de dados.

---

## 🚀 Inicialização do Projeto

Após configurar o `.env` e o banco:

```bash
mvn clean install
```

Execute a aplicação pela classe:

```java
src/main/java/com/SpringBD/SpringBdApplication.java
```

---

## 📄 Licença

Este projeto está licenciado sob os termos da [Licença MIT](LICENSE).
```

URL DA ENTREGA 01: http://localhost:8080/cliente.html
