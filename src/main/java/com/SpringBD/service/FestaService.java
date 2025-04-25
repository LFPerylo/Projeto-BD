package com.SpringBD.service;

import com.SpringBD.dao.AniversarianteDAO;
import com.SpringBD.dao.ContemDAO;
import com.SpringBD.dao.FestaDAO;
import com.SpringBD.model.Aniversariante;
import com.SpringBD.model.Contem;
import com.SpringBD.model.Festa;

import java.sql.SQLException;
import java.util.List;

public class FestaService {
    private final FestaDAO festaDAO = new FestaDAO();
    private final AniversarianteDAO aniversarianteDAO = new AniversarianteDAO();
    private final ContemDAO contemDAO = new ContemDAO();

    public void inserir(Festa festa, List<Aniversariante> aniversariantes, int ano) throws SQLException {
        // 1. Insere a festa
        int codFesta = festaDAO.inserir(festa);

        // 2. Insere cada aniversariante + relacionamento
        for (Aniversariante a : aniversariantes) {
            int codAniversariante = aniversarianteDAO.inserir(a); // Retorna o código auto_increment
            Contem contem = new Contem(codFesta, codAniversariante, ano);
            contemDAO.inserir(contem);
        }
    }

    public List<Festa> listarTodos() throws SQLException {
        return festaDAO.listarTodos();
    }

    public Festa buscarPorId(int id) throws SQLException {
        return festaDAO.buscarPorId(id);
    }

    public void atualizar(Festa festa) throws SQLException {
        festaDAO.atualizar(festa);
    }

    public void deletar(int codFesta) throws SQLException {
        // 1. Remove todos os registros da tabela Contem ligados a esta festa
        List<Contem> contemList = contemDAO.listarTodos();
        for (Contem c : contemList) {
            if (c.getCodFesta() == codFesta) {
                contemDAO.deletar(c.getCodFesta(), c.getCodAniversariante());
            }
        }

        // 2. Remove a festa
        festaDAO.deletar(codFesta);
    }
}
