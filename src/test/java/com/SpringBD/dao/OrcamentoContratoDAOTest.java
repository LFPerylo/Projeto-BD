// OrcamentoContratoDAOTest.java
package com.SpringBD.dao;

import com.SpringBD.model.OrcamentoContrato;
import org.junit.jupiter.api.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class OrcamentoContratoDAOTest {

    private static OrcamentoContratoDAO dao;
    private static int codOrcamentoInserido;
    private static int numContratoInserido;

    private static final int COD_TEMA = 1;
    private static final int COD_CLIENTE = 1;
    private static final int COD_FUNCIONARIO = 1;

    @BeforeAll
    public static void setup() {
        dao = new OrcamentoContratoDAO();
    }

    @Test
    @Order(1)
    void testInserir() throws Exception {
        OrcamentoContrato o = new OrcamentoContrato();
        o.setDataFesta(LocalDate.now().plusDays(30));
        o.setDiaSemana("Sábado");
        o.setHorarioFesta(LocalTime.of(18, 0));
        o.setValorInicial(BigDecimal.valueOf(2500));
        o.setLocalFesta("Espaço Kids");
        o.setCodTema(COD_TEMA);
        o.setCodCliente(COD_CLIENTE);

        Random rand = new Random();
        int contrato = 9000 + rand.nextInt(1000);
        o.setNumContrato(contrato);

        o.setDataAssinatura(LocalDate.now());
        o.setValorSinal(BigDecimal.valueOf(800));
        o.setCodFuncionario(COD_FUNCIONARIO);

        dao.inserir(o);

        List<OrcamentoContrato> lista = dao.listarTodos();
        OrcamentoContrato ultimo = lista.stream()
                .filter(or -> or.getNumContrato() == contrato)
                .findFirst()
                .orElseThrow();
        codOrcamentoInserido = ultimo.getCodOrcamento();
        numContratoInserido = contrato;

        assertEquals("Sábado", ultimo.getDiaSemana());
        assertEquals("Espaço Kids", ultimo.getLocalFesta());
    }

    @Test
    @Order(2)
    void testBuscarPorId() throws Exception {
        OrcamentoContrato o = dao.buscarPorId(codOrcamentoInserido, numContratoInserido);
        assertNotNull(o);
        assertEquals("Sábado", o.getDiaSemana());
    }

    @Test
    @Order(3)
    void testAtualizar() throws Exception {
        OrcamentoContrato o = dao.buscarPorId(codOrcamentoInserido, numContratoInserido);
        o.setDiaSemana("Domingo");
        o.setLocalFesta("Espaço Divertido");

        dao.atualizar(o);
        OrcamentoContrato atualizado = dao.buscarPorId(codOrcamentoInserido, numContratoInserido);
        assertEquals("Domingo", atualizado.getDiaSemana());
    }

    @Test
    @Order(4)
    void testDeletar() throws Exception {
        dao.deletar(codOrcamentoInserido, numContratoInserido);
        OrcamentoContrato o = dao.buscarPorId(codOrcamentoInserido, numContratoInserido);
        assertNull(o);
    }
}
