

CREATE PROCEDURE festas_por_mes(IN ano INT)
BEGIN
  SELECT 
    MONTH(Data_Festa) AS mes,
    COUNT(*) AS quantidade
  FROM orcamento_contrato
  WHERE YEAR(Data_Festa) = ano
  GROUP BY mes
  ORDER BY mes;
END;

CREATE PROCEDURE festas_por_mes_e_ano(IN ano INT, IN mes INT)
BEGIN
  SELECT 
    COUNT(*) AS quantidade
  FROM orcamento_contrato
  WHERE YEAR(Data_Festa) = ano AND MONTH(Data_Festa) = mes;
END;

