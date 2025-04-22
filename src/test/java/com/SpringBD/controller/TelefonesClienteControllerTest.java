// TelefonesClienteControllerTest.java
package com.SpringBD.controller;

import com.SpringBD.SpringBdApplication;
import com.SpringBD.model.TelefonesCliente;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(classes = SpringBdApplication.class)
@AutoConfigureMockMvc
public class TelefonesClienteControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    @DisplayName("Deve listar todos os telefones")
    void testListarTelefones() throws Exception {
        mockMvc.perform(get("/telefones"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", is(not(empty()))));
    }

    @Test
    @DisplayName("Deve inserir novo telefone para cliente existente")
    void testInserirTelefone() throws Exception {
        TelefonesCliente telefone = new TelefonesCliente(1, "81999990000");

        mockMvc.perform(post("/telefones")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(telefone)))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Deve atualizar o telefone existente")
    void testAtualizarTelefone() throws Exception {
        TelefonesCliente telefone = new TelefonesCliente(1, "81988887777");

        mockMvc.perform(put("/telefones")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(telefone)))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Deve deletar o telefone existente")
    void testDeletarTelefone() throws Exception {
        mockMvc.perform(delete("/telefones")
                        .param("codCliente", "1")
                        .param("telefone", "81988887777"))
                .andExpect(status().isOk());
    }
}
