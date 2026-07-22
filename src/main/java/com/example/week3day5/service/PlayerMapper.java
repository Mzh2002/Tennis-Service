package com.example.week3day5.service;

import com.example.week3day5.dto.PlayerRequest;
import com.example.week3day5.dto.PlayerResponse;
import com.example.week3day5.entity.Player;
import com.example.week3day5.entity.Team;
import org.springframework.stereotype.Component;

import java.time.Clock;
import java.time.LocalDate;
import java.time.Period;

@Component
public class PlayerMapper {

    private final Clock clock;

    public PlayerMapper(Clock clock) {
        this.clock = clock;
    }

    public PlayerResponse toResponse(Player player) {
        PlayerResponse response = new PlayerResponse();
        response.setPlayerId(player.getPlayerId());
        response.setFirstName(player.getFirstName());
        response.setLastName(player.getLastName());
        response.setGender(player.getGender());
        response.setBirthDate(player.getBirthDate());
        response.setAge(calculateAge(player.getBirthDate()));
        response.setHeightCm(player.getHeightCm());
        response.setWeightKg(player.getWeightKg());
        response.setCareerTitle(player.getCareerTitles());
        response.setCareerWins(player.getCareerWins());
        response.setCountry(player.getCountry());
        response.setRanking(player.getRanking());
        if (player.getTeam() != null) {
            response.setTeamId(player.getTeam().getTeamId());
            response.setTeamName(player.getTeam().getTeamName());
        }
        return response;
    }

    public Player toEntity(PlayerRequest request, Team team) {
        Player player = new Player();
        updateEntity(player, request, team);
        return player;
    }

    public void updateEntity(Player player, PlayerRequest request, Team team) {
        player.setFirstName(request.getFirstName());
        player.setLastName(request.getLastName());
        player.setGender(request.getGender());
        player.setBirthDate(request.getBirthDate());
        player.setHeightCm(request.getHeightCm());
        player.setWeightKg(request.getWeightKg());
        player.setCareerTitles(request.getCareerTitle());
        player.setCareerWins(request.getCareerWins());
        player.setCountry(request.getCountry());
        player.setRanking(request.getRanking());
        player.setTeam(team);
    }

    private Integer calculateAge(LocalDate birthDate) {
        return birthDate == null ? null : Period.between(birthDate, LocalDate.now(clock)).getYears();
    }
}
