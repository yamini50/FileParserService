package com.eCG.recruitment.controller;

import com.eCG.recruitment.creditcheck.Application;
import com.eCG.recruitment.creditcheck.models.ClientDetails;
import com.eCG.recruitment.creditcheck.repository.ClientDataRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.util.LinkedMultiValueMap;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = "classpath:data.sql")
@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = Application.class)
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)

public class ClientDataApplicationTest {
    @Autowired private MockMvc mockMvc;
    @Autowired private ObjectMapper mapper;

    @Autowired private ClientDataRepository repository;


    @Test
    @DisplayName("When all Client data are requested then they are all returned")
    public void getAllClient() throws Exception {
        mockMvc
                .perform(get("/clientData/getAllClients"))
                .andExpect(status().is2xxSuccessful())
                .andExpect((MockMvcResultMatchers.content().contentType("application/json;charset=UTF-8")))
                .andExpect(jsonPath("$", hasSize((int) repository.count())));
    }

    @Test
    @DisplayName("When client data  requested per unique key")
    public void getClientByParam() throws Exception {

        LinkedMultiValueMap<String, String> requestParams = new LinkedMultiValueMap<>();
        requestParams.add("phone-no", "32342342");
        requestParams.add("dob", "1999-09-20");

       ClientDetails clientDetails=mapper.readValue( mockMvc.perform(get("/clientData/getClientDetails").params(requestParams))
                .andExpect(status().is2xxSuccessful())
                .andExpect((MockMvcResultMatchers.content().contentType("application/json;charset=UTF-8")))
                .andReturn().getResponse().getContentAsString(),
                ClientDetails.class);

        assertEquals(clientDetails.getFirstName(),"Paul");
        assertEquals(clientDetails.getLastName(),"Anderson");

    }






}
