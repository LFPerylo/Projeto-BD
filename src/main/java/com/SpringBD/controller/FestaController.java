package com.SpringBD.controller;

import com.SpringBD.model.Aniversariante;
import com.SpringBD.model.Festa;
import com.SpringBD.service.FestaService;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

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
