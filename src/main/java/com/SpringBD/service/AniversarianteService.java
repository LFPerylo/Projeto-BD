// AniversarianteService.java
package com.SpringBD.service;

import com.SpringBD.dao.AniversarianteDAO;
import com.SpringBD.model.Aniversariante;

import java.sql.SQLException;
import java.util.List;

public class AniversarianteService {

    private final AniversarianteDAO dao = new AniversarianteDAO();

    public void inserir(Aniversariante aniversariante) throws SQLException {
        dao.inserir(aniversariante);
    }

    public List<Aniversariante> listarTodos() throws SQLException {
        return dao.listarTodos();
    }

    public Aniversariante buscarPorId(int id) throws SQLException {
        return dao.buscarPorId(id);
    }

    public void atualizar(Aniversariante aniversariante) throws SQLException {
        dao.atualizar(aniversariante);
    }

    public void deletar(int id) throws SQLException {
        dao.deletar(id);
    }
}