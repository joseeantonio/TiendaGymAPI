package com.example.tiendagym;


import com.example.tiendagym.repositories.ClienteRepository;
import com.example.tiendagym.repositories.ProductoRepository;
import com.example.tiendagym.repositories.ProductoVentaRepository;
import com.example.tiendagym.repositories.VentaRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@AutoConfigureMockMvc
@SpringBootTest
class TiendaGymApplicationTests {


    @Autowired
    MockMvc mockMvc;

    @Autowired
    ClienteRepository clienteRepository;

    @Autowired
    ProductoRepository productoRepository;

    @Autowired
    ProductoVentaRepository productoVentaRepository;

    @Autowired
    VentaRepository ventaRepository;

    @Test
    void rootWhenUnauthenticatedThen401() throws Exception {
        this.mockMvc.perform(get("/"))
                .andExpect(status().isUnauthorized());
    }

    private String token;
    @BeforeAll
    void getToken() throws Exception{
        MvcResult result=this.mockMvc.perform(post("/token").with(httpBasic("pepito02","password")))
                .andExpect(status().isOk())
                .andReturn();

        String jwt = result.getResponse().getContentAsString();
        this.token=jwt;
    }

    @Transactional
    @Test
    void contextLoads() {
        assert clienteRepository.count() == 2;
        assert ventaRepository.count() == 1;
        assert productoRepository.count() == 2;
        assert productoVentaRepository.count() == 1;
    }

    @Transactional
    @Test
    void listTest() throws Exception {

        mockMvc.perform(get("/clientes/").header("Authorization","Bearer "+token).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].email").value("a@a.com"))
                .andExpect(jsonPath("$[0].username").value("pepito02"));
    }

    @Transactional
    @Test
    void creationTest() throws Exception {
        long usersCount = clienteRepository.count();
        String testUser = "{\"email\": \"peri@peri.com\", \"password\": \"password\", \"username\": \"peri02\"}";

        mockMvc.perform(post("/clientes/create/").header("Authorization","Bearer "+token).contentType(MediaType.APPLICATION_JSON).content(testUser))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].email").value("peri@peri.com"))
                .andExpect(jsonPath("$[0].password").value("password"))
                .andExpect(jsonPath("$[0].username").value("peri02"));
        assert clienteRepository.count() == usersCount + 1;
    }


    @Transactional
    @Test
    void updateTest() throws Exception {
        String testUser = "{\"email\": \"peri@peri.com\", \"password\": \"password\", \"username\": \"peri02\"}";
        mockMvc.perform(put("/clientes/1/").header("Authorization","Bearer "+token).contentType(MediaType.APPLICATION_JSON).content(testUser))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.email").value("peri@peri.com"));
    }


}
