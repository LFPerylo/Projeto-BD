package com.SpringBD.controller;

import com.SpringBD.model.Tema;
import com.SpringBD.service.TemaService;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/temas")
@CrossOrigin(origins = "*")
public class TemaController {

    private final TemaService service = new TemaService();

    // LISTAR TODOS
    @GetMapping
    public List<Tema> listarTodos() throws SQLException {
        return service.listarTodos();
    }

    // BUSCAR POR ID
    @GetMapping("/{id}")
    public Tema buscarPorId(@PathVariable int id) throws SQLException {
        return service.buscarPorId(id);
    }

    // INSERIR
    @PostMapping
    public void inserir(@RequestBody Tema tema) throws SQLException {
        service.inserir(tema);
    }

    // ATUALIZAR
    @PutMapping("/{id}")
    public void atualizar(@PathVariable int id, @RequestBody Tema tema) throws SQLException {
        tema.setCodTema(id); // garante que o ID seja respeitado
        service.atualizar(tema);
    }

    // DELETAR
    @DeleteMapping("/{id}")
    public void deletar(@PathVariable int id) throws SQLException {
        service.deletar(id);
    }
}
