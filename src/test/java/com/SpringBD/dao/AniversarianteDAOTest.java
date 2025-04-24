// AniversarianteDAOTest.java
package com.SpringBD.dao;

import com.SpringBD.model.Aniversariante;
import org.junit.jupiter.api.*;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class AniversarianteDAOTest {

    private static AniversarianteDAO dao;
    private static int codInserido;

    @BeforeAll
    public static void setup() {
        dao = new AniversarianteDAO();
    }

    @Test
    @Order(1)
    public void testInserir() throws SQLException {
        Aniversariante a = new Aniversariante();
        a.setNome("Joãozinho Teste");
        a.setIdade(10);
        a.setDataNascimento(LocalDate.of(2014, 4, 20));

        dao.inserir(a);

        List<Aniversariante> lista = dao.listarTodos();
        codInserido = lista.get(lista.size() - 1).getCodAniversariante();

        assertTrue(codInserido > 0);
    }

    @Test
    @Order(2)
    public void testBuscarPorId() throws SQLException {
        Aniversariante a = dao.buscarPorId(codInserido);
        assertNotNull(a);
        assertEquals("Joãozinho Teste", a.getNome());
    }

    @Test
    @Order(3)
    public void testAtualizar() throws SQLException {
        Aniversariante a = dao.buscarPorId(codInserido);
        a.setNome("João Atualizado");
        dao.atualizar(a);

        Aniversariante atualizado = dao.buscarPorId(codInserido);
        assertEquals("João Atualizado", atualizado.getNome());
    }

    @Test
    @Order(4)
    public void testDeletar() throws SQLException {
        dao.deletar(codInserido);
        Aniversariante a = dao.buscarPorId(codInserido);
        assertNull(a);
    }
}
