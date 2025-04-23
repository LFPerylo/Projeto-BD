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
    @DisplayName("Deve atualizar o telefone existente")
    void testAtualizarTelefone() throws Exception {
        // Gerar um número original e um novo aleatório
        String telefoneOriginal = "81988" + (int)(Math.random() * 1000000);
        String telefoneAtualizado = "81999" + (int)(Math.random() * 1000000);

        TelefonesCliente original = new TelefonesCliente(codClienteExistente, telefoneOriginal);
        TelefonesCliente atualizado = new TelefonesCliente(codClienteExistente, telefoneAtualizado);

        // Inserir telefone original
        mockMvc.perform(post("/telefones")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(original)))
                .andExpect(status().isOk());

        // Atualizar para novo número
        mockMvc.perform(put("/telefones")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(atualizado)))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Deve deletar o telefone existente")
    void testDeletarTelefone() throws Exception {
        // Gerar um telefone único
        String telefoneParaDeletar = "81977" + (int)(Math.random() * 1000000);

        TelefonesCliente t = new TelefonesCliente(codClienteExistente, telefoneParaDeletar);

        // Inserir para garantir que existe
        mockMvc.perform(post("/telefones")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(t)))
                .andExpect(status().isOk());

        // Deletar
        mockMvc.perform(delete("/telefones")
                        .param("codCliente", String.valueOf(codClienteExistente))
                        .param("telefone", telefoneParaDeletar))
                .andExpect(status().isOk());
    }

}
