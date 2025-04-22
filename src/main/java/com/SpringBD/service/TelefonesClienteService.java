package com.SpringBD.service;

import com.SpringBD.dao.ClienteDAO;
import com.SpringBD.dao.TelefonesClienteDAO;
import com.SpringBD.model.Cliente;
import com.SpringBD.model.TelefonesCliente;

import java.sql.SQLException;
import java.util.List;

public class TelefonesClienteService {

    private final TelefonesClienteDAO dao = new TelefonesClienteDAO();
    private final ClienteDAO clienteDAO = new ClienteDAO(); // Usado para verificar se o cliente existe

    public void inserir(TelefonesCliente telefone) throws SQLException {
        // Verifica se o cliente existe antes de inserir
        Cliente cliente = clienteDAO.buscarPorId(telefone.getCodCliente());
        if (cliente == null) {
            throw new SQLException("O cliente com o código " + telefone.getCodCliente() + " não existe.");
        }

        dao.inserir(telefone);
    }

    public List<TelefonesCliente> listarTodos() throws SQLException {
        return dao.listarTodos();
    }

    public void atualizar(TelefonesCliente telefone) throws SQLException {
        // Verifica se o cliente existe antes de atualizar
        Cliente cliente = clienteDAO.buscarPorId(telefone.getCodCliente());
        if (cliente == null) {
            throw new SQLException("O cliente com o código " + telefone.getCodCliente() + " não existe.");
        }

        dao.atualizar(telefone);
    }

    public void deletar(String telefone, int codCliente) throws SQLException {
        dao.deletar(telefone, codCliente);
    }
}
