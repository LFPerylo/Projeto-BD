// TemaControllerTest.java
package com.SpringBD.controller;

import com.SpringBD.SpringBdApplication;
import com.SpringBD.model.Tema;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
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
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TemaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private final ObjectMapper objectMapper = new ObjectMapper();
    private static int idTema;

    @Test
    @Order(1)
    public void testInserirTema() throws Exception {
        Tema tema = new Tema(0, "Tema Teste", "Descrição de teste");

        mockMvc.perform(post("/temas")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(tema)))
                .andExpect(status().isOk());
    }

    @Test
    @Order(2)
    public void testListarTemas() throws Exception {
        String json = mockMvc.perform(get("/temas"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", not(empty())))
                .andReturn().getResponse().getContentAsString();

        Tema[] temas = objectMapper.readValue(json, Tema[].class);
        idTema = temas[temas.length - 1].getCodTema();
    }

    @Test
    @Order(3)
    public void testAtualizarTema() throws Exception {
        Tema tema = new Tema(idTema, "Tema Atualizado", "Nova descrição");

        mockMvc.perform(put("/temas/" + idTema)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(tema)))
                .andExpect(status().isOk());
    }

    @Test
    @Order(4)
    public void testDeletarTema() throws Exception {
        mockMvc.perform(delete("/temas/" + idTema))
                .andExpect(status().isOk());
    }
}
