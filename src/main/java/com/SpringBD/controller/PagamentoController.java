// PagamentoController.java
package com.SpringBD.controller;

import com.SpringBD.model.Pagamento;
import com.SpringBD.service.PagamentoService;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/pagamentos")
@CrossOrigin(origins = "*")
public class PagamentoController {

    private final PagamentoService service = new PagamentoService();

    @PostMapping
    public void inserir(@RequestBody Pagamento pagamento) throws SQLException {
        service.inserir(pagamento);
    }

    @GetMapping
    public List<Pagamento> listar() throws SQLException {
        return service.listarTodos();
    }

    @GetMapping("/{id}")
    public Pagamento buscar(@PathVariable int id) throws SQLException {
        return service.buscarPorId(id);
    }

    @PutMapping("/{id}")
    public void atualizar(@PathVariable int id, @RequestBody Pagamento pagamento) throws SQLException {
        pagamento.setCodPagamento(id);
        service.atualizar(pagamento);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable int id) throws SQLException {
        service.deletar(id);
    }
}
