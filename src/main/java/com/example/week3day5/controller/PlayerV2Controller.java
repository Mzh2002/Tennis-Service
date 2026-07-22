package com.example.week3day5.controller;

import com.example.week3day5.dto.PlayerResponse;
import com.example.week3day5.service.PlayerService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/v2/players")
public class PlayerV2Controller {

    private final PlayerService playerService;

    public PlayerV2Controller(PlayerService playerService) {
        this.playerService = playerService;
    }

    @GetMapping
    public Map<String, Object> getPlayers(@RequestParam int page,
                                          @RequestParam String fieldName,
                                          @RequestParam String direction,
                                          @RequestParam(defaultValue = "false") boolean isAdmin) {
        Page<PlayerResponse> playersPage = playerService.getPlayersPage(page, fieldName, direction);

        Map<String, Object> body = new LinkedHashMap<>();
        body.put("content", toPlayerBodies(playersPage.getContent(), isAdmin));
        body.put("page", playersPage.getNumber());
        body.put("pageSize", playersPage.getSize());
        body.put("totalElements", playersPage.getTotalElements());
        body.put("totalPages", playersPage.getTotalPages());
        body.put("fieldName", fieldName);
        body.put("direction", direction);
        return body;
    }

    private List<Map<String, Object>> toPlayerBodies(List<PlayerResponse> players, boolean isAdmin) {
        return players.stream()
                .map(player -> PlayerController.toPlayerBody(player, isAdmin))
                .toList();
    }
}
