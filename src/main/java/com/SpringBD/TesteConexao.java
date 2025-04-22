package com.SpringBD;

import com.SpringBD.config.ConexaoBD;

import java.sql.Connection;
import java.sql.SQLException;

public class TesteConexao {
    public static void main(String[] args) {
        try (Connection conn = ConexaoBD.conectar()) {
            if (conn != null && !conn.isClosed()) {
                System.out.println("✅ Conexão estabelecida com sucesso!");
            } else {
                System.out.println("⚠️ Conexão está nula ou foi encerrada.");
            }
        } catch (SQLException e) {
            System.out.println("❌ Erro ao conectar com o banco de dados:");
            e.printStackTrace();
        }
    }
}
