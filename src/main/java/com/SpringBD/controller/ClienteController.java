// ClienteController.java
package com.SpringBD.controller;

import com.SpringBD.model.Cliente;
import com.SpringBD.model.PessoaFisica;
import com.SpringBD.model.PessoaJuridica;
import com.SpringBD.service.ClienteService;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/clientes")
@CrossOrigin(origins = "*")
public class ClienteController {

    private final ClienteService service = new ClienteService();

    @GetMapping
    public List<Cliente> listar() throws SQLException {
        return service.listarTodos();
    }

    @GetMapping("/{id}")
    public Cliente buscar(@PathVariable int id) throws SQLException {
        return service.buscarPorId(id);
    }

    @PostMapping
    public void inserir(@RequestBody Cliente cliente) throws SQLException {
        if (cliente.getCpf() != null && !cliente.getCpf().isEmpty()) {
            PessoaFisica pf = new PessoaFisica(cliente);
            pf.setCpf(cliente.getCpf());
            pf.setRg(cliente.getRg());
            service.inserir(pf);
        } else if (cliente.getCnpj() != null && !cliente.getCnpj().isEmpty()) {
            PessoaJuridica pj = new PessoaJuridica(cliente);
            pj.setCnpj(cliente.getCnpj());
            pj.setInscricaoEstadual(cliente.getInscricaoEstadual());
            pj.setRazaoSocial(cliente.getRazaoSocial());
            service.inserir(pj);
        } else {
            service.inserir(cliente);
        }
    }

    @PutMapping("/{id}")
    public void atualizar(@PathVariable int id, @RequestBody Cliente cliente) throws SQLException {
        cliente.setCodCliente(id);
        if (cliente.getCpf() != null && !cliente.getCpf().isEmpty()) {
            PessoaFisica pf = new PessoaFisica(cliente);
            pf.setCpf(cliente.getCpf());
            pf.setRg(cliente.getRg());
            service.atualizar(pf);
        } else if (cliente.getCnpj() != null && !cliente.getCnpj().isEmpty()) {
            PessoaJuridica pj = new PessoaJuridica(cliente);
            pj.setCnpj(cliente.getCnpj());
            pj.setInscricaoEstadual(cliente.getInscricaoEstadual());
            pj.setRazaoSocial(cliente.getRazaoSocial());
            service.atualizar(pj);
        } else {
            service.atualizar(cliente);
        }
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable int id) throws SQLException {
        service.deletar(id);
    }
}