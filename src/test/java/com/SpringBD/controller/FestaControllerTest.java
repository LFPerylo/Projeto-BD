// FestaControllerTest.java
package com.SpringBD.controller;

import com.SpringBD.SpringBdApplication;
import com.SpringBD.model.Festa;
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
import java.util.Random;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(classes = SpringBdApplication.class)
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class FestaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private final ObjectMapper objectMapper = new ObjectMapper()
            .registerModule(new JavaTimeModule())
            .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

    private static int codFesta;
    private static final int COD_TEMA = 1;
    private static final int NUM_CONTRATO = 9500 + new Random().nextInt(500);

    @BeforeAll
    public static void prepararContrato() throws Exception {
        OrcamentoContrato contrato = new OrcamentoContrato();
        contrato.setDataFesta(LocalDate.now().plusDays(5));
        contrato.setDiaSemana("Sexta");
        contrato.setHorarioFesta(LocalTime.of(18, 0));
        contrato.setValorInicial(BigDecimal.valueOf(1800));
        contrato.setLocalFesta("Buffet Alegria");
        contrato.setCodTema(COD_TEMA);
        contrato.setCodCliente(1);
        contrato.setNumContrato(NUM_CONTRATO);
        contrato.setDataAssinatura(LocalDate.now());
        contrato.setValorSinal(BigDecimal.valueOf(500));
        contrato.setCodFuncionario(1);

        new com.SpringBD.dao.OrcamentoContratoDAO().inserir(contrato);
    }

    @Test
    @Order(1)
    void testInserir() throws Exception {
        Festa festa = new Festa();
        festa.setCodTema(COD_TEMA);
        festa.setNumContrato(NUM_CONTRATO);
        festa.setHorarioFim(LocalTime.of(20, 30));

        String json = objectMapper.writeValueAsString(festa);

        mockMvc.perform(post("/festas")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk());
    }

    @Test
    @Order(2)
    void testListarBuscarAtualizarDeletar() throws Exception {
        // Listar
        String response = mockMvc.perform(get("/festas"))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        Festa[] festas = objectMapper.readValue(response, Festa[].class);
        Festa ultima = festas[festas.length - 1];
        codFesta = ultima.getCodFesta();

        // Buscar
        mockMvc.perform(get("/festas/" + codFesta))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.numContrato").value(NUM_CONTRATO));

        // Atualizar
        ultima.setHorarioFim(LocalTime.of(22, 0));
        String atualizadoJson = objectMapper.writeValueAsString(ultima);

        mockMvc.perform(put("/festas/" + codFesta)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(atualizadoJson))
                .andExpect(status().isOk());

        // Deletar
        mockMvc.perform(delete("/festas/" + codFesta))
                .andExpect(status().isOk());
    }
}
