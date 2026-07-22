package com.example.week3day5.controller;

import com.example.week3day5.dto.PlayerResponse;
import com.example.week3day5.entity.Gender;
import com.example.week3day5.service.PlayerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDate;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class PlayerControllerTest {

    private MockMvc mockMvc;

    @Mock
    private PlayerService playerService;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(new PlayerController(playerService)).build();
    }

    @Test
    void getPlayersHidesLastNameForRegularUsers() throws Exception {
        given(playerService.getAllPlayers()).willReturn(List.of(buildPlayer()));

        mockMvc.perform(get("/v1/players").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].firstName").value("Ariana"))
                .andExpect(jsonPath("$[0].lastName").doesNotExist())
                .andExpect(jsonPath("$[0].age").value(28));
    }

    @Test
    void getPlayersShowsLastNameForAdmins() throws Exception {
        given(playerService.getAllPlayers()).willReturn(List.of(buildPlayer()));

        mockMvc.perform(get("/v1/players").param("isAdmin", "true").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].firstName").value("Ariana"))
                .andExpect(jsonPath("$[0].lastName").value("Lopez"))
                .andExpect(jsonPath("$[0].age").value(28));
    }

    private PlayerResponse buildPlayer() {
        PlayerResponse response = new PlayerResponse();
        response.setPlayerId(1);
        response.setFirstName("Ariana");
        response.setLastName("Lopez");
        response.setGender(Gender.Female);
        response.setBirthDate(LocalDate.of(1998, 4, 11));
        response.setAge(28);
        response.setHeightCm(176.0);
        response.setWeightKg(63.0);
        response.setCareerTitle(7);
        response.setCareerWins(214);
        response.setCountry("Spain");
        response.setRanking(12);
        response.setTeamId(1);
        response.setTeamName("Aces United");
        return response;
    }
}
