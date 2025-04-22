
INSERT INTO Cliente (Nome, Email, Rua, Numero, Bairro, Cidade, Estado)
VALUES
('Ana Silva', 'ana@gmail.com', 'Rua A', 101, 'Centro', 'Recife', 'PE'),
('Carlos Lima', 'carlos@gmail.com', 'Rua B', 202, 'Boa Viagem', 'Recife', 'PE'),
('Luciana Souza', 'luciana@gmail.com', 'Rua C', 303, 'Derby', 'Recife', 'PE'),
('Beatriz Rocha', 'bia@gmail.com', 'Rua D', 404, 'Pina', 'Recife', 'PE'),
('João Pedro', 'joao@gmail.com', 'Rua E', 505, 'Casa Forte', 'Recife', 'PE'),
('Empresa XYZ', 'contato@xyz.com', 'Av Central', 600, 'Centro', 'Olinda', 'PE'),
('Mariana Freitas', 'mariana@gmail.com', 'Rua F', 707, 'Boa Vista', 'Olinda', 'PE'),
('Lucas Almeida', 'lucas@gmail.com', 'Rua G', 808, 'Cohab', 'Jaboatão', 'PE'),
('Fernanda Dias', 'fernanda@gmail.com', 'Rua H', 909, 'Centro', 'Jaboatão', 'PE'),
('Instituto Alpha', 'alpha@org.com', 'Rua I', 1001, 'Centro', 'Recife', 'PE');

INSERT INTO Telefones_Cliente (Cod_Cliente, Telefone)
VALUES
(1, '81999990001'),
(2, '81999990002'),
(3, '81999990003'),
(4, '81999990004'),
(5, '81999990005'),
(6, '81999990006'),
(7, '81999990007'),
(8, '81999990008'),
(9, '81999990009'),
(10, '81999990010');

INSERT INTO Pessoa_Fisica (Cod_Cliente, CPF, RG)
VALUES
(1, '11111111111', '1234567'),
(2, '22222222222', '2345678'),
(3, '33333333333', '3456789'),
(4, '44444444444', '4567890'),
(5, '55555555555', '5678901'),
(7, '77777777777', '7890123'),
(8, '88888888888', '8901234'),
(9, '99999999999', '9012345');

INSERT INTO Pessoa_Juridica (Cod_Cliente, CNPJ, Inscricao_Estadual, Razao_Social)
VALUES
(6, '12345678000101', '123456789', 'Empresa XYZ Ltda'),
(10, '98765432000102', '987654321', 'Instituto Alpha');

INSERT INTO Tema (Nome, Descricao)
VALUES
('Safari', 'Festa com animais da selva'),
('Princesas', 'Festa encantada'),
('Super-heróis', 'Heróis da Marvel e DC'),
('Fazendinha', 'Festa com tema rural'),
('Espacial', 'Astronautas e planetas'),
('Unicórnios', 'Festa colorida e mágica'),
('Circo', 'Festa com tema circense'),
('Dinossauros', 'Aventura jurássica'),
('Harry Potter', 'Festa mágica de Hogwarts'),
('Carros', 'Corrida e velocidade');

INSERT INTO Funcionario (Nome, Supervisor)
VALUES
('João Supervisor', NULL),   -- ID = 1
('Maria Assistente', 1),
('Carlos Lima', 1),
('Beatriz Souza', 2),
('Rafael Santos', 2),
('Larissa Ramos', 1),
('Juliana Paz', 1),
('Felipe Gomes', 3),
('Roberta Melo', 3),
('André Carvalho', 1);

INSERT INTO Orcamento_Contrato (
  Data_Festa, Dia_Semana, Horario_Festa, Valor_Inicial, Local_Festa,
  Cod_Tema, Cod_Cliente, Num_Contrato, Data_Assinatura, Valor_Sinal, Cod_Funcionario
) VALUES
('2025-06-01', 'Sábado', '16:00:00', 1500.00, 'Buffet Alegria', 1, 1, 201, '2025-05-01', 500.00, 1),
('2025-06-02', 'Domingo', '17:00:00', 1600.00, 'Buffet Festa Feliz', 2, 2, 202, '2025-05-02', 600.00, 2),
('2025-06-03', 'Segunda', '18:00:00', 1700.00, 'Buffet Diversão', 3, 3, 203, '2025-05-03', 700.00, 1),
('2025-06-04', 'Terça', '19:00:00', 1800.00, 'Buffet Encanto', 4, 4, 204, '2025-05-04', 800.00, 2),
('2025-06-05', 'Quarta', '16:00:00', 1900.00, 'Buffet Sonho', 5, 5, 205, '2025-05-05', 900.00, 1),
('2025-06-06', 'Quinta', '17:00:00', 2000.00, 'Buffet Magia', 6, 6, 206, '2025-05-06', 1000.00, 2),
('2025-06-07', 'Sexta', '18:00:00', 2100.00, 'Buffet Alegria', 7, 7, 207, '2025-05-07', 1100.00, 1),
('2025-06-08', 'Sábado', '19:00:00', 2200.00, 'Buffet Show', 8, 8, 208, '2025-05-08', 1200.00, 2),
('2025-06-09', 'Domingo', '20:00:00', 2300.00, 'Buffet Kids', 9, 9, 209, '2025-05-09', 1300.00, 1),
('2025-06-10', 'Segunda', '15:00:00', 2400.00, 'Buffet Master', 10, 10, 210, '2025-05-10', 1400.00, 2);

INSERT INTO Festa (Num_Contrato, Horario_Fim, Cod_Tema)
VALUES
(201, '20:00:00', 1),
(202, '21:00:00', 2),
(203, '19:30:00', 3),
(204, '18:00:00', 4),
(205, '19:00:00', 5),
(206, '20:30:00', 6),
(207, '17:30:00', 7),
(208, '21:00:00', 8),
(209, '18:45:00', 9),
(210, '20:15:00', 10);

INSERT INTO Aniversariante (Nome, idade, Data_Nascimento)
VALUES
('Lucas', 6, '2018-04-10'),
('Lara', 5, '2019-03-15'),
('Bruno', 7, '2017-08-01'),
('Amanda', 4, '2020-09-20'),
('Felipe', 5, '2019-07-25'),
('Cecília', 6, '2018-10-30'),
('Arthur', 8, '2016-05-05'),
('Marina', 5, '2019-12-12'),
('Caio', 9, '2015-11-11'),
('Júlia', 6, '2018-01-22');

INSERT INTO Contem (Cod_Festa, Cod_Aniversariante, Ano)
VALUES
(1, 1, 2025),
(2, 2, 2025),
(3, 3, 2025),
(4, 4, 2025),
(5, 5, 2025),
(6, 6, 2025),
(7, 7, 2025),
(8, 8, 2025),
(9, 9, 2025),
(10, 10, 2025);

INSERT INTO Pagamento (
  Valor_Final, Data_Pagamento, Forma_Pagamento, Valor_Acrescentado,
  Cod_Cliente, Num_Contrato
) VALUES
(2000.00, '2025-05-20', 'Pix', 500.00, 1, 201),
(2200.00, '2025-05-21', 'Cartão', 600.00, 2, 202),
(2300.00, '2025-05-22', 'Boleto', 600.00, 3, 203),
(2100.00, '2025-05-23', 'Dinheiro', 300.00, 4, 204),
(2400.00, '2025-05-24', 'Pix', 500.00, 5, 205),
(2500.00, '2025-05-25', 'Cartão', 500.00, 6, 206),
(2600.00, '2025-05-26', 'Pix', 500.00, 7, 207),
(2700.00, '2025-05-27', 'Dinheiro', 600.00, 8, 208),
(2800.00, '2025-05-28', 'Cartão', 500.00, 9, 209),
(2900.00, '2025-05-29', 'Boleto', 500.00, 10, 210);