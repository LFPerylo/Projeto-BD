-- Criar tabela de alertas 
CREATE TABLE IF NOT EXISTS alerta_aniversario (
    cod_alerta INT AUTO_INCREMENT PRIMARY KEY,
    cod_aniversariante INT,
    nome VARCHAR(100),
    data_nascimento DATE,
    dias_restantes INT,
    idade INT,
    criado_em DATETIME DEFAULT CURRENT_TIMESTAMP
);

-- Procedure para detectar aniversariantes nos próximos 45 dias
DROP PROCEDURE IF EXISTS verificar_aniversariantes_proximos;

CREATE PROCEDURE verificar_aniversariantes_proximos()
BEGIN
    DECLARE hoje DATE;
    SET hoje = CURDATE();

    DELETE FROM alerta_aniversario; -- limpa alertas anteriores

    INSERT INTO alerta_aniversario (cod_aniversariante, nome, data_nascimento, dias_restantes, idade)
    SELECT
        Cod_Aniversariante,
        Nome,
        Data_Nascimento,
        DATEDIFF(
            STR_TO_DATE(CONCAT(YEAR(CURDATE()), '-', LPAD(MONTH(Data_Nascimento), 2, '0'), '-', LPAD(DAY(Data_Nascimento), 2, '0')), '%Y-%m-%d'),
            CURDATE()
        ) AS dias_restantes,
        TIMESTAMPDIFF(YEAR, Data_Nascimento, CURDATE()) AS idade
    FROM Aniversariante
    WHERE
        DATEDIFF(
            STR_TO_DATE(CONCAT(YEAR(CURDATE()), '-', LPAD(MONTH(Data_Nascimento), 2, '0'), '-', LPAD(DAY(Data_Nascimento), 2, '0')), '%Y-%m-%d'),
            CURDATE()
        ) BETWEEN 0 AND 45;
END;

-- Agendamento do evento para rodar diariamente
SET GLOBAL event_scheduler = ON;

CREATE EVENT IF NOT EXISTS checar_aniversariantes_diariamente
ON SCHEDULE EVERY 1 DAY
STARTS CURRENT_TIMESTAMP
DO
BEGIN
    CALL verificar_aniversariantes_proximos();
END;


-- trigger 
CREATE TRIGGER trg_alerta_aniversario_insert
AFTER INSERT ON Aniversariante
FOR EACH ROW
BEGIN
    DECLARE dias_restantes INT;
    DECLARE aniversario_ano_atual DATE;

    SET aniversario_ano_atual = STR_TO_DATE(
        CONCAT(YEAR(CURDATE()), '-', LPAD(MONTH(NEW.Data_Nascimento), 2, '0'), '-', LPAD(DAY(NEW.Data_Nascimento), 2, '0')),
        '%Y-%m-%d'
    );

    SET dias_restantes = DATEDIFF(aniversario_ano_atual, CURDATE());

    IF dias_restantes BETWEEN 0 AND 45 THEN
        INSERT INTO alerta_aniversario (cod_aniversariante, nome, data_nascimento, dias_restantes, idade)
        VALUES (
            NEW.Cod_Aniversariante,
            NEW.Nome,
            NEW.Data_Nascimento,
            dias_restantes,
            TIMESTAMPDIFF(YEAR, NEW.Data_Nascimento, CURDATE())
        );
    END IF;
END;







