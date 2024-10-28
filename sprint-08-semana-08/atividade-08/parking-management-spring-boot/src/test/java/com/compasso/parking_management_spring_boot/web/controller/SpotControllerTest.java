package com.compasso.parking_management_spring_boot.web.controller;

import com.compasso.parking_management_spring_boot.service.ParkingSpotService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@Slf4j
@WebMvcTest(ParkingSpotController.class)
class SpotControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ParkingSpotService parkingSpotService;

    @Autowired
    private ObjectMapper objectMapper;
}
