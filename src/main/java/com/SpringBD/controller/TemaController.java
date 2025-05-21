package com.SpringBD.controller;

import com.SpringBD.config.ConexaoBD;
import com.SpringBD.model.Tema;
import com.SpringBD.service.TemaService;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;

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

    @GetMapping("/mais-usados")
    public List<Map<String, Object>> temasMaisUsados() throws SQLException {
        String sql = """
        SELECT t.Nome AS tema, COUNT(f.Cod_Tema) AS quantidade
        FROM Tema t
        LEFT JOIN Festa f ON f.Cod_Tema = t.Cod_Tema
        GROUP BY t.Nome
        ORDER BY quantidade DESC
    """;

        List<Map<String, Object>> resultado = new ArrayList<>();

        try (Connection conn = ConexaoBD.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Map<String, Object> linha = new HashMap<>();
                linha.put("tema", rs.getString("tema"));
                linha.put("quantidade", rs.getInt("quantidade"));
                resultado.add(linha);
            }
        }

        return resultado;
    }
}
