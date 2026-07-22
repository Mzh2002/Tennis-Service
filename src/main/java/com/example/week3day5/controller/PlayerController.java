package com.example.week3day5.controller;

import com.example.week3day5.dto.PlayerRequest;
import com.example.week3day5.dto.PlayerResponse;
import com.example.week3day5.service.PlayerService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/v1/players")
public class PlayerController {

    private final PlayerService playerService;

    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @PostMapping
    public ResponseEntity<Map<String, Object>> createPlayer(@Valid @RequestBody PlayerRequest request,
                                                            @RequestParam(defaultValue = "false") boolean isAdmin) {
        PlayerResponse response = playerService.createPlayer(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(toPlayerBody(response, isAdmin));
    }

    @GetMapping
    public List<Map<String, Object>> getPlayers(@RequestParam(defaultValue = "false") boolean isAdmin) {
        return playerService.getAllPlayers().stream()
                .map(player -> toPlayerBody(player, isAdmin))
                .toList();
    }

    @GetMapping("/{id}")
    public Map<String, Object> getPlayerById(@PathVariable Integer id,
                                             @RequestParam(defaultValue = "false") boolean isAdmin) {
        return toPlayerBody(playerService.getPlayerById(id), isAdmin);
    }

    @PutMapping("/{id}")
    public Map<String, Object> updatePlayer(@PathVariable Integer id,
                                            @Valid @RequestBody PlayerRequest request,
                                            @RequestParam(defaultValue = "false") boolean isAdmin) {
        return toPlayerBody(playerService.updatePlayer(id, request), isAdmin);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePlayer(@PathVariable Integer id) {
        playerService.deletePlayer(id);
        return ResponseEntity.noContent().build();
    }

    static Map<String, Object> toPlayerBody(PlayerResponse player, boolean isAdmin) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("playerId", player.getPlayerId());
        body.put("firstName", player.getFirstName());
        if (isAdmin) {
            body.put("lastName", player.getLastName());
        }
        body.put("gender", player.getGender());
        body.put("birthDate", player.getBirthDate());
        body.put("age", player.getAge());
        body.put("heightCm", player.getHeightCm());
        body.put("weightKg", player.getWeightKg());
        body.put("careerTitle", player.getCareerTitle());
        body.put("careerWins", player.getCareerWins());
        body.put("country", player.getCountry());
        body.put("ranking", player.getRanking());
        body.put("teamId", player.getTeamId());
        body.put("teamName", player.getTeamName());
        return body;
    }
}
