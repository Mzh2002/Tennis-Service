package com.example.week3day5.repository;

import com.example.week3day5.entity.Player;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PlayerRepository extends JpaRepository<Player, Integer> {

    @Override
    @EntityGraph(attributePaths = "team")
    List<Player> findAll();

    @EntityGraph(attributePaths = "team")
    List<Player> findByTeamTeamId(Integer teamId);
}
