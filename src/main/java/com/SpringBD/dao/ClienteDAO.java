package com.SpringBD.dao;

import com.SpringBD.config.ConexaoBD;
import com.SpringBD.model.Cliente;
import com.SpringBD.model.PessoaFisica;
import com.SpringBD.model.PessoaJuridica;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO {

    // CREATE
    public void inserir(Cliente cliente) throws SQLException {
        String sqlCliente = "INSERT INTO Cliente (Nome, Email, Rua, Numero, Bairro, Cidade, Estado) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = ConexaoBD.conectar();
             PreparedStatement stmt = conn.prepareStatement(sqlCliente, PreparedStatement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, cliente.getNome());
            stmt.setString(2, cliente.getEmail());
            stmt.setString(3, cliente.getRua());
            stmt.setInt(4, cliente.getNumero());
            stmt.setString(5, cliente.getBairro());
            stmt.setString(6, cliente.getCidade());
            stmt.setString(7, cliente.getEstado());

            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            int codCliente = -1;
            if (rs.next()) codCliente = rs.getInt(1);

            if (cliente instanceof PessoaFisica pf) {
                inserirPessoaFisica(codCliente, pf, conn);
            } else if (cliente instanceof PessoaJuridica pj) {
                inserirPessoaJuridica(codCliente, pj, conn);
            }
        }
    }

    private void inserirPessoaFisica(int codCliente, PessoaFisica pf, Connection conn) throws SQLException {
        String sql = "INSERT INTO Pessoa_Fisica (Cod_Cliente, CPF, RG) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, codCliente);
            stmt.setString(2, pf.getCpf());
            stmt.setString(3, pf.getRg());
            stmt.executeUpdate();
        }
    }

    private void inserirPessoaJuridica(int codCliente, PessoaJuridica pj, Connection conn) throws SQLException {
        String sql = "INSERT INTO Pessoa_Juridica (Cod_Cliente, CNPJ, Inscricao_Estadual, Razao_Social) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, codCliente);
            stmt.setString(2, pj.getCnpj());
            stmt.setString(3, pj.getInscricaoEstadual());
            stmt.setString(4, pj.getRazaoSocial());
            stmt.executeUpdate();
        }
    }

    // READ
    public List<Cliente> listarTodos() throws SQLException {
        List<Cliente> lista = new ArrayList<>();
        String sql = "SELECT * FROM Cliente";

        try (Connection conn = ConexaoBD.conectar();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Cliente cliente = new Cliente();
                cliente.setCodCliente(rs.getInt("Cod_Cliente"));
                cliente.setNome(rs.getString("Nome"));
                cliente.setEmail(rs.getString("Email"));
                cliente.setRua(rs.getString("Rua"));
                cliente.setNumero(rs.getInt("Numero"));
                cliente.setBairro(rs.getString("Bairro"));
                cliente.setCidade(rs.getString("Cidade"));
                cliente.setEstado(rs.getString("Estado"));
                lista.add(cliente);
            }
        }
        return lista;
    }

    public Cliente buscarPorId(int id) throws SQLException {
        String sql = "SELECT * FROM Cliente WHERE Cod_Cliente = ?";
        try (Connection conn = ConexaoBD.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Cliente cliente = new Cliente();
                cliente.setCodCliente(rs.getInt("Cod_Cliente"));
                cliente.setNome(rs.getString("Nome"));
                cliente.setEmail(rs.getString("Email"));
                cliente.setRua(rs.getString("Rua"));
                cliente.setNumero(rs.getInt("Numero"));
                cliente.setBairro(rs.getString("Bairro"));
                cliente.setCidade(rs.getString("Cidade"));
                cliente.setEstado(rs.getString("Estado"));
                return cliente;
            }
            return null;
        }
    }

    // UPDATE
    public void atualizar(Cliente cliente) throws SQLException {
        String sql = "UPDATE Cliente SET Nome=?, Email=?, Rua=?, Numero=?, Bairro=?, Cidade=?, Estado=? WHERE Cod_Cliente=?";
        try (Connection conn = ConexaoBD.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, cliente.getNome());
            stmt.setString(2, cliente.getEmail());
            stmt.setString(3, cliente.getRua());
            stmt.setInt(4, cliente.getNumero());
            stmt.setString(5, cliente.getBairro());
            stmt.setString(6, cliente.getCidade());
            stmt.setString(7, cliente.getEstado());
            stmt.setInt(8, cliente.getCodCliente());

            stmt.executeUpdate();
        }
    }

    // DELETE
    public void deletar(int codCliente) throws SQLException {
        String deletePF = "DELETE FROM Pessoa_Fisica WHERE Cod_Cliente = ?";
        String deletePJ = "DELETE FROM Pessoa_Juridica WHERE Cod_Cliente = ?";
        String deleteCliente = "DELETE FROM Cliente WHERE Cod_Cliente = ?";

        try (Connection conn = ConexaoBD.conectar()) {

            // Deleta da tabela Pessoa_Fisica
            try (PreparedStatement stmt = conn.prepareStatement(deletePF)) {
                stmt.setInt(1, codCliente);
                stmt.executeUpdate();
            }

            // Deleta da tabela Pessoa_Juridica
            try (PreparedStatement stmt = conn.prepareStatement(deletePJ)) {
                stmt.setInt(1, codCliente);
                stmt.executeUpdate();
            }

            // Finalmente, deleta da tabela Cliente
            try (PreparedStatement stmt = conn.prepareStatement(deleteCliente)) {
                stmt.setInt(1, codCliente);
                stmt.executeUpdate();
            }
        }
    }
}
