package com.SpringBD.service;

import com.SpringBD.dao.TemaDAO;
import com.SpringBD.model.Tema;

import java.sql.SQLException;
import java.util.List;

public class TemaService {
    private final TemaDAO dao = new TemaDAO();

    public void inserir(Tema tema) throws SQLException {
        dao.inserir(tema);
    }

    public List<Tema> listarTodos() throws SQLException {
        return dao.listarTodos();
    }

    public Tema buscarPorId(int id) throws SQLException {
        return dao.buscarPorId(id);
    }

    public void atualizar(Tema tema) throws SQLException {
        dao.atualizar(tema);
    }

    public void deletar(int codTema) throws SQLException {
        dao.deletar(codTema);
    }
}
