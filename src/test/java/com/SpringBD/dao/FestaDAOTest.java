// FestaDAOTest.java
package com.SpringBD.dao;

import com.SpringBD.model.Festa;
import com.SpringBD.model.OrcamentoContrato;
import org.junit.jupiter.api.*;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.math.BigDecimal;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class FestaDAOTest {

    private static FestaDAO dao;
    private static int codFestaInserido;
    private static final int COD_TEMA = 1;
    private static final int NUM_CONTRATO = 9000 + new Random().nextInt(99);

    @BeforeAll
    public static void setup() throws SQLException {
        dao = new FestaDAO();

        // garantir contrato válido antes do teste
        OrcamentoContratoDAO contratoDAO = new OrcamentoContratoDAO();
        OrcamentoContrato contrato = new OrcamentoContrato();
        contrato.setDataFesta(LocalDate.now().plusDays(10));
        contrato.setDiaSemana("Sábado");
        contrato.setHorarioFesta(LocalTime.of(16, 0));
        contrato.setValorInicial(BigDecimal.valueOf(2000));
        contrato.setLocalFesta("Buffet Alegria");
        contrato.setCodTema(COD_TEMA);
        contrato.setCodCliente(1);
        contrato.setNumContrato(NUM_CONTRATO);
        contrato.setDataAssinatura(LocalDate.now());
        contrato.setValorSinal(BigDecimal.valueOf(500));
        contrato.setCodFuncionario(1);

        contratoDAO.inserir(contrato);
    }

    @Test
    @Order(1)
    public void testInserir() throws SQLException {
        Festa festa = new Festa();
        festa.setNumContrato(NUM_CONTRATO);
        festa.setHorarioFim(LocalTime.of(20, 30));
        festa.setCodTema(COD_TEMA);

        dao.inserir(festa);

        List<Festa> festas = dao.listarTodos();
        codFestaInserido = festas.get(festas.size() - 1).getCodFesta();

        assertTrue(codFestaInserido > 0);
    }

    @Test
    @Order(2)
    public void testBuscarPorId() throws SQLException {
        Festa festa = dao.buscarPorId(codFestaInserido);
        assertNotNull(festa);
        assertEquals(NUM_CONTRATO, festa.getNumContrato());
    }

    @Test
    @Order(3)
    public void testAtualizar() throws SQLException {
        Festa festa = dao.buscarPorId(codFestaInserido);
        festa.setHorarioFim(LocalTime.of(21, 0));
        dao.atualizar(festa);

        Festa atualizada = dao.buscarPorId(codFestaInserido);
        assertEquals("21:00", atualizada.getHorarioFim().toString());
    }

    @Test
    @Order(4)
    public void testDeletar() throws SQLException {
        dao.deletar(codFestaInserido);
        Festa deletada = dao.buscarPorId(codFestaInserido);
        assertNull(deletada);
    }
}
