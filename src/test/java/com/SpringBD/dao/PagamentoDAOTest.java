// PagamentoDAOTest.java
package com.SpringBD.dao;

import com.SpringBD.model.Pagamento;
import org.junit.jupiter.api.*;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class PagamentoDAOTest {

    private static PagamentoDAO dao;
    private static int codPagamento;
    private static final int COD_CLIENTE = 1;
    private static final int NUM_CONTRATO = 201;

    @BeforeAll
    public static void setup() throws SQLException {
        dao = new PagamentoDAO();
    }

    @Test
    @Order(1)
    public void testInserir() throws SQLException {
        Pagamento pagamento = new Pagamento();
        pagamento.setValorFinal(BigDecimal.valueOf(1500));
        pagamento.setDataPagamento(LocalDate.of(2025, 5, 22));
        pagamento.setFormaPagamento("PIX");
        pagamento.setValorAcrescentado(BigDecimal.valueOf(100));
        pagamento.setCodCliente(COD_CLIENTE);
        pagamento.setNumContrato(NUM_CONTRATO);

        dao.inserir(pagamento);

        List<Pagamento> lista = dao.listarTodos();
        codPagamento = lista.get(lista.size() - 1).getCodPagamento();

        assertTrue(codPagamento > 0);
    }

    @Test
    @Order(2)
    public void testBuscarPorId() throws SQLException {
        Pagamento pagamento = dao.buscarPorId(codPagamento);
        assertNotNull(pagamento);
        assertEquals("PIX", pagamento.getFormaPagamento());
    }

    @Test
    @Order(3)
    public void testAtualizar() throws SQLException {
        Pagamento pagamento = dao.buscarPorId(codPagamento);
        pagamento.setFormaPagamento("Cartao");
        pagamento.setValorFinal(BigDecimal.valueOf(1800));

        dao.atualizar(pagamento);

        Pagamento atualizado = dao.buscarPorId(codPagamento);
        assertEquals("Cartao", atualizado.getFormaPagamento());
    }

    @Test
    @Order(4)
    public void testDeletar() throws SQLException {
        dao.deletar(codPagamento);
        Pagamento deletado = dao.buscarPorId(codPagamento);
        assertNull(deletado);
    }
}
