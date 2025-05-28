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
        String buscarAniversariantes = "SELECT Cod_Aniversariante FROM Contem WHERE Cod_Festa = ?";
        String verificarOutrasFestas = "SELECT COUNT(*) FROM Contem WHERE Cod_Aniversariante = ? AND Cod_Festa <> ?";
        String deletarAniversariante = "DELETE FROM Aniversariante WHERE Cod_Aniversariante = ?";
        String deletarContem = "DELETE FROM Contem WHERE Cod_Festa = ?";
        String deletarFesta = "DELETE FROM Festa WHERE Cod_Festa = ?";

        try (Connection conn = ConexaoBD.conectar()) {

            // 1. Buscar os aniversariantes dessa festa
            List<Integer> aniversariantes = new ArrayList<>();
            try (PreparedStatement stmt = conn.prepareStatement(buscarAniversariantes)) {
                stmt.setInt(1, codFesta);
                try (ResultSet rs = stmt.executeQuery()) {
                    while (rs.next()) {
                        aniversariantes.add(rs.getInt("Cod_Aniversariante"));
                    }
                }
            }

            // 2. Verificar se o aniversariante está vinculado a outras festas
            for (int codAniv : aniversariantes) {
                try (PreparedStatement stmt = conn.prepareStatement(verificarOutrasFestas)) {
                    stmt.setInt(1, codAniv);
                    stmt.setInt(2, codFesta);
                    try (ResultSet rs = stmt.executeQuery()) {
                        if (rs.next() && rs.getInt(1) == 0) {
                            // Se não estiver vinculado a outras festas, pode excluir da tabela aniversariante
                            try (PreparedStatement stmtDel = conn.prepareStatement(deletarAniversariante)) {
                                stmtDel.setInt(1, codAniv);
                                stmtDel.executeUpdate();
                            }
                        }
                    }
                }
            }

            // 3. Agora sim, excluir da tabela contem
            try (PreparedStatement stmt = conn.prepareStatement(deletarContem)) {
                stmt.setInt(1, codFesta);
                stmt.executeUpdate();
            }

            // 4. Por fim, excluir a festa
            try (PreparedStatement stmt = conn.prepareStatement(deletarFesta)) {
                stmt.setInt(1, codFesta);
                stmt.executeUpdate();
            }
        }
    }







}
