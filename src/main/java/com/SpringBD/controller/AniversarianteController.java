// AniversarianteController.java
package com.SpringBD.controller;

import com.SpringBD.model.Aniversariante;
import com.SpringBD.service.AniversarianteService;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/aniversariantes")
@CrossOrigin(origins = "*")
public class AniversarianteController {

    private final AniversarianteService service = new AniversarianteService();

    @PostMapping
    public void inserir(@RequestBody Aniversariante aniversariante) throws SQLException {
        service.inserir(aniversariante);
    }

    @GetMapping
    public List<Aniversariante> listar() throws SQLException {
        return service.listarTodos();
    }

    @GetMapping("/{id}")
    public Aniversariante buscar(@PathVariable int id) throws SQLException {
        return service.buscarPorId(id);
    }

    @PutMapping("/{id}")
    public void atualizar(@PathVariable int id, @RequestBody Aniversariante aniversariante) throws SQLException {
        aniversariante.setCodAniversariante(id);
        service.atualizar(aniversariante);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable int id) throws SQLException {
        service.deletar(id);
    }
}