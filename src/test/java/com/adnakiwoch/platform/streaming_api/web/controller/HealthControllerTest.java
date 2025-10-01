package com.adnakiwoch.platform.streaming_api.web.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(HealthController.class)
public class HealthControllerTest {
  @Autowired MockMvc mockMvc;

  @Test
  void helthCheck() throws Exception {
    mockMvc
        .perform(get("/api/v1/health"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.status").value("ok"));
  }

  @Test
  void healthCheckReturns406ForUnsupportedMedea() throws Exception {
    mockMvc
        .perform((get("/api/v1/health").accept(MediaType.APPLICATION_XML)))
        .andExpect(status().isNotAcceptable());
  }
}
