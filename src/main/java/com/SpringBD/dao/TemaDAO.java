package com.SpringBD.dao;

import com.SpringBD.config.ConexaoBD;
import com.SpringBD.model.Tema;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TemaDAO {

    public void inserir(Tema tema) throws SQLException {
        String sql = "INSERT INTO Tema (Nome, Descricao) VALUES (?, ?)";

        try (Connection conn = ConexaoBD.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, tema.getNome());
            stmt.setString(2, tema.getDescricao());
            stmt.executeUpdate();
        }
    }

    public List<Tema> listarTodos() throws SQLException {
        List<Tema> temas = new ArrayList<>();
        String sql = "SELECT * FROM Tema";

        try (Connection conn = ConexaoBD.conectar();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Tema t = new Tema();
                t.setCodTema(rs.getInt("Cod_Tema"));
                t.setNome(rs.getString("Nome"));
                t.setDescricao(rs.getString("Descricao"));
                temas.add(t);
            }
        }
        return temas;
    }

    public Tema buscarPorId(int id) throws SQLException {
        String sql = "SELECT * FROM Tema WHERE Cod_Tema = ?";

        try (Connection conn = ConexaoBD.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Tema(
                        rs.getInt("Cod_Tema"),
                        rs.getString("Nome"),
                        rs.getString("Descricao")
                );
            }
        }
        return null;
    }

    public void atualizar(Tema tema) throws SQLException {
        String sql = "UPDATE Tema SET Nome = ?, Descricao = ? WHERE Cod_Tema = ?";

        try (Connection conn = ConexaoBD.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, tema.getNome());
            stmt.setString(2, tema.getDescricao());
            stmt.setInt(3, tema.getCodTema());
            stmt.executeUpdate();
        }
    }

    public void deletar(int codTema) throws SQLException {
        String sql = "DELETE FROM Tema WHERE Cod_Tema = ?";

        try (Connection conn = ConexaoBD.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, codTema);
            stmt.executeUpdate();
        }
    }
}
