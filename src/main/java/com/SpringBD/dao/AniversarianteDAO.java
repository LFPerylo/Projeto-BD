package com.SpringBD.dao;

import com.SpringBD.config.ConexaoBD;
import com.SpringBD.model.Aniversariante;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AniversarianteDAO {

    public int inserir(Aniversariante a) throws SQLException {
        String sql = "INSERT INTO Aniversariante (Nome, Idade, Data_Nascimento) VALUES (?, ?, ?)";

        try (Connection conn = ConexaoBD.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, a.getNome());
            stmt.setInt(2, a.getIdade());
            stmt.setDate(3, Date.valueOf(a.getDataNascimento()));

            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                return rs.getInt(1); // Retorna o Cod_Aniversariante auto_increment
            }
        }
        return -1; // Em caso de erro
    }

    public List<Aniversariante> listarTodos() throws SQLException {
        List<Aniversariante> lista = new ArrayList<>();
        String sql = "SELECT * FROM Aniversariante";

        try (Connection conn = ConexaoBD.conectar();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Aniversariante a = new Aniversariante();
                a.setCodAniversariante(rs.getInt("Cod_Aniversariante"));
                a.setNome(rs.getString("Nome"));
                a.setIdade(rs.getInt("Idade"));
                a.setDataNascimento(rs.getDate("Data_Nascimento").toLocalDate());
                lista.add(a);
            }
        }
        return lista;
    }

    public Aniversariante buscarPorId(int id) throws SQLException {
        String sql = "SELECT * FROM Aniversariante WHERE Cod_Aniversariante = ?";

        try (Connection conn = ConexaoBD.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Aniversariante a = new Aniversariante();
                a.setCodAniversariante(rs.getInt("Cod_Aniversariante"));
                a.setNome(rs.getString("Nome"));
                a.setIdade(rs.getInt("Idade"));
                a.setDataNascimento(rs.getDate("Data_Nascimento").toLocalDate());
                return a;
            }
        }
        return null;
    }

    public void atualizar(Aniversariante a) throws SQLException {
        String sql = "UPDATE Aniversariante SET Nome = ?, Idade = ?, Data_Nascimento = ? WHERE Cod_Aniversariante = ?";

        try (Connection conn = ConexaoBD.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, a.getNome());
            stmt.setInt(2, a.getIdade());
            stmt.setDate(3, Date.valueOf(a.getDataNascimento()));
            stmt.setInt(4, a.getCodAniversariante());

            stmt.executeUpdate();
        }
    }

    public void deletar(int id) throws SQLException {
        String sql = "DELETE FROM Aniversariante WHERE Cod_Aniversariante = ?";

        try (Connection conn = ConexaoBD.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    public List<Aniversariante> buscarPorCodFesta(int codFesta) throws SQLException {
        List<Aniversariante> lista = new ArrayList<>();
        String sql = """
        SELECT a.*
        FROM contem c
        JOIN aniversariante a ON c.cod_aniversariante = a.cod_aniversariante
        WHERE c.cod_festa = ?
    """;

        try (Connection conn = ConexaoBD.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, codFesta);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Aniversariante a = new Aniversariante();
                a.setCodAniversariante(rs.getInt("cod_aniversariante"));
                a.setNome(rs.getString("nome"));
                a.setIdade(rs.getInt("idade"));
                a.setDataNascimento(rs.getDate("data_nascimento").toLocalDate());
                lista.add(a);
            }
        }

        return lista;
    }

}
