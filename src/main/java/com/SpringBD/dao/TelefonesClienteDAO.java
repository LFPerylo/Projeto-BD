// TelefonesClienteDAO.java
package com.SpringBD.dao;

import com.SpringBD.config.ConexaoBD;
import com.SpringBD.model.TelefonesCliente;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TelefonesClienteDAO {

    public void inserir(TelefonesCliente telefone) throws SQLException {
        String sql = "INSERT INTO Telefones_Cliente (telefone, Cod_Cliente) VALUES (?, ?)";

        try (Connection conn = ConexaoBD.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, telefone.getTelefone());
            stmt.setInt(2, telefone.getCodCliente());

            stmt.executeUpdate();
        }
    }

    public List<TelefonesCliente> listarTodos() throws SQLException {
        List<TelefonesCliente> lista = new ArrayList<>();
        String sql = "SELECT * FROM Telefones_Cliente";

        try (Connection conn = ConexaoBD.conectar();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                TelefonesCliente t = new TelefonesCliente();
                t.setTelefone(rs.getString("telefone"));
                t.setCodCliente(rs.getInt("Cod_Cliente"));
                lista.add(t);
            }
        }
        return lista;
    }

    public void atualizar(TelefonesCliente telefone) throws SQLException {
        String sql = "UPDATE Telefones_Cliente SET telefone=? WHERE telefone=? AND Cod_Cliente=?";
        try (Connection conn = ConexaoBD.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, telefone.getTelefone());
            stmt.setString(2, telefone.getTelefone());
            stmt.setInt(3, telefone.getCodCliente());

            stmt.executeUpdate();
        }
    }

    public void deletar(String telefone, int codCliente) throws SQLException {
        String sql = "DELETE FROM Telefones_Cliente WHERE telefone=? AND Cod_Cliente=?";

        try (Connection conn = ConexaoBD.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, telefone);
            stmt.setInt(2, codCliente);
            stmt.executeUpdate();
        }
    }
}
