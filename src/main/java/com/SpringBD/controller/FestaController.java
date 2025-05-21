package com.SpringBD.controller;

import com.SpringBD.model.Aniversariante;
import com.SpringBD.model.Festa;
import com.SpringBD.service.FestaService;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import com.SpringBD.config.ConexaoBD;

@RestController
@RequestMapping("/festas")
@CrossOrigin(origins = "*")
public class FestaController {

    private final FestaService service = new FestaService();

    // CLÁSSICO: Listar todas as festas
    @GetMapping
    public List<Festa> listar() throws SQLException {
        return service.listarTodos();
    }

    // CLÁSSICO: Buscar festa por ID
    @GetMapping("/{id}")
    public Festa buscar(@PathVariable int id) throws SQLException {
        return service.buscarPorId(id);
    }

    // NOVO: Inserir festa + aniversariantes + ano
    @PostMapping
    public void inserir(@RequestBody FestaRequest request) throws SQLException {
        service.inserir(request.getFesta(), request.getAniversariantes(), request.getAno());
    }

    // Atualizar apenas a festa
    @PutMapping("/{id}")
    public void atualizar(@PathVariable int id, @RequestBody Festa festa) throws SQLException {
        festa.setCodFesta(id);
        service.atualizar(festa);
    }

    // Deletar festa
    @DeleteMapping("/{id}")
    public void deletar(@PathVariable int id) throws SQLException {
        service.deletar(id);
    }

    @GetMapping("/aniversariantes/proximos")
    public List<Map<String, Object>> buscarAniversariantesProximos() throws SQLException {
        String sql = """
        SELECT cod_aniversariante, nome, data_nascimento, dias_restantes
        FROM alerta_aniversario
        ORDER BY dias_restantes ASC
    """;

        try (Connection conn = ConexaoBD.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            List<Map<String, Object>> lista = new ArrayList<>();
            while (rs.next()) {
                Map<String, Object> item = new HashMap<>();
                item.put("cod_aniversariante", rs.getInt("cod_aniversariante"));
                item.put("nome", rs.getString("nome"));
                item.put("data_nascimento", rs.getDate("data_nascimento").toString());
                item.put("dias_restantes", rs.getInt("dias_restantes"));
                lista.add(item);
            }
            return lista;
        }
    }

    // Classe interna para receber a requisição correta (DTO)
    public static class FestaRequest {
        private Festa festa;
        private List<Aniversariante> aniversariantes;
        private int ano;

        // Getters e Setters
        public Festa getFesta() {
            return festa;
        }
        public void setFesta(Festa festa) {
            this.festa = festa;
        }
        public List<Aniversariante> getAniversariantes() {
            return aniversariantes;
        }
        public void setAniversariantes(List<Aniversariante> aniversariantes) {
            this.aniversariantes = aniversariantes;
        }
        public int getAno() {
            return ano;
        }
        public void setAno(int ano) {
            this.ano = ano;
        }
    }
}
