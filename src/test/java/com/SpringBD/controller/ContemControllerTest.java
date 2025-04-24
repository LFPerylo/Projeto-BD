// ContemControllerTest.java
package com.SpringBD.controller;

import com.SpringBD.SpringBdApplication;
import com.SpringBD.model.Contem;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(classes = SpringBdApplication.class)
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ContemControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private final ObjectMapper objectMapper = new ObjectMapper();

    private static final int COD_FESTA = 1;
    private static final int COD_ANIVERSARIANTE = 1;

    @Test
    @Order(1)
    public void testInserir() throws Exception {
        Contem contem = new Contem(COD_FESTA, COD_ANIVERSARIANTE, 2025);
        mockMvc.perform(post("/contem")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(contem)))
                .andExpect(status().isOk());
    }

    @Test
    @Order(2)
    public void testBuscar() throws Exception {
        mockMvc.perform(get("/contem/" + COD_FESTA + "/" + COD_ANIVERSARIANTE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.ano").value(2025));
    }

    @Test
    @Order(3)
    public void testAtualizar() throws Exception {
        Contem contem = new Contem(COD_FESTA, COD_ANIVERSARIANTE, 2026);
        mockMvc.perform(put("/contem/" + COD_FESTA + "/" + COD_ANIVERSARIANTE)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(contem)))
                .andExpect(status().isOk());
    }

    @Test
    @Order(4)
    public void testDeletar() throws Exception {
        mockMvc.perform(delete("/contem/" + COD_FESTA + "/" + COD_ANIVERSARIANTE))
                .andExpect(status().isOk());
    }
}