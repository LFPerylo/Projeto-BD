package com.SpringBD.service;

import com.SpringBD.dao.TelefonesClienteDAO;
import com.SpringBD.model.TelefonesCliente;

import java.sql.SQLException;
import java.util.List;

public class TelefonesClienteService {
    private final TelefonesClienteDAO dao = new TelefonesClienteDAO();

    public void inserir(TelefonesCliente telefone) throws SQLException {
        dao.inserir(telefone);
    }

    public List<TelefonesCliente> listarTodos() throws SQLException {
        return dao.listarTodos();
    }

    public void atualizar(TelefonesCliente telefone) throws SQLException {
        dao.atualizar(telefone);
    }

    public void deletar(String telefone, int codCliente) throws SQLException {
        dao.deletar(telefone, codCliente);
    }
}
