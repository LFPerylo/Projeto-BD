// FuncionarioController.java
package com.SpringBD.controller;

import com.SpringBD.dao.FuncionarioDAO;
import com.SpringBD.model.Funcionario;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/funcionarios")
@CrossOrigin(origins = "*")
public class FuncionarioController {

    private final FuncionarioDAO dao = new FuncionarioDAO();

    @PostMapping
    public void inserir(@RequestBody Funcionario funcionario) throws SQLException {
        dao.inserir(funcionario);
    }

    @GetMapping
    public List<Funcionario> listar() throws SQLException {
        return dao.listarTodos();
    }

    @GetMapping("/{id}")
    public Funcionario buscar(@PathVariable int id) throws SQLException {
        return dao.buscarPorId(id);
    }

    @PutMapping("/{id}")
    public void atualizar(@PathVariable int id, @RequestBody Funcionario funcionario) throws SQLException {
        funcionario.setCodFuncionario(id);
        dao.atualizar(funcionario);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable int id) throws SQLException {
        dao.deletar(id);
    }
}
