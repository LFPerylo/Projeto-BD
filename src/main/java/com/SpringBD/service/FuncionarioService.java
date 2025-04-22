// FuncionarioService.java
package com.SpringBD.service;

import com.SpringBD.dao.FuncionarioDAO;
import com.SpringBD.model.Funcionario;

import java.sql.SQLException;
import java.util.List;

public class FuncionarioService {
    private final FuncionarioDAO dao = new FuncionarioDAO();

    public void inserir(Funcionario funcionario) throws SQLException {
        dao.inserir(funcionario);
    }

    public List<Funcionario> listarTodos() throws SQLException {
        return dao.listarTodos();
    }

    public Funcionario buscarPorId(int id) throws SQLException {
        return dao.buscarPorId(id);
    }

    public void atualizar(Funcionario funcionario) throws SQLException {
        dao.atualizar(funcionario);
    }

    public void deletar(int id) throws SQLException {
        dao.deletar(id);
    }
}
