
# 🎉 Sistema de Gerenciamento de Festas Infantis – Projeto de Banco de Dados

Este projeto foi desenvolvido com **Java (Spring Boot)** e **MySQL**, com foco em um sistema completo de gerenciamento de festas infantis. Todas as operações são realizadas utilizando **JDBC puro**, sem o uso de frameworks ORM como JPA ou Hibernate.

A aplicação permite o cadastro, edição, exclusão e consulta de informações envolvendo **clientes, festas, aniversariantes, contratos, temas, pagamentos** e muito mais. Conta ainda com **painéis de controle (dashboards)** interativos e scripts avançados no banco.

> 🔗 Acesse a aplicação localmente em:  
> 📍 [`http://localhost:8080/login.html`](http://localhost:8080/login.html)

---

## 📁 Clonando o Projeto

```bash
git clone https://github.com/LFPerylo/Projeto-BD.git
cd Projeto-BD
```

---

## 🧠 Modelagem e Scripts

### 📊 Modelos

- 🧩 [Modelo Conceitual](documentacao/Conceitual_Projeto_Final.png)
- 🧩 [Modelo Lógico](documentacao/Logico_Projeto_Final.png)

### 📜 Scripts SQL

- 🏗️ [Script de Criação de Tabelas](documentacao/Script-Projeto.sql)
- ✍️ [Script de Inserção de Dados](documentacao/Script-insercao-projeto.sql)
- 📌 Scripts Programáveis:
  - [`resumo_financeiro_completo`](documentacao/Script-resumo-financeiro.sql):
    ```sql
    CREATE PROCEDURE resumo_financeiro_completo(IN contrato INT)
    BEGIN
        SELECT 
            o.Num_Contrato,
            o.Valor_Inicial AS valor_inicial,
            o.Valor_Sinal AS valor_sinal,
            IFNULL(SUM(p.Valor_Acrescentado), 0) AS valor_extra,
            (o.Valor_Inicial + IFNULL(SUM(p.Valor_Acrescentado), 0)) AS valor_final
        FROM orcamento_contrato o
        LEFT JOIN pagamento p ON o.Num_Contrato = p.Num_Contrato
        WHERE o.Num_Contrato = contrato
        GROUP BY o.Num_Contrato, o.Valor_Inicial, o.Valor_Sinal;
    END;
    ```
  - [`festas_por_mes` e `festas_por_mes_e_ano`](documentacao/Script-festas-por-ano.sql):
    ```sql
    CREATE PROCEDURE festas_por_mes(IN ano INT)
    BEGIN
      SELECT MONTH(Data_Festa) AS mes, COUNT(*) AS quantidade
      FROM orcamento_contrato
      WHERE YEAR(Data_Festa) = ano
      GROUP BY mes ORDER BY mes;
    END;
    ```
  - [`verificar_aniversariantes_proximos` + Trigger + Evento](documentacao/Script-alerta-aniversario.sql)

---

## ⚙️ Pré-requisitos

- ✅ Java 17 (Amazon Corretto recomendado)
- ✅ MySQL Server (porta padrão: 3306)
- ✅ Maven configurado no PATH

---

## 🔐 Configuração de Ambiente

Crie um arquivo `.env` com:

```env
DB_URL=jdbc:mysql://localhost:3306/projeto-bd?useSSL=false&serverTimezone=UTC
DB_USERNAME=root
DB_PASSWORD=sua_senha
```

> Exemplo disponível em `.env.example`

---

## 🧪 Teste de Conexão

```java
src/main/java/com/SpringBD/TesteConexao.java
```

---

## 🚀 Inicialização

```bash
mvn clean install
```

```bash
src/main/java/com/SpringBD/SpringBdApplication.java
```

---

## 📋 Funcionalidades

- CRUD completo para:
  - Cliente (com herança PF/PJ)
  - Funcionário
  - Festa
  - Tema
  - Pagamento
  - OrçamentoContrato
  - TelefonesCliente
  - Aniversariante (entidade fraca)
- Filtros por chaves e atributos
- Spring Boot + HTML + CSS + JavaScript
- Dashboard interativo com dados dinâmicos
- Scripts avançados (procedures, trigger, evento)

---

## 📊 Dashboards Disponíveis

- 👥 Total de clientes
- 💰 Faturamento no mês (com filtro)
- 🎈 Festas do mês atual
- 🎨 Temas mais utilizados
- 🎂 Aniversariantes nos próximos 45 dias
- 🧾 Resumo financeiro de contratos
- 📅 Festas por mês (com filtro de ano)

---

## 📄 Licença

Distribuído sob a licença [MIT](LICENSE).
