package com.example.week3day5.service;

import com.example.week3day5.dto.PlayerRequest;
import com.example.week3day5.dto.PlayerResponse;
import com.example.week3day5.entity.Gender;
import com.example.week3day5.entity.Player;
import com.example.week3day5.entity.Team;
import org.junit.jupiter.api.Test;

import java.time.Clock;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;

import static org.assertj.core.api.Assertions.assertThat;

class PlayerMapperTest {

    @Test
    void toResponseCalculatesAgeFromBirthDate() {
        Clock fixedClock = Clock.fixed(Instant.parse("2026-07-18T12:00:00Z"), ZoneId.of("America/Los_Angeles"));
        PlayerMapper mapper = new PlayerMapper(fixedClock);

        Team team = new Team();
        team.setTeamId(2);
        team.setTeamName("Baseline Elite");

        Player player = new Player();
        player.setPlayerId(7);
        player.setFirstName("Jordan");
        player.setLastName("Reed");
        player.setGender(Gender.Male);
        player.setBirthDate(LocalDate.of(1995, 9, 23));
        player.setHeightCm(188.0);
        player.setWeightKg(81.0);
        player.setCareerTitles(11);
        player.setCareerWins(342);
        player.setCountry("United States");
        player.setRanking(8);
        player.setTeam(team);

        PlayerResponse response = mapper.toResponse(player);

        assertThat(response.getAge()).isEqualTo(30);
        assertThat(response.getCareerTitle()).isEqualTo(11);
        assertThat(response.getTeamId()).isEqualTo(2);
        assertThat(response.getTeamName()).isEqualTo("Baseline Elite");
    }

    @Test
    void updateEntityCopiesRequestValues() {
        PlayerMapper mapper = new PlayerMapper(Clock.systemUTC());
        Player player = new Player();
        Team team = new Team();
        team.setTeamId(1);
        team.setTeamName("Aces United");

        PlayerRequest request = new PlayerRequest();
        request.setFirstName("Ariana");
        request.setLastName("Lopez");
        request.setGender(Gender.Female);
        request.setBirthDate(LocalDate.of(1998, 4, 11));
        request.setHeightCm(176.0);
        request.setWeightKg(63.0);
        request.setCareerTitle(7);
        request.setCareerWins(214);
        request.setCountry("Spain");
        request.setRanking(12);
        request.setTeamId(1);

        mapper.updateEntity(player, request, team);

        assertThat(player.getFirstName()).isEqualTo("Ariana");
        assertThat(player.getLastName()).isEqualTo("Lopez");
        assertThat(player.getCareerTitles()).isEqualTo(7);
        assertThat(player.getTeam()).isSameAs(team);
        assertThat(player.getRanking()).isEqualTo(12);
    }
}
