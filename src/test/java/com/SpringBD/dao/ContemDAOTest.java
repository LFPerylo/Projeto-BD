// ContemDAOTest.java
package com.SpringBD.dao;

import com.SpringBD.model.Contem;
import org.junit.jupiter.api.*;

import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ContemDAOTest {

    private static ContemDAO dao;
    private static final int COD_FESTA = 1;
    private static final int COD_ANIVERSARIANTE = 1;

    @BeforeAll
    public static void setup() throws SQLException {
        dao = new ContemDAO();
    }

    @Test
    @Order(1)
    public void testInserir() throws SQLException {
        Contem contem = new Contem(COD_FESTA, COD_ANIVERSARIANTE, 2025);
        dao.inserir(contem);

        Contem buscado = dao.buscarPorId(COD_FESTA, COD_ANIVERSARIANTE);
        assertNotNull(buscado);
        assertEquals(2025, buscado.getAno());
    }

    @Test
    @Order(2)
    public void testAtualizar() throws SQLException {
        Contem contem = new Contem(COD_FESTA, COD_ANIVERSARIANTE, 2026);
        dao.atualizar(contem);

        Contem atualizado = dao.buscarPorId(COD_FESTA, COD_ANIVERSARIANTE);
        assertNotNull(atualizado);
        assertEquals(2026, atualizado.getAno());
    }

    @Test
    @Order(3)
    public void testListar() throws SQLException {
        List<Contem> lista = dao.listarTodos();
        assertFalse(lista.isEmpty());
    }

    @Test
    @Order(4)
    public void testDeletar() throws SQLException {
        dao.deletar(COD_FESTA, COD_ANIVERSARIANTE);
        Contem contem = dao.buscarPorId(COD_FESTA, COD_ANIVERSARIANTE);
        assertNull(contem);
    }
}