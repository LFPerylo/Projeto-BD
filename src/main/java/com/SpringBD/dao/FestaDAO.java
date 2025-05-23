package com.SpringBD.dao;

import com.SpringBD.config.ConexaoBD;
import com.SpringBD.model.Festa;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FestaDAO {

    public int inserir(Festa festa) throws SQLException {
        String sql = "INSERT INTO Festa (Num_Contrato, Horario_Fim, Cod_Tema) VALUES (?, ?, ?)";

        try (Connection conn = ConexaoBD.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setInt(1, festa.getNumContrato());
            stmt.setTime(2, Time.valueOf(festa.getHorarioFim()));
            stmt.setInt(3, festa.getCodTema());

            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                return rs.getInt(1); // retorna Cod_Festa gerado (auto_increment)
            }
        }
        return -1; // erro
    }

    public List<Festa> listarTodos() throws SQLException {
        List<Festa> lista = new ArrayList<>();
        String sql = "SELECT * FROM Festa";

        try (Connection conn = ConexaoBD.conectar();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Festa f = new Festa();
                f.setCodFesta(rs.getInt("Cod_Festa"));
                f.setNumContrato(rs.getInt("Num_Contrato"));
                f.setHorarioFim(rs.getTime("Horario_Fim").toLocalTime());
                f.setCodTema(rs.getInt("Cod_Tema"));
                lista.add(f);
            }
        }
        return lista;
    }

    public Festa buscarPorId(int codFesta) throws SQLException {
        String sql = "SELECT * FROM Festa WHERE Cod_Festa = ?";

        try (Connection conn = ConexaoBD.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, codFesta);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Festa(
                        rs.getInt("Cod_Festa"),
                        rs.getInt("Num_Contrato"),
                        rs.getTime("Horario_Fim").toLocalTime(),
                        rs.getInt("Cod_Tema")
                );
            }
        }
        return null;
    }

    public void atualizar(Festa festa) throws SQLException {
        String sql = "UPDATE Festa SET Num_Contrato=?, Horario_Fim=?, Cod_Tema=? WHERE Cod_Festa=?";

        try (Connection conn = ConexaoBD.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, festa.getNumContrato());
            stmt.setTime(2, Time.valueOf(festa.getHorarioFim()));
            stmt.setInt(3, festa.getCodTema());
            stmt.setInt(4, festa.getCodFesta());
            stmt.executeUpdate();
        }
    }

    public void deletar(int codFesta) throws SQLException {
        String sql = "DELETE FROM Festa WHERE Cod_Festa = ?";

        try (Connection conn = ConexaoBD.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, codFesta);
            stmt.executeUpdate();
        }
    }


}
