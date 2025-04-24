// ContemController.java
package com.SpringBD.controller;

import com.SpringBD.model.Contem;
import com.SpringBD.service.ContemService;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/contem")
@CrossOrigin(origins = "*")
public class ContemController {

    private final ContemService service = new ContemService();

    @PostMapping
    public void inserir(@RequestBody Contem contem) throws SQLException {
        service.inserir(contem);
    }

    @GetMapping
    public List<Contem> listar() throws SQLException {
        return service.listarTodos();
    }

    @GetMapping("/{codFesta}/{codAniversariante}")
    public Contem buscar(@PathVariable int codFesta, @PathVariable int codAniversariante) throws SQLException {
        return service.buscarPorId(codFesta, codAniversariante);
    }

    @PutMapping("/{codFesta}/{codAniversariante}")
    public void atualizar(@PathVariable int codFesta, @PathVariable int codAniversariante,
                          @RequestBody Contem contem) throws SQLException {
        contem.setCodFesta(codFesta);
        contem.setCodAniversariante(codAniversariante);
        service.atualizar(contem);
    }

    @DeleteMapping("/{codFesta}/{codAniversariante}")
    public void deletar(@PathVariable int codFesta, @PathVariable int codAniversariante) throws SQLException {
        service.deletar(codFesta, codAniversariante);
    }
}