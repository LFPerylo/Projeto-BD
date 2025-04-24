// ContemService.java
package com.SpringBD.service;

import com.SpringBD.dao.ContemDAO;
import com.SpringBD.model.Contem;

import java.sql.SQLException;
import java.util.List;

public class ContemService {

    private final ContemDAO dao = new ContemDAO();

    public void inserir(Contem contem) throws SQLException {
        dao.inserir(contem);
    }

    public List<Contem> listarTodos() throws SQLException {
        return dao.listarTodos();
    }

    public Contem buscarPorId(int codFesta, int codAniversariante) throws SQLException {
        return dao.buscarPorId(codFesta, codAniversariante);
    }

    public void atualizar(Contem contem) throws SQLException {
        dao.atualizar(contem);
    }

    public void deletar(int codFesta, int codAniversariante) throws SQLException {
        dao.deletar(codFesta, codAniversariante);
    }
}