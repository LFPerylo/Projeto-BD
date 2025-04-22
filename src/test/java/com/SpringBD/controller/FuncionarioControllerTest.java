// FuncionarioControllerTest.java
package com.SpringBD.controller;

import com.SpringBD.model.Funcionario;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class FuncionarioControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testCRUDComFuncionarioTemporario() throws Exception {
        // 1. Inserir Supervisor Temporário
        Funcionario supervisor = new Funcionario();
        supervisor.setCodFuncionario(0); // será ignorado pelo banco
        supervisor.setNome("Supervisor Temp");
        supervisor.setSupervisor(null);

        mockMvc.perform(post("/funcionarios")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(supervisor)))
                .andExpect(status().isOk());

        // 2. Listar para obter o ID do supervisor
        String jsonLista = mockMvc.perform(get("/funcionarios"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].codFuncionario", notNullValue()))
                .andReturn().getResponse().getContentAsString();

        Funcionario[] funcionarios = objectMapper.readValue(jsonLista, Funcionario[].class);
        int supervisorId = funcionarios[funcionarios.length - 1].getCodFuncionario();

        // 3. Inserir subordinado ligado ao supervisor
        Funcionario subordinado = new Funcionario();
        subordinado.setCodFuncionario(0);
        subordinado.setNome("Subordinado Temp");
        subordinado.setSupervisor(supervisorId);

        mockMvc.perform(post("/funcionarios")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(subordinado)))
                .andExpect(status().isOk());

        // 4. Buscar subordinado pelo ID
        String jsonAtualizado = mockMvc.perform(get("/funcionarios"))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        Funcionario[] atualizados = objectMapper.readValue(jsonAtualizado, Funcionario[].class);
        int subordinadoId = atualizados[atualizados.length - 1].getCodFuncionario();

        // 5. Atualizar subordinado (remover supervisor)
        subordinado.setCodFuncionario(subordinadoId);
        subordinado.setSupervisor(null);
        subordinado.setNome("Subordinado Atualizado");
        mockMvc.perform(put("/funcionarios/" + subordinadoId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(subordinado)))
                .andExpect(status().isOk());

        // 6. Deletar subordinado (sem FK ativa)
        mockMvc.perform(delete("/funcionarios/" + subordinadoId))
                .andExpect(status().isOk());

        // 7. Deletar supervisor (sem subordinados ativos)
        mockMvc.perform(delete("/funcionarios/" + supervisorId))
                .andExpect(status().isOk());
    }
}
