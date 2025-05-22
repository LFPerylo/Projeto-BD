// OrcamentoContratoController.java
package com.SpringBD.controller;

import com.SpringBD.model.OrcamentoContrato;
import com.SpringBD.service.OrcamentoContratoService;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/orcamentos")
@CrossOrigin(origins = "*")
public class OrcamentoContratoController {

    private final OrcamentoContratoService service = new OrcamentoContratoService();

    @PostMapping
    public void inserir(@RequestBody OrcamentoContrato o) throws SQLException {
        service.inserir(o);
    }

    @GetMapping
    public List<OrcamentoContrato> listar() throws SQLException {
        return service.listarTodos();
    }

    @GetMapping("/{codOrcamento}/{numContrato}")
    public OrcamentoContrato buscar(@PathVariable int codOrcamento, @PathVariable int numContrato) throws SQLException {
        return service.buscarPorId(codOrcamento, numContrato);
    }

    @PutMapping("/{codOrcamento}/{numContrato}")
    public void atualizar(@PathVariable int codOrcamento,
                          @PathVariable int numContrato,
                          @RequestBody OrcamentoContrato o) throws SQLException {
        o.setCodOrcamento(codOrcamento);
        o.setNumContrato(numContrato);
        service.atualizar(o);
    }

    @DeleteMapping("/{codOrcamento}/{numContrato}")
    public void deletar(@PathVariable int codOrcamento, @PathVariable int numContrato) throws SQLException {
        service.deletar(codOrcamento, numContrato);
    }

    @GetMapping("/por-mes/{ano}")
    public List<Map<String, Object>> festasPorMes(@PathVariable int ano) throws SQLException {
        return service.buscarFestasPorMes(ano);
    }

}
