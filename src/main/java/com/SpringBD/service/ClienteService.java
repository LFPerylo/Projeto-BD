package com.SpringBD.service;

import com.SpringBD.dao.ClienteDAO;
import com.SpringBD.model.Cliente;

import java.sql.SQLException;
import java.util.List;

public class ClienteService {
    private final ClienteDAO dao = new ClienteDAO();

    public void inserir(Cliente cliente) throws SQLException {
        dao.inserir(cliente);
    }

    public List<Cliente> listarTodos() throws SQLException {
        return dao.listarTodos();
    }

    public Cliente buscarPorId(int id) throws SQLException {
        return dao.buscarPorId(id);
    }

    public void atualizar(Cliente cliente) throws SQLException {
        dao.atualizar(cliente);
    }

    public void deletar(int id) throws SQLException {
        dao.deletar(id);
    }
}
