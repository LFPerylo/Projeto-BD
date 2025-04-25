package com.SpringBD.dao;

import com.SpringBD.dao.AniversarianteDAO;
import com.SpringBD.dao.ContemDAO;
import com.SpringBD.dao.FestaDAO;
import com.SpringBD.model.Aniversariante;
import com.SpringBD.model.Contem;
import com.SpringBD.model.Festa;
import org.junit.jupiter.api.*;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ContemDAOTest {

    private static FestaDAO festaDAO;
    private static AniversarianteDAO aniversarianteDAO;
    private static ContemDAO contemDAO;

    private static int codFesta;
    private static int codAniversariante;

    @BeforeAll
    public static void setup() throws SQLException {
        festaDAO = new FestaDAO();
        aniversarianteDAO = new AniversarianteDAO();
        contemDAO = new ContemDAO();

        // Criar uma festa de verdade
        Festa festa = new Festa();
        festa.setNumContrato(123456);
        festa.setHorarioFim(LocalTime.of(22, 0));
        festa.setCodTema(1); // assume que existe

        codFesta = festaDAO.inserir(festa);

        // Criar um aniversariante de verdade
        Aniversariante aniversariante = new Aniversariante("Contem Teste", 10, LocalDate.of(2014, 1, 1));
        codAniversariante = aniversarianteDAO.inserir(aniversariante);
    }

    @Test
    @Order(1)
    public void testInserirContem() throws SQLException {
        Contem c = new Contem(codFesta, codAniversariante, 2024);
        contemDAO.inserir(c);

        Contem result = contemDAO.buscarPorId(codFesta, codAniversariante);
        Assertions.assertNotNull(result);
        Assertions.assertEquals(2024, result.getAno());
    }

    @Test
    @Order(2)
    public void testAtualizarEDeletar() throws SQLException {
        Contem atualizado = new Contem(codFesta, codAniversariante, 2025);
        contemDAO.atualizar(atualizado);

        Contem atualizadoBanco = contemDAO.buscarPorId(codFesta, codAniversariante);
        Assertions.assertEquals(2025, atualizadoBanco.getAno());

        contemDAO.deletar(codFesta, codAniversariante);
        Contem deletado = contemDAO.buscarPorId(codFesta, codAniversariante);
        Assertions.assertNull(deletado);
    }

    @AfterAll
    public static void cleanup() throws SQLException {
        festaDAO.deletar(codFesta);
        aniversarianteDAO.deletar(codAniversariante);
    }
}
