// PagamentoControllerTest.java
package com.SpringBD.controller;

import com.SpringBD.SpringBdApplication;
import com.SpringBD.model.Pagamento;
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
import java.util.List;
import java.util.Random;

import static org.hamcrest.Matchers.equalToIgnoringCase;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(classes = SpringBdApplication.class)
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class PagamentoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private final ObjectMapper objectMapper = new ObjectMapper()
            .registerModule(new JavaTimeModule())
            .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

    private static int codPagamento;
    private static final int COD_CLIENTE = 1;
    private static final int NUM_CONTRATO = 201;
    private static final String FORMA_PAGAMENTO = "Pix";

    @Test
    @Order(1)
    public void testInserir() throws Exception {
        Pagamento pagamento = new Pagamento();
        pagamento.setValorFinal(BigDecimal.valueOf(2000));
        pagamento.setDataPagamento(LocalDate.of(2025, 5, 20));
        pagamento.setFormaPagamento(FORMA_PAGAMENTO);
        pagamento.setValorAcrescentado(BigDecimal.valueOf(500));
        pagamento.setCodCliente(COD_CLIENTE);
        pagamento.setNumContrato(NUM_CONTRATO);

        mockMvc.perform(post("/pagamentos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(pagamento)))
                .andExpect(status().isOk());

        // Buscar todos e pegar o último inserido
        String response = mockMvc.perform(get("/pagamentos"))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        Pagamento[] lista = objectMapper.readValue(response, Pagamento[].class);
        codPagamento = lista[lista.length - 1].getCodPagamento();
    }

    @Test
    @Order(2)
    public void testBuscar() throws Exception {
        mockMvc.perform(get("/pagamentos/" + codPagamento))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.formaPagamento").value(equalToIgnoringCase(FORMA_PAGAMENTO)));
    }

    @Test
    @Order(3)
    public void testAtualizar() throws Exception {
        Pagamento pagamento = new Pagamento();
        pagamento.setCodPagamento(codPagamento);
        pagamento.setValorFinal(BigDecimal.valueOf(2100));
        pagamento.setDataPagamento(LocalDate.of(2025, 5, 21));
        pagamento.setFormaPagamento("Cartao");
        pagamento.setValorAcrescentado(BigDecimal.valueOf(300));
        pagamento.setCodCliente(COD_CLIENTE);
        pagamento.setNumContrato(NUM_CONTRATO);

        mockMvc.perform(put("/pagamentos/" + codPagamento)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(pagamento)))
                .andExpect(status().isOk());
    }

    @Test
    @Order(4)
    public void testDeletar() throws Exception {
        mockMvc.perform(delete("/pagamentos/" + codPagamento))
                .andExpect(status().isOk());
    }
}
