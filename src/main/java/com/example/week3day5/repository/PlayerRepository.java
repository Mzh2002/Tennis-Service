package com.example.week3day5.repository;

import com.example.week3day5.entity.Player;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PlayerRepository extends JpaRepository<Player, Integer> {

    @Override
    @EntityGraph(attributePaths = "team")
    List<Player> findAll();

    @Override
    @EntityGraph(attributePaths = "team")
    Page<Player> findAll(Pageable pageable);

    @EntityGraph(attributePaths = "team")
    List<Player> findByTeamTeamId(Integer teamId);
}
