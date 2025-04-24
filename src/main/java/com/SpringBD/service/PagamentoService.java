// PagamentoService.java
package com.SpringBD.service;

import com.SpringBD.dao.PagamentoDAO;
import com.SpringBD.model.Pagamento;

import java.sql.SQLException;
import java.util.List;

public class PagamentoService {

    private final PagamentoDAO dao = new PagamentoDAO();

    public void inserir(Pagamento pagamento) throws SQLException {
        dao.inserir(pagamento);
    }

    public List<Pagamento> listarTodos() throws SQLException {
        return dao.listarTodos();
    }

    public Pagamento buscarPorId(int codPagamento) throws SQLException {
        return dao.buscarPorId(codPagamento);
    }

    public void atualizar(Pagamento pagamento) throws SQLException {
        dao.atualizar(pagamento);
    }

    public void deletar(int codPagamento) throws SQLException {
        dao.deletar(codPagamento);
    }
}
