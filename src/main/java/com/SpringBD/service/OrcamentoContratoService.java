// OrcamentoContratoService.java
package com.SpringBD.service;

import com.SpringBD.dao.OrcamentoContratoDAO;
import com.SpringBD.model.OrcamentoContrato;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class OrcamentoContratoService {
    private final OrcamentoContratoDAO dao = new OrcamentoContratoDAO();

    public void inserir(OrcamentoContrato o) throws SQLException {
        dao.inserir(o);
    }

    public List<OrcamentoContrato> listarTodos() throws SQLException {
        return dao.listarTodos();
    }

    public OrcamentoContrato buscarPorId(int codOrcamento, int numContrato) throws SQLException {
        return dao.buscarPorId(codOrcamento, numContrato);
    }

    public void atualizar(OrcamentoContrato o) throws SQLException {
        dao.atualizar(o);
    }

    public void deletar(int codOrcamento, int numContrato) throws SQLException {
        dao.deletar(codOrcamento, numContrato);
    }

    public List<Map<String, Object>> buscarFestasPorMes(int ano) throws SQLException {
        return dao.buscarFestasPorMes(ano);
    }


}
