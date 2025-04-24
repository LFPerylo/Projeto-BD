// OrcamentoContratoControllerTest.java
package com.SpringBD.controller;

import com.SpringBD.SpringBdApplication;
import com.SpringBD.model.OrcamentoContrato;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Random;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(classes = SpringBdApplication.class)
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class OrcamentoContratoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private final ObjectMapper objectMapper = new ObjectMapper()
            .registerModule(new JavaTimeModule())
            .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

    private static int codOrcamentoInserido;
    private static int numContratoInserido;
    private static final int COD_TEMA = 1;
    private static final int COD_CLIENTE = 1;
    private static final int COD_FUNCIONARIO = 1;

    @Test
    @Order(1)
    public void testInserir() throws Exception {
        OrcamentoContrato o = new OrcamentoContrato();
        o.setDataFesta(LocalDate.now().plusDays(7));
        o.setDiaSemana("Sábado");
        o.setHorarioFesta(LocalTime.of(17, 30));
        o.setValorInicial(BigDecimal.valueOf(2500));
        o.setLocalFesta("Buffet Alegria");
        o.setCodTema(COD_TEMA);
        o.setCodCliente(COD_CLIENTE);

        int contrato = 8000 + new Random().nextInt(999);
        o.setNumContrato(contrato);

        o.setDataAssinatura(LocalDate.now());
        o.setValorSinal(BigDecimal.valueOf(500));
        o.setCodFuncionario(COD_FUNCIONARIO);

        mockMvc.perform(post("/orcamentos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(o)))
                .andExpect(status().isOk());

        numContratoInserido = contrato;

        // Buscar codOrcamento com base no contrato
        String responseBody = mockMvc.perform(get("/orcamentos")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        List<OrcamentoContrato> lista = objectMapper.readValue(responseBody,
                objectMapper.getTypeFactory().constructCollectionType(List.class, OrcamentoContrato.class));

        codOrcamentoInserido = lista.stream()
                .filter(o2 -> o2.getNumContrato() == numContratoInserido)
                .findFirst()
                .map(OrcamentoContrato::getCodOrcamento)
                .orElseThrow();
    }

    @Test
    @Order(2)
    public void testBuscarPorContrato() throws Exception {
        mockMvc.perform(get("/orcamentos/" + codOrcamentoInserido + "/" + numContratoInserido))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.diaSemana", is("Sábado")));
    }

    @Test
    @Order(3)
    public void testAtualizar() throws Exception {
        OrcamentoContrato o = new OrcamentoContrato();
        o.setCodOrcamento(codOrcamentoInserido);
        o.setNumContrato(numContratoInserido);
        o.setDataFesta(LocalDate.now().plusDays(10));
        o.setDiaSemana("Domingo");
        o.setHorarioFesta(LocalTime.of(19, 0));
        o.setValorInicial(BigDecimal.valueOf(2800));
        o.setLocalFesta("Espaço Encantado");
        o.setCodTema(COD_TEMA);
        o.setCodCliente(COD_CLIENTE);
        o.setDataAssinatura(LocalDate.now());
        o.setValorSinal(BigDecimal.valueOf(600));
        o.setCodFuncionario(COD_FUNCIONARIO);

        mockMvc.perform(put("/orcamentos/" + codOrcamentoInserido + "/" + numContratoInserido)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(o)))
                .andExpect(status().isOk());
    }

    @Test
    @Order(4)
    public void testDeletar() throws Exception {
        mockMvc.perform(delete("/orcamentos/" + codOrcamentoInserido + "/" + numContratoInserido))
                .andExpect(status().isOk());
    }
}
