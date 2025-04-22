// FuncionarioDAOTest.java
package com.SpringBD.dao;

import com.SpringBD.model.Funcionario;
import org.junit.jupiter.api.*;

import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class FuncionarioDAOTest {

    private static FuncionarioDAO dao;
    private static int idInserido;

    @BeforeAll
    public static void setup() {
        dao = new FuncionarioDAO();
    }

    @Test
    @Order(1)
    public void testInserirFuncionarioSemSupervisor() throws SQLException {
        Funcionario f = new Funcionario();
        f.setNome("Carlos Silva");
        f.setSupervisor(null);

        dao.inserir(f);

        List<Funcionario> lista = dao.listarTodos();
        Funcionario ultimo = lista.get(lista.size() - 1);
        idInserido = ultimo.getCodFuncionario();

        assertEquals("Carlos Silva", ultimo.getNome());
        assertNull(ultimo.getSupervisor());
    }

    @Test
    @Order(2)
    public void testAtualizarFuncionarioComSupervisor() throws SQLException {
        Funcionario novoSupervisor = new Funcionario();
        novoSupervisor.setNome("Supervisor Exemplo");
        dao.inserir(novoSupervisor);

        List<Funcionario> lista = dao.listarTodos();
        int idSupervisor = lista.get(lista.size() - 1).getCodFuncionario();

        Funcionario f = dao.buscarPorId(idInserido);
        f.setSupervisor(idSupervisor);
        dao.atualizar(f);

        Funcionario atualizado = dao.buscarPorId(idInserido);
        assertEquals(idSupervisor, atualizado.getSupervisor());
    }

    @Test
    @Order(3)
    public void testBuscarPorId() throws SQLException {
        Funcionario f = dao.buscarPorId(idInserido);
        assertNotNull(f);
        assertEquals("Carlos Silva", f.getNome());
    }

    @Test
    @Order(4)
    public void testDeletarFuncionarioComSubordinados() throws SQLException {
        // Remove vínculo dos subordinados antes da exclusão
        dao.deletar(idInserido);
        Funcionario apagado = dao.buscarPorId(idInserido);
        assertNull(apagado);
    }
}
