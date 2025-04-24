// FestaService.java
package com.SpringBD.service;

import com.SpringBD.dao.FestaDAO;
import com.SpringBD.model.Festa;

import java.sql.SQLException;
import java.util.List;

public class FestaService {
    private final FestaDAO dao = new FestaDAO();

    public void inserir(Festa festa) throws SQLException {
        dao.inserir(festa);
    }

    public List<Festa> listarTodos() throws SQLException {
        return dao.listarTodos();
    }

    public Festa buscarPorId(int id) throws SQLException {
        return dao.buscarPorId(id);
    }

    public void atualizar(Festa festa) throws SQLException {
        dao.atualizar(festa);
    }

    public void deletar(int id) throws SQLException {
        dao.deletar(id);
    }
}