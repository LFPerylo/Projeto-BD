// ClienteControllerTest.java
package com.SpringBD.controller;

import com.SpringBD.SpringBdApplication;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.hamcrest.Matchers.*;

@SpringBootTest(classes = SpringBdApplication.class)
@AutoConfigureMockMvc
public class ClienteControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("Deve retornar status 200 e uma lista de clientes")
    void testListarClientes() throws Exception {
        mockMvc.perform(get("/clientes"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", is(not(empty()))))
                .andExpect(jsonPath("$[0].codCliente", is(notNullValue())))
                .andExpect(jsonPath("$[0].nome", is(not(emptyOrNullString()))));
    }

    @Test
    @DisplayName("Deve retornar status 200 e cliente por ID")
    void testBuscarPorId() throws Exception {
        mockMvc.perform(get("/clientes/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.codCliente", is(1)))
                .andExpect(jsonPath("$.nome", is(not(emptyOrNullString()))));
    }
}
