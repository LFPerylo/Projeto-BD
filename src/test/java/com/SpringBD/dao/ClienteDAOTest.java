package com.SpringBD.dao;

import com.SpringBD.model.Cliente;
import com.SpringBD.model.PessoaFisica;
import com.SpringBD.model.PessoaJuridica;
import org.junit.jupiter.api.*;

import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ClienteDAOTest {

    private static ClienteDAO dao;
    private static int idPf;
    private static int idPj;

    @BeforeAll
    public static void setup() {
        dao = new ClienteDAO();
    }

    @Test
    @Order(1)
    public void testInserirPessoaFisica() throws SQLException {
        PessoaFisica pf = new PessoaFisica();
        pf.setNome("Teste PF");
        pf.setEmail("pf" + UUID.randomUUID() + "@email.com"); // evitar duplicação
        pf.setRua("Rua A");
        pf.setNumero(100);
        pf.setBairro("Centro");
        pf.setCidade("Recife");
        pf.setEstado("PE");
        pf.setCpf(UUID.randomUUID().toString().substring(0, 14)); // CPF aleatório
        pf.setRg("RG" + System.currentTimeMillis());

        dao.inserir(pf);

        List<Cliente> clientes = dao.listarTodos();
        idPf = clientes.get(clientes.size() - 1).getCodCliente();

        Cliente c = dao.buscarPorId(idPf);
        assertNotNull(c);
        assertEquals("Teste PF", c.getNome());
    }

    @Test
    @Order(2)
    public void testInserirPessoaJuridica() throws SQLException {
        PessoaJuridica pj = new PessoaJuridica();
        pj.setNome("Empresa PJ");
        pj.setEmail("pj" + UUID.randomUUID() + "@email.com");
        pj.setRua("Rua B");
        pj.setNumero(200);
        pj.setBairro("Boa Vista");
        pj.setCidade("Olinda");
        pj.setEstado("PE");
        pj.setCnpj(UUID.randomUUID().toString().substring(0, 18)); // CNPJ aleatório
        pj.setInscricaoEstadual("ISENTO");
        pj.setRazaoSocial("PJ LTDA");

        dao.inserir(pj);

        List<Cliente> clientes = dao.listarTodos();
        idPj = clientes.get(clientes.size() - 1).getCodCliente();

        Cliente c = dao.buscarPorId(idPj);
        assertNotNull(c);
        assertEquals("Empresa PJ", c.getNome());
    }

    @Test
    @Order(3)
    public void testAtualizarPessoaFisica() throws SQLException {
        Cliente cliente = dao.buscarPorId(idPf);
        assumeTrue(cliente != null, "Cliente Pessoa Física deve ter sido inserido antes.");

        cliente.setNome("PF Atualizado");
        cliente.setEmail("novo@email.com");

        dao.atualizar(cliente);

        Cliente atualizado = dao.buscarPorId(idPf);
        assertEquals("PF Atualizado", atualizado.getNome());
    }

    @Test
    @Order(4)
    public void testDeletarPessoaJuridica() throws SQLException {
        dao.deletar(idPj);
        Cliente deletado = dao.buscarPorId(idPj);
        assertNull(deletado);
    }
}
