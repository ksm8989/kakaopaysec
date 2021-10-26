package com.kakaopay.controller;

import com.kakaopay.service.FindService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.HashMap;
import java.util.Map;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@WebMvcTest(TransactionAggregateController.class)
class FindControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    FindService findService;

    @Test
    @DisplayName("problem_1 API test")
    void problem1() throws Exception {

        mockMvc.perform(get("/test/problem_1"))
                    .andExpect(status().isOk())
                    .andDo(print())
                    .andReturn();
    }

    @Test
    @DisplayName("problem_2 API test")
    void problem2() throws Exception {
        mockMvc.perform(get("/test/problem_2"))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn();
    }

    @Test
    @DisplayName("problem_3 API test")
    void problem3() throws Exception{
        mockMvc.perform(get("/test/problem_3"))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn();
    }

    @Test
    @DisplayName("problem_4 API test")
    void problem4() throws Exception{
        Map<String, String> params = new HashMap<>();
        mockMvc.perform(post("/test/problem_4"))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn();
    }


}