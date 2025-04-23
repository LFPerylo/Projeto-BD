// TemaDAOTest.java
package com.SpringBD.dao;

import com.SpringBD.model.Tema;
import org.junit.jupiter.api.*;

import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TemaDAOTest {

    private static TemaDAO dao;
    private static int idTema;

    @BeforeAll
    public static void setup() {
        dao = new TemaDAO();
    }

    @Test
    @Order(1)
    public void testInserirTema() throws SQLException {
        Tema tema = new Tema(0, "Tema DAO Teste", "Teste via DAO");
        dao.inserir(tema);
        List<Tema> temas = dao.listarTodos();
        assertFalse(temas.isEmpty());
        idTema = temas.get(temas.size() - 1).getCodTema();
    }

    @Test
    @Order(2)
    public void testBuscarPorId() throws SQLException {
        Tema tema = dao.buscarPorId(idTema);
        assertNotNull(tema);
        assertEquals("Tema DAO Teste", tema.getNome());
    }

    @Test
    @Order(3)
    public void testAtualizarTema() throws SQLException {
        Tema tema = new Tema(idTema, "Tema DAO Atualizado", "Descrição atualizada");
        dao.atualizar(tema);
        Tema atualizado = dao.buscarPorId(idTema);
        assertEquals("Tema DAO Atualizado", atualizado.getNome());
    }

    @Test
    @Order(4)
    public void testDeletarTema() throws SQLException {
        dao.deletar(idTema);
        Tema deletado = dao.buscarPorId(idTema);
        assertNull(deletado);
    }
}
