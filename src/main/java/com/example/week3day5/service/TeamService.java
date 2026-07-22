package com.example.week3day5.service;

import com.example.week3day5.dto.TeamResponse;
import com.example.week3day5.repository.TeamRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class TeamService {

    private final TeamRepository teamRepository;

    public TeamService(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    public List<TeamResponse> getAllTeams() {
        return teamRepository.findAll().stream()
                .map(team -> new TeamResponse(team.getTeamId(), team.getTeamName(), team.getCountry()))
                .toList();
    }
}
