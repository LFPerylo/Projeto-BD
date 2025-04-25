package com.SpringBD.dao;

import com.SpringBD.dao.AniversarianteDAO;
import com.SpringBD.model.Aniversariante;
import org.junit.jupiter.api.*;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class AniversarianteDAOTest {

    private static AniversarianteDAO dao;
    private static int id;

    @BeforeAll
    public static void setup() {
        dao = new AniversarianteDAO();
    }

    @Test
    @Order(1)
    public void testInserirAniversariante() throws SQLException {
        Aniversariante a = new Aniversariante("Isolado Teste", 9, LocalDate.of(2015, 2, 10));
        id = dao.inserir(a);
        Assertions.assertTrue(id > 0);
    }

    @Test
    @Order(2)
    public void testBuscarAtualizarDeletar() throws SQLException {
        Aniversariante a = dao.buscarPorId(id);
        Assertions.assertNotNull(a);

        a.setNome("Atualizado");
        dao.atualizar(a);

        Aniversariante atualizado = dao.buscarPorId(id);
        Assertions.assertEquals("Atualizado", atualizado.getNome());

        dao.deletar(id);
        Assertions.assertNull(dao.buscarPorId(id));
    }
}
