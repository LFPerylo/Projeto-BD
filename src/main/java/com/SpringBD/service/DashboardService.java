package com.SpringBD.service;

import com.SpringBD.dao.ClienteDAO;
import com.SpringBD.dao.PagamentoDAO;
import com.SpringBD.dao.OrcamentoContratoDAO;

import java.sql.SQLException;

public class DashboardService {

    private final ClienteDAO clienteDAO = new ClienteDAO();
    private final PagamentoDAO pagamentoDAO = new PagamentoDAO();
    private final OrcamentoContratoDAO orcamentoContratoDAO = new OrcamentoContratoDAO();

    public int getTotalClientes() throws SQLException {
        return clienteDAO.contarClientes();
    }

    public double getFaturamentoMesAtual() throws SQLException {
        return pagamentoDAO.somarFaturamentoMesAtual();
    }

    public int getFestasMesAtual() throws SQLException {
        return orcamentoContratoDAO.contarFestasMesAtual();
    }



}
