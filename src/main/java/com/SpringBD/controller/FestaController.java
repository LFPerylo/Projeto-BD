// FestaController.java
package com.SpringBD.controller;

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

    @PostMapping
    public void inserir(@RequestBody Festa festa) throws SQLException {
        service.inserir(festa);
    }

    @GetMapping
    public List<Festa> listar() throws SQLException {
        return service.listarTodos();
    }

    @GetMapping("/{id}")
    public Festa buscar(@PathVariable int id) throws SQLException {
        return service.buscarPorId(id);
    }

    @PutMapping("/{id}")
    public void atualizar(@PathVariable int id, @RequestBody Festa festa) throws SQLException {
        festa.setCodFesta(id);
        service.atualizar(festa);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable int id) throws SQLException {
        service.deletar(id);
    }
}