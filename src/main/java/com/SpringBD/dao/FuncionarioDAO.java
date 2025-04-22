package com.SpringBD.dao;

import com.SpringBD.config.ConexaoBD;
import com.SpringBD.model.Funcionario;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FuncionarioDAO {

    // CREATE
    public void inserir(Funcionario funcionario) throws SQLException {
        String sql = "INSERT INTO Funcionario (Nome, Supervisor) VALUES (?, ?)";

        try (Connection conn = ConexaoBD.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, funcionario.getNome());

            if (funcionario.getSupervisor() != null) {
                stmt.setInt(2, funcionario.getSupervisor());
            } else {
                stmt.setNull(2, Types.INTEGER);
            }

            stmt.executeUpdate();
        }
    }

    // READ
    public List<Funcionario> listarTodos() throws SQLException {
        List<Funcionario> lista = new ArrayList<>();
        String sql = "SELECT * FROM Funcionario";

        try (Connection conn = ConexaoBD.conectar();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Funcionario f = new Funcionario();
                f.setCodFuncionario(rs.getInt("Cod_Funcionario"));
                f.setNome(rs.getString("Nome"));
                int sup = rs.getInt("Supervisor");
                f.setSupervisor(rs.wasNull() ? null : sup);
                lista.add(f);
            }
        }

        return lista;
    }

    public Funcionario buscarPorId(int id) throws SQLException {
        String sql = "SELECT * FROM Funcionario WHERE Cod_Funcionario = ?";

        try (Connection conn = ConexaoBD.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Funcionario f = new Funcionario();
                f.setCodFuncionario(rs.getInt("Cod_Funcionario"));
                f.setNome(rs.getString("Nome"));
                int sup = rs.getInt("Supervisor");
                f.setSupervisor(rs.wasNull() ? null : sup);
                return f;
            }
        }

        return null;
    }

    // UPDATE
    public void atualizar(Funcionario funcionario) throws SQLException {
        String sql = "UPDATE Funcionario SET Nome = ?, Supervisor = ? WHERE Cod_Funcionario = ?";

        try (Connection conn = ConexaoBD.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, funcionario.getNome());

            if (funcionario.getSupervisor() != null) {
                stmt.setInt(2, funcionario.getSupervisor());
            } else {
                stmt.setNull(2, Types.INTEGER);
            }

            stmt.setInt(3, funcionario.getCodFuncionario());
            stmt.executeUpdate();
        }
    }

    // DELETE
    public void deletar(int codFuncionario) throws SQLException {
        try (Connection conn = ConexaoBD.conectar()) {
            // Atualiza os subordinados antes da exclusão
            String limparSupervisor = "UPDATE Funcionario SET Supervisor = NULL WHERE Supervisor = ?";
            try (PreparedStatement stmt = conn.prepareStatement(limparSupervisor)) {
                stmt.setInt(1, codFuncionario);
                stmt.executeUpdate();
            }

            // Exclui o funcionário
            String delete = "DELETE FROM Funcionario WHERE Cod_Funcionario = ?";
            try (PreparedStatement stmt = conn.prepareStatement(delete)) {
                stmt.setInt(1, codFuncionario);
                stmt.executeUpdate();
            }
        }
    }
}
