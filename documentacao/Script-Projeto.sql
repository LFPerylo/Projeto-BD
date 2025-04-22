create database Projeto-BD;

create table Cliente (
	Cod_Cliente INT auto_increment primary key,
	Nome VARCHAR(100) not null,
	Email VARCHAR(100) not null,
	Rua VARCHAR(100) default null,
	Numero int default null,
	Bairro Varchar(50) default null,
	Cidade varchar(50) default null,
	Estado varchar(50) default null
	
);

create table Telefones_Cliente (
	Cod_Cliente INT not null,
	Telefone VARCHAR(20) not null,
	primary key (Cod_Cliente, Telefone),
	foreign key (Cod_Cliente) references Cliente(Cod_Cliente)

);

 
create table Pessoa_Fisica(
	Cod_Cliente int primary key,
	CPF varchar(14) unique not null,
	RG varchar(20) unique not null,
	foreign key (Cod_Cliente) references Cliente(Cod_Cliente)

);

create table Pessoa_Juridica(
	Cod_Cliente int primary key,
	CNPJ VARCHAR(18) unique not null,
	Inscricao_Estadual varchar(20) unique not null,
	Razao_Social varchar(100) not null,
	foreign key (Cod_Cliente) references Cliente(Cod_Cliente)


);

create table Funcionario(
	Cod_Funcionario int auto_increment primary key,
	Nome varchar(100) not null,
	Supervisor int default null,
	foreign key (Supervisor) references Funcionario(Cod_Funcionario)
	
);

create table Tema (
	Cod_Tema int auto_increment primary key,
	Nome varchar(100) unique not null,
	Descricao varchar(100) not null

);


create table Orcamento_Contrato (
	Cod_Orcamento int AUTO_INCREMENT,
	Data_Festa date not null,
	Dia_Semana varchar (15) not null,
	Horario_Festa time not null,
	Valor_Inicial decimal(10,2) not null,
	Local_Festa varchar(50) not null,
	Cod_Tema int not null,
	Cod_Cliente int not null,
	Num_Contrato int unique not null,
	Data_Assinatura date not null,
	Valor_Sinal decimal(10,2) not null,
	Cod_Funcionario int not null,
	primary key (Cod_Orcamento, Num_Contrato),
	foreign key (Cod_Cliente) references Cliente(Cod_Cliente),
	foreign key (Cod_Tema) references Tema(Cod_Tema),
	foreign key (Cod_Funcionario) references Funcionario(Cod_Funcionario)
	
);


create table Festa (
	Cod_Festa int AUTO_INCREMENT primary key,
	Num_Contrato int unique not null,
	Horario_Fim time not null,
	Cod_Tema int not null,
	foreign key (Cod_Tema) references Tema(Cod_Tema),
	foreign key (Num_Contrato) references Orcamento_Contrato(Num_Contrato)

);

create table Contem (
	Cod_Festa int,
	Cod_Aniversariante int,
	Ano int not null,
	primary key(Cod_Festa,Cod_Aniversariante),
	foreign key (Cod_Festa) references Festa(Cod_Festa),
	foreign key (Cod_Aniversariante) references Aniversariante(Cod_Aniversariante)

);


create table Aniversariante (
	Cod_Aniversariante int auto_increment primary key,
	Nome varchar(100) not null,
	idade int not null check (idade>0),
	Data_Nascimento date not null
	
);


CREATE TABLE Pagamento (
  Cod_Pagamento INT AUTO_INCREMENT PRIMARY KEY,
  Valor_Final DECIMAL(10,2) NOT NULL,
  Data_Pagamento DATE not null,
  Forma_Pagamento VARCHAR(30) DEFAULT 'Dinheiro',
  Valor_Acrescentado DECIMAL(10,2) DEFAULT 0,
  Cod_Cliente INT NOT NULL,
  Num_Contrato INT NOT NULL,
  FOREIGN KEY (Cod_Cliente) REFERENCES Cliente(Cod_Cliente),
  FOREIGN KEY (Num_Contrato) REFERENCES Orcamento_Contrato(Num_Contrato)
);


