package com.example.week3day5.service;

import com.example.week3day5.dto.PlayerRequest;
import com.example.week3day5.dto.PlayerResponse;
import com.example.week3day5.entity.Player;
import com.example.week3day5.entity.Team;
import com.example.week3day5.exception.ResourceNotFoundException;
import com.example.week3day5.repository.PlayerRepository;
import com.example.week3day5.repository.TeamRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
@Transactional(readOnly = true)
public class PlayerService {
    private static final int PLAYER_PAGE_SIZE = 10;
    private static final Map<String, String> SORT_FIELDS = Map.of(
            "age", "birthDate",
            "height", "heightCm",
            "careerTitle", "careerTitles",
            "careerWins", "careerWins"
    );

    private final PlayerRepository playerRepository;
    private final TeamRepository teamRepository;
    private final PlayerMapper playerMapper;

    public PlayerService(PlayerRepository playerRepository, TeamRepository teamRepository, PlayerMapper playerMapper) {
        this.playerRepository = playerRepository;
        this.teamRepository = teamRepository;
        this.playerMapper = playerMapper;
    }

    public List<PlayerResponse> getAllPlayers() {
        return playerRepository.findAll().stream()
                .map(playerMapper::toResponse)
                .toList();
    }

    public Page<PlayerResponse> getPlayersPage(int page, String fieldName, String direction) {
        if (page < 0) {
            throw new IllegalArgumentException("page must be greater than or equal to 0");
        }

        String sortProperty = SORT_FIELDS.get(fieldName);
        if (sortProperty == null) {
            throw new IllegalArgumentException("fieldName must be one of: age, height, careerTitle, careerWins");
        }

        Sort.Direction sortDirection;
        try {
            sortDirection = Sort.Direction.fromString(direction);
        } catch (IllegalArgumentException exception) {
            throw new IllegalArgumentException("direction must be either asc or desc");
        }

        Pageable pageable = PageRequest.of(page, PLAYER_PAGE_SIZE, Sort.by(sortDirection, sortProperty));
        return playerRepository.findAll(pageable)
                .map(playerMapper::toResponse);
    }

    public PlayerResponse getPlayerById(Integer id) {
        return playerMapper.toResponse(findPlayer(id));
    }

    @Transactional
    public PlayerResponse createPlayer(PlayerRequest request) {
        Team team = resolveTeam(request.getTeamId());
        Player player = playerMapper.toEntity(request, team);
        return playerMapper.toResponse(playerRepository.save(player));
    }

    @Transactional
    public PlayerResponse updatePlayer(Integer id, PlayerRequest request) {
        Player player = findPlayer(id);
        Team team = resolveTeam(request.getTeamId());
        playerMapper.updateEntity(player, request, team);
        return playerMapper.toResponse(playerRepository.save(player));
    }

    @Transactional
    public void deletePlayer(Integer id) {
        Player player = findPlayer(id);
        playerRepository.delete(player);
    }

    public List<PlayerResponse> getPlayersByTeam(Integer teamId) {
        if (!teamRepository.existsById(teamId)) {
            throw new ResourceNotFoundException("Team with id " + teamId + " was not found");
        }
        return playerRepository.findByTeamTeamId(teamId).stream()
                .map(playerMapper::toResponse)
                .toList();
    }

    private Player findPlayer(Integer id) {
        return playerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Player with id " + id + " was not found"));
    }

    private Team resolveTeam(Integer teamId) {
        if (teamId == null) {
            return null;
        }
        return teamRepository.findById(teamId)
                .orElseThrow(() -> new ResourceNotFoundException("Team with id " + teamId + " was not found"));
    }
}
