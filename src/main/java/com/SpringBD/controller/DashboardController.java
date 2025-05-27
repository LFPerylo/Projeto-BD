package com.SpringBD.controller;

import com.SpringBD.service.DashboardService;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping("/dashboard")
@CrossOrigin(origins = "*")
public class DashboardController {

    private final DashboardService service = new DashboardService();

    @GetMapping("/resumo")
    public Map<String, Object> getResumoDashboard() throws SQLException {
        Map<String, Object> resumo = new HashMap<>();

        resumo.put("totalClientes", service.getTotalClientes());
        resumo.put("faturamentoMesAtual", service.getFaturamentoMesAtual());
        resumo.put("festasMesAtual", service.getFestasMesAtual());

        return resumo;
    }



}
