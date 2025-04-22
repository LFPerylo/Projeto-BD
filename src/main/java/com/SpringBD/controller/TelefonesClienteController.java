package com.SpringBD.controller;

import com.SpringBD.model.TelefonesCliente;
import com.SpringBD.service.TelefonesClienteService;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/telefones")
@CrossOrigin(origins = "*")
public class TelefonesClienteController {

    private final TelefonesClienteService service = new TelefonesClienteService();

    @PostMapping
    public void inserir(@RequestBody TelefonesCliente telefone) throws SQLException {
        service.inserir(telefone);
    }

    @GetMapping
    public List<TelefonesCliente> listarTodos() throws SQLException {
        return service.listarTodos();
    }

    @PutMapping
    public void atualizar(@RequestBody TelefonesCliente telefone) throws SQLException {
        service.atualizar(telefone);
    }

    @DeleteMapping
    public void deletar(@RequestParam String telefone, @RequestParam int codCliente) throws SQLException {
        service.deletar(telefone, codCliente);
    }
}
