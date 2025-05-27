

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


