// PagamentoDAO.java
package com.SpringBD.dao;

import com.SpringBD.config.ConexaoBD;
import com.SpringBD.model.Pagamento;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PagamentoDAO {

    public void inserir(Pagamento pagamento) throws SQLException {
        String sql = "INSERT INTO Pagamento (Valor_Final, Data_Pagamento, Forma_Pagamento, Valor_Acrescentado, Cod_Cliente, Num_Contrato) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = ConexaoBD.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setBigDecimal(1, pagamento.getValorFinal());
            stmt.setDate(2, Date.valueOf(pagamento.getDataPagamento()));
            stmt.setString(3, pagamento.getFormaPagamento());
            stmt.setBigDecimal(4, pagamento.getValorAcrescentado());
            stmt.setInt(5, pagamento.getCodCliente());
            stmt.setInt(6, pagamento.getNumContrato());

            stmt.executeUpdate();
        }
    }

    public List<Pagamento> listarTodos() throws SQLException {
        List<Pagamento> lista = new ArrayList<>();
        String sql = "SELECT * FROM Pagamento";

        try (Connection conn = ConexaoBD.conectar();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Pagamento p = new Pagamento();
                p.setCodPagamento(rs.getInt("Cod_Pagamento"));
                p.setValorFinal(rs.getBigDecimal("Valor_Final"));
                p.setDataPagamento(rs.getDate("Data_Pagamento").toLocalDate());
                p.setFormaPagamento(rs.getString("Forma_Pagamento"));
                p.setValorAcrescentado(rs.getBigDecimal("Valor_Acrescentado"));
                p.setCodCliente(rs.getInt("Cod_Cliente"));
                p.setNumContrato(rs.getInt("Num_Contrato"));
                lista.add(p);
            }
        }
        return lista;
    }

    public Pagamento buscarPorId(int codPagamento) throws SQLException {
        String sql = "SELECT * FROM Pagamento WHERE Cod_Pagamento = ?";

        try (Connection conn = ConexaoBD.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, codPagamento);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Pagamento p = new Pagamento();
                p.setCodPagamento(rs.getInt("Cod_Pagamento"));
                p.setValorFinal(rs.getBigDecimal("Valor_Final"));
                p.setDataPagamento(rs.getDate("Data_Pagamento").toLocalDate());
                p.setFormaPagamento(rs.getString("Forma_Pagamento"));
                p.setValorAcrescentado(rs.getBigDecimal("Valor_Acrescentado"));
                p.setCodCliente(rs.getInt("Cod_Cliente"));
                p.setNumContrato(rs.getInt("Num_Contrato"));
                return p;
            }
        }
        return null;
    }

    public void atualizar(Pagamento pagamento) throws SQLException {
        String sql = "UPDATE Pagamento SET Valor_Final=?, Data_Pagamento=?, Forma_Pagamento=?, Valor_Acrescentado=?, Cod_Cliente=?, Num_Contrato=? WHERE Cod_Pagamento=?";

        try (Connection conn = ConexaoBD.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setBigDecimal(1, pagamento.getValorFinal());
            stmt.setDate(2, Date.valueOf(pagamento.getDataPagamento()));
            stmt.setString(3, pagamento.getFormaPagamento());
            stmt.setBigDecimal(4, pagamento.getValorAcrescentado());
            stmt.setInt(5, pagamento.getCodCliente());
            stmt.setInt(6, pagamento.getNumContrato());
            stmt.setInt(7, pagamento.getCodPagamento());

            stmt.executeUpdate();
        }
    }

    public void deletar(int codPagamento) throws SQLException {
        String sql = "DELETE FROM Pagamento WHERE Cod_Pagamento = ?";

        try (Connection conn = ConexaoBD.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, codPagamento);
            stmt.executeUpdate();
        }
    }
}