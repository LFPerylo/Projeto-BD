package com.SpringBD.dao;

import com.SpringBD.config.ConexaoBD;
import com.SpringBD.model.OrcamentoContrato;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrcamentoContratoDAO {

    // CREATE
    public void inserir(OrcamentoContrato o) throws SQLException {
        String sql = "INSERT INTO Orcamento_Contrato (Data_Festa, Dia_Semana, Horario_Festa, Valor_Inicial, Local_Festa, Cod_Tema, Cod_Cliente, Num_Contrato, Data_Assinatura, Valor_Sinal, Cod_Funcionario) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = ConexaoBD.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setDate(1, Date.valueOf(o.getDataFesta()));
            stmt.setString(2, o.getDiaSemana());
            stmt.setTime(3, Time.valueOf(o.getHorarioFesta()));
            stmt.setBigDecimal(4, o.getValorInicial());
            stmt.setString(5, o.getLocalFesta());
            stmt.setInt(6, o.getCodTema());
            stmt.setInt(7, o.getCodCliente());
            stmt.setInt(8, o.getNumContrato());
            stmt.setDate(9, Date.valueOf(o.getDataAssinatura()));
            stmt.setBigDecimal(10, o.getValorSinal());
            stmt.setInt(11, o.getCodFuncionario());

            stmt.executeUpdate();
        }
    }

    // READ
    public List<OrcamentoContrato> listarTodos() throws SQLException {
        List<OrcamentoContrato> lista = new ArrayList<>();
        String sql = "SELECT * FROM Orcamento_Contrato";

        try (Connection conn = ConexaoBD.conectar();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                OrcamentoContrato o = new OrcamentoContrato();
                o.setCodOrcamento(rs.getInt("Cod_Orcamento"));
                o.setDataFesta(rs.getDate("Data_Festa").toLocalDate());
                o.setDiaSemana(rs.getString("Dia_Semana"));
                o.setHorarioFesta(rs.getTime("Horario_Festa").toLocalTime());
                o.setValorInicial(rs.getBigDecimal("Valor_Inicial"));
                o.setLocalFesta(rs.getString("Local_Festa"));
                o.setCodTema(rs.getInt("Cod_Tema"));
                o.setCodCliente(rs.getInt("Cod_Cliente"));
                o.setNumContrato(rs.getInt("Num_Contrato"));
                o.setDataAssinatura(rs.getDate("Data_Assinatura").toLocalDate());
                o.setValorSinal(rs.getBigDecimal("Valor_Sinal"));
                o.setCodFuncionario(rs.getInt("Cod_Funcionario"));
                lista.add(o);
            }
        }
        return lista;
    }

    public OrcamentoContrato buscarPorId(int codOrcamento, int numContrato) throws SQLException {
        String sql = "SELECT * FROM Orcamento_Contrato WHERE Cod_Orcamento = ? AND Num_Contrato = ?";

        try (Connection conn = ConexaoBD.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, codOrcamento);
            stmt.setInt(2, numContrato);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                OrcamentoContrato o = new OrcamentoContrato();
                o.setCodOrcamento(rs.getInt("Cod_Orcamento"));
                o.setDataFesta(rs.getDate("Data_Festa").toLocalDate());
                o.setDiaSemana(rs.getString("Dia_Semana"));
                o.setHorarioFesta(rs.getTime("Horario_Festa").toLocalTime());
                o.setValorInicial(rs.getBigDecimal("Valor_Inicial"));
                o.setLocalFesta(rs.getString("Local_Festa"));
                o.setCodTema(rs.getInt("Cod_Tema"));
                o.setCodCliente(rs.getInt("Cod_Cliente"));
                o.setNumContrato(rs.getInt("Num_Contrato"));
                o.setDataAssinatura(rs.getDate("Data_Assinatura").toLocalDate());
                o.setValorSinal(rs.getBigDecimal("Valor_Sinal"));
                o.setCodFuncionario(rs.getInt("Cod_Funcionario"));
                return o;
            }
        }
        return null;
    }

    // UPDATE
    public void atualizar(OrcamentoContrato o) throws SQLException {
        String sql = "UPDATE Orcamento_Contrato SET Data_Festa=?, Dia_Semana=?, Horario_Festa=?, Valor_Inicial=?, Local_Festa=?, Cod_Tema=?, Cod_Cliente=?, Data_Assinatura=?, Valor_Sinal=?, Cod_Funcionario=? WHERE Cod_Orcamento=? AND Num_Contrato=?";

        try (Connection conn = ConexaoBD.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setDate(1, Date.valueOf(o.getDataFesta()));
            stmt.setString(2, o.getDiaSemana());
            stmt.setTime(3, Time.valueOf(o.getHorarioFesta()));
            stmt.setBigDecimal(4, o.getValorInicial());
            stmt.setString(5, o.getLocalFesta());
            stmt.setInt(6, o.getCodTema());
            stmt.setInt(7, o.getCodCliente());
            stmt.setDate(8, Date.valueOf(o.getDataAssinatura()));
            stmt.setBigDecimal(9, o.getValorSinal());
            stmt.setInt(10, o.getCodFuncionario());
            stmt.setInt(11, o.getCodOrcamento());
            stmt.setInt(12, o.getNumContrato());

            stmt.executeUpdate();
        }
    }

    // DELETE
    public void deletar(int codOrcamento, int numContrato) throws SQLException {
        String sql = "DELETE FROM Orcamento_Contrato WHERE Cod_Orcamento = ? AND Num_Contrato = ?";

        try (Connection conn = ConexaoBD.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, codOrcamento);
            stmt.setInt(2, numContrato);

            stmt.executeUpdate();
        }
    }

    public List<Map<String, Object>> buscarFestasPorMes(int ano) throws SQLException {
        String sql = "CALL festas_por_mes(?)";

        try (Connection conn = ConexaoBD.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, ano);
            ResultSet rs = stmt.executeQuery();

            List<Map<String, Object>> resultado = new ArrayList<>();
            while (rs.next()) {
                Map<String, Object> item = new HashMap<>();
                item.put("mes", rs.getInt("mes"));
                item.put("quantidade", rs.getInt("quantidade"));
                resultado.add(item);
            }
            return resultado;
        }
    }





}
