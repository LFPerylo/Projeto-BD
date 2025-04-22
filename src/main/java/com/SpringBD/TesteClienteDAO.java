package com.SpringBD;

import com.SpringBD.dao.ClienteDAO;
import com.SpringBD.model.Cliente;
import com.SpringBD.model.PessoaFisica;
import java.util.List;


public class TesteClienteDAO {
    public static void main(String[] args) {
        ClienteDAO dao = new ClienteDAO();

        try {
            // ✅ 1. Inserir cliente
            PessoaFisica novo = new PessoaFisica();
            novo.setNome("silvio");
            novo.setEmail("maria@teste.com");
            novo.setRua("Rua Central");
            novo.setNumero(456);
            novo.setBairro("Boa Vista");
            novo.setCidade("Olinda");
            novo.setEstado("PE");
            novo.setCpf("333.333.333-33");
            novo.setRg("3333333");

            dao.inserir(novo);
            System.out.println("✅ Cliente inserido com sucesso!");

            // ✅ 2. Listar todos os clientes
            System.out.println("\n📄 Lista de todos os clientes:");
            for (Cliente c : dao.listarTodos()) {
                System.out.println(" - " + c.getCodCliente() + ": " + c.getNome());
            }

            // ✅ 3. Buscar cliente pelo último ID inserido
            List<Cliente> lista = dao.listarTodos();
            int ultimoId = lista.get(lista.size() - 1).getCodCliente();
            Cliente buscado = dao.buscarPorId(ultimoId);
            if (buscado != null) {
                System.out.println("\n🔍 Cliente encontrado:");
                System.out.println("ID: " + buscado.getCodCliente() + ", Nome: " + buscado.getNome());
            } else {
                System.out.println("❌ Cliente não encontrado.");
            }

            // ✅ 4. Atualizar cliente
            buscado.setNome("Maria Atualizada");
            buscado.setEmail("atualizado@teste.com");
            dao.atualizar(buscado);
            System.out.println("\n✏️ Cliente atualizado com sucesso!");

            // ✅ 5. Deletar cliente
            int idParaExcluir = 14; // Altere para o ID de um cliente que NÃO esteja em nenhuma FK
            try {
                dao.deletar(idParaExcluir);
                System.out.println("\n🗑️ Cliente com ID " + idParaExcluir + " deletado com sucesso!");
            } catch (Exception e) {
                System.out.println("❌ Erro ao deletar cliente com ID " + idParaExcluir + ":");
                e.printStackTrace();
            }

            // ✅ 6. Verificar lista final
            System.out.println("\n📄 Lista atualizada de clientes:");
            for (Cliente c : dao.listarTodos()) {
                System.out.println(" - " + c.getCodCliente() + ": " + c.getNome());
            }

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("❌ Erro ao executar teste do DAO.");
        }
    }
}
