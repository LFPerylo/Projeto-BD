package com.SpringBD.service;

import com.SpringBD.dao.*;
import com.SpringBD.model.*;
import com.SpringBD.service.FestaService;
import org.junit.jupiter.api.*;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class FestaServiceTest {

    private static FestaService service;
    private static FestaDAO festaDAO;
    private static TemaDAO temaDAO;
    private static ClienteDAO clienteDAO;
    private static FuncionarioDAO funcionarioDAO;
    private static OrcamentoContratoDAO orcamentoDAO;
    private static AniversarianteDAO aniversarianteDAO;
    private static ContemDAO contemDAO;

    private static int codFesta;
    private static int codTema;
    private static int codCliente;
    private static int codFuncionario;
    private static int codOrcamento;
    private static int codAniversariante1;
    private static int codAniversariante2;

    @BeforeAll
    public static void setup() throws SQLException {
        service = new FestaService();
        festaDAO = new FestaDAO();
        temaDAO = new TemaDAO();
        clienteDAO = new ClienteDAO();
        funcionarioDAO = new FuncionarioDAO();
        orcamentoDAO = new OrcamentoContratoDAO();
        aniversarianteDAO = new AniversarianteDAO();
        contemDAO = new ContemDAO();

        // Tema com nome único
        String nomeTema = "Teste Tema " + System.currentTimeMillis();
        Tema tema = new Tema(nomeTema, "Descrição dinâmica");
        temaDAO.inserir(tema);
        List<Tema> temas = temaDAO.listarTodos();
        codTema = temas.get(temas.size() - 1).getCodTema();

        // Cliente com e-mail único
        Cliente cliente = new Cliente();
        cliente.setNome("Cliente Teste");
        cliente.setEmail("teste" + System.currentTimeMillis() + "@email.com");
        cliente.setRua("Rua A");
        cliente.setNumero(100);
        cliente.setBairro("Bairro");
        cliente.setCidade("Cidade");
        cliente.setEstado("PE");
        clienteDAO.inserir(cliente);
        List<Cliente> clientes = clienteDAO.listarTodos();
        codCliente = clientes.get(clientes.size() - 1).getCodCliente();

        // Funcionário
        Funcionario funcionario = new Funcionario();
        funcionario.setNome("Funcionario " + System.nanoTime());
        funcionario.setSupervisor(null);
        funcionarioDAO.inserir(funcionario);
        List<Funcionario> funcionarios = funcionarioDAO.listarTodos();
        codFuncionario = funcionarios.get(funcionarios.size() - 1).getCodFuncionario();

        // OrcamentoContrato com numContrato único
        codOrcamento = (int) (System.currentTimeMillis() % Integer.MAX_VALUE);
        OrcamentoContrato contrato = new OrcamentoContrato();
        contrato.setNumContrato(codOrcamento);
        contrato.setCodTema(codTema);
        contrato.setCodCliente(codCliente);
        contrato.setCodFuncionario(codFuncionario);
        contrato.setDataAssinatura(LocalDate.now());
        contrato.setDataFesta(LocalDate.now());
        contrato.setDiaSemana("Sábado");
        contrato.setHorarioFesta(LocalTime.of(19, 0));
        contrato.setLocalFesta("Local Teste");
        contrato.setValorInicial(BigDecimal.valueOf(1500));
        contrato.setValorSinal(BigDecimal.valueOf(500));
        orcamentoDAO.inserir(contrato);
    }

    @Test
    @Order(1)
    public void testInserirFestaComAniversariantes() throws SQLException {
        Festa festa = new Festa();
        festa.setNumContrato(codOrcamento);
        festa.setHorarioFim(LocalTime.of(22, 0));
        festa.setCodTema(codTema);

        Aniversariante a1 = new Aniversariante("G1", 7, LocalDate.of(2017, 4, 25));
        Aniversariante a2 = new Aniversariante("G2", 7, LocalDate.of(2017, 4, 25));

        List<Aniversariante> aniversariantes = Arrays.asList(a1, a2);

        service.inserir(festa, aniversariantes, 2024);

        List<Festa> festas = festaDAO.listarTodos();
        codFesta = festas.get(festas.size() - 1).getCodFesta();

        List<Aniversariante> todos = aniversarianteDAO.listarTodos();
        codAniversariante1 = todos.get(todos.size() - 2).getCodAniversariante();
        codAniversariante2 = todos.get(todos.size() - 1).getCodAniversariante();

        Assertions.assertEquals(2024, contemDAO.buscarPorId(codFesta, codAniversariante1).getAno());
    }

    @Test
    @Order(2)
    public void testBuscarAtualizarDeletarFesta() throws SQLException {
        Festa f = service.buscarPorId(codFesta);
        Assertions.assertNotNull(f);

        f.setHorarioFim(LocalTime.of(23, 0));
        service.atualizar(f);

        Festa atualizada = service.buscarPorId(codFesta);
        Assertions.assertEquals(LocalTime.of(23, 0), atualizada.getHorarioFim());

        service.deletar(codFesta);
        Assertions.assertNull(service.buscarPorId(codFesta));

        Assertions.assertNull(contemDAO.buscarPorId(codFesta, codAniversariante1));
        Assertions.assertNull(contemDAO.buscarPorId(codFesta, codAniversariante2));
    }

    @AfterAll
    public static void cleanup() throws SQLException {
        aniversarianteDAO.deletar(codAniversariante1);
        aniversarianteDAO.deletar(codAniversariante2);
        // deletar o contrato antes do funcionário
        orcamentoDAO.deletar(codOrcamento, codOrcamento);
        funcionarioDAO.deletar(codFuncionario);
        clienteDAO.deletar(codCliente);
        temaDAO.deletar(codTema);
    }
}
