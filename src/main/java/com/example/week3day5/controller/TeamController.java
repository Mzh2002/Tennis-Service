package com.example.week3day5.controller;

import com.example.week3day5.dto.TeamResponse;
import com.example.week3day5.service.PlayerService;
import com.example.week3day5.service.TeamService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/v1/teams")
public class TeamController {

    private final TeamService teamService;
    private final PlayerService playerService;

    public TeamController(TeamService teamService, PlayerService playerService) {
        this.teamService = teamService;
        this.playerService = playerService;
    }

    @GetMapping
    public List<TeamResponse> getTeams() {
        return teamService.getAllTeams();
    }

    @GetMapping("/{id}/players")
    public List<Map<String, Object>> getPlayersByTeam(@PathVariable Integer id,
                                                      @RequestParam(defaultValue = "false") boolean isAdmin) {
        return playerService.getPlayersByTeam(id).stream()
                .map(player -> PlayerController.toPlayerBody(player, isAdmin))
                .toList();
    }
}
