// TesteTelefonesClienteDAO.java
package com.SpringBD.dao;

import com.SpringBD.dao.TelefonesClienteDAO;
import com.SpringBD.model.TelefonesCliente;
import org.junit.jupiter.api.*;

import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TesteTelefonesClienteDAO {

    private static final int COD_CLIENTE_EXISTENTE = 1; // Certifique-se que existe esse cliente no banco
    private static final String TELEFONE_TESTE = "(81) 99999-9999";

    private static final TelefonesClienteDAO dao = new TelefonesClienteDAO();

    @Test
    @Order(1)
    public void testInserir() throws SQLException {
        TelefonesCliente tel = new TelefonesCliente(COD_CLIENTE_EXISTENTE, TELEFONE_TESTE);
        dao.inserir(tel);

        List<TelefonesCliente> telefones = dao.listarTodos();
        boolean encontrado = telefones.stream()
                .anyMatch(t -> t.getCodCliente() == COD_CLIENTE_EXISTENTE && t.getTelefone().equals(TELEFONE_TESTE));
        assertTrue(encontrado, "Telefone deve ser inserido no banco de dados");
    }

    @Test
    @Order(2)
    public void testAtualizar() throws SQLException {
        // Simula "atualizar" trocando o número antigo por novo valor (como não há tipo, só reusa o número mesmo)
        TelefonesCliente tel = new TelefonesCliente(COD_CLIENTE_EXISTENTE, TELEFONE_TESTE);
        dao.atualizar(tel); // Atualiza sem mudanças reais, só valida que não dá erro

        List<TelefonesCliente> telefones = dao.listarTodos();
        boolean aindaExiste = telefones.stream()
                .anyMatch(t -> t.getCodCliente() == COD_CLIENTE_EXISTENTE && t.getTelefone().equals(TELEFONE_TESTE));
        assertTrue(aindaExiste, "Telefone ainda deve estar presente após atualização");
    }

    @Test
    @Order(3)
    public void testListarTodos() throws SQLException {
        List<TelefonesCliente> lista = dao.listarTodos();
        assertNotNull(lista);
        assertTrue(lista.size() > 0, "Deve haver ao menos um telefone cadastrado");
    }

    @Test
    @Order(4)
    public void testDeletar() throws SQLException {
        dao.deletar(TELEFONE_TESTE, COD_CLIENTE_EXISTENTE);

        List<TelefonesCliente> telefones = dao.listarTodos();
        boolean existe = telefones.stream()
                .anyMatch(t -> t.getCodCliente() == COD_CLIENTE_EXISTENTE && t.getTelefone().equals(TELEFONE_TESTE));
        assertFalse(existe, "Telefone deve ser excluído do banco de dados");
    }
}
