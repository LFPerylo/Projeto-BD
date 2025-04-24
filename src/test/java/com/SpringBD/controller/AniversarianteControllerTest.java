// AniversarianteControllerTest.java
package com.SpringBD.controller;

import com.SpringBD.SpringBdApplication;
import com.SpringBD.dao.AniversarianteDAO;
import com.SpringBD.model.Aniversariante;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.List;
import java.util.Random;

import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(classes = SpringBdApplication.class)
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class AniversarianteControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private final ObjectMapper objectMapper = new ObjectMapper()
            .registerModule(new JavaTimeModule())
            .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

    private static int codAniversarianteInserido;

    @Test
    @Order(1)
    public void testInserir() throws Exception {
        Aniversariante a = new Aniversariante();
        a.setNome("Teste " + new Random().nextInt(1000));
        a.setIdade(13);
        a.setDataNascimento(LocalDate.of(2014, 1, 1));

        mockMvc.perform(post("/aniversariantes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(a)))
                .andExpect(status().isOk());

        List<Aniversariante> lista = new AniversarianteDAO().listarTodos();
        codAniversarianteInserido = lista.get(lista.size() - 1).getCodAniversariante();

        assertTrue(codAniversarianteInserido > 0);
    }

    @Test
    @Order(2)
    public void testBuscar() throws Exception {
        mockMvc.perform(get("/aniversariantes/" + codAniversarianteInserido))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.codAniversariante", is(codAniversarianteInserido)));
    }

    @Test
    @Order(3)
    public void testAtualizar() throws Exception {
        Aniversariante atualizado = new Aniversariante();
        atualizado.setCodAniversariante(codAniversarianteInserido);
        atualizado.setNome("Atualizado");
        atualizado.setIdade(8);
        atualizado.setDataNascimento(LocalDate.of(2016, 3, 5));

        mockMvc.perform(put("/aniversariantes/" + codAniversarianteInserido)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(atualizado)))
                .andExpect(status().isOk());

        mockMvc.perform(get("/aniversariantes/" + codAniversarianteInserido))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome", is("Atualizado")));
    }

    @Test
    @Order(4)
    public void testDeletar() throws Exception {
        mockMvc.perform(delete("/aniversariantes/" + codAniversarianteInserido))
                .andExpect(status().isOk());

        mockMvc.perform(get("/aniversariantes/" + codAniversarianteInserido))
                .andExpect(status().isOk())
                .andExpect(content().string(""));
    }
}
