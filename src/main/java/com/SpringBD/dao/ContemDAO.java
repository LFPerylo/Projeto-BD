// ContemDAO.java
package com.SpringBD.dao;

import com.SpringBD.config.ConexaoBD;
import com.SpringBD.model.Contem;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ContemDAO {

    public void inserir(Contem contem) throws SQLException {
        String sql = "INSERT INTO Contem (Cod_Festa, Cod_Aniversariante, Ano) VALUES (?, ?, ?)";

        try (Connection conn = ConexaoBD.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, contem.getCodFesta());
            stmt.setInt(2, contem.getCodAniversariante());
            stmt.setInt(3, contem.getAno());
            stmt.executeUpdate();
        }
    }

    public List<Contem> listarTodos() throws SQLException {
        List<Contem> lista = new ArrayList<>();
        String sql = "SELECT * FROM Contem";

        try (Connection conn = ConexaoBD.conectar();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Contem contem = new Contem();
                contem.setCodFesta(rs.getInt("Cod_Festa"));
                contem.setCodAniversariante(rs.getInt("Cod_Aniversariante"));
                contem.setAno(rs.getInt("Ano"));
                lista.add(contem);
            }
        }
        return lista;
    }

    public Contem buscarPorId(int codFesta, int codAniversariante) throws SQLException {
        String sql = "SELECT * FROM Contem WHERE Cod_Festa = ? AND Cod_Aniversariante = ?";

        try (Connection conn = ConexaoBD.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, codFesta);
            stmt.setInt(2, codAniversariante);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Contem(
                        rs.getInt("Cod_Festa"),
                        rs.getInt("Cod_Aniversariante"),
                        rs.getInt("Ano")
                );
            }
        }
        return null;
    }

    public void atualizar(Contem contem) throws SQLException {
        String sql = "UPDATE Contem SET Ano = ? WHERE Cod_Festa = ? AND Cod_Aniversariante = ?";

        try (Connection conn = ConexaoBD.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, contem.getAno());
            stmt.setInt(2, contem.getCodFesta());
            stmt.setInt(3, contem.getCodAniversariante());
            stmt.executeUpdate();
        }
    }

    public void deletar(int codFesta, int codAniversariante) throws SQLException {
        String sql = "DELETE FROM Contem WHERE Cod_Festa = ? AND Cod_Aniversariante = ?";

        try (Connection conn = ConexaoBD.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, codFesta);
            stmt.setInt(2, codAniversariante);
            stmt.executeUpdate();
        }
    }
}