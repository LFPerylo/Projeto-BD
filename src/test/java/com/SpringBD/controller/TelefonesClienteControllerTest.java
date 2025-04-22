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

    private final int codClienteExistente = 1;

    @Test
    @DisplayName("Deve listar todos os telefones")
    void testListarTelefones() throws Exception {
        mockMvc.perform(get("/telefones"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", is(not(empty()))));
    }

    @Test
    @DisplayName("Deve inserir novo telefone para cliente existente (evitando duplicata)")
    void testInserirTelefone() throws Exception {
        String telefoneAleatorio = "8199" + (int)(Math.random() * 1000000);
        TelefonesCliente telefone = new TelefonesCliente(codClienteExistente, telefoneAleatorio);

        mockMvc.perform(post("/telefones")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(telefone)))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Deve atualizar o telefone existente")
    void testAtualizarTelefone() throws Exception {
        // Inserir telefone original para garantir que exista
        String telefoneOriginal = "81988887777";
        TelefonesCliente original = new TelefonesCliente(codClienteExistente, telefoneOriginal);

        mockMvc.perform(post("/telefones")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(original)))
                .andExpect(status().isOk());

        // Atualizar o mesmo número (simulação de regravação)
        mockMvc.perform(put("/telefones")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(original)))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Deve deletar o telefone existente")
    void testDeletarTelefone() throws Exception {
        String telefoneParaDeletar = "81988887777";

        // Primeiro, garantir que o telefone esteja cadastrado
        TelefonesCliente t = new TelefonesCliente(codClienteExistente, telefoneParaDeletar);
        mockMvc.perform(post("/telefones")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(t)))
                .andExpect(status().isOk());

        // Em seguida, deletar
        mockMvc.perform(delete("/telefones")
                        .param("codCliente", String.valueOf(codClienteExistente))
                        .param("telefone", telefoneParaDeletar))
                .andExpect(status().isOk());
    }
}
