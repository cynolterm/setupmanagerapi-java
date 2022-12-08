package com.cyn.setupmanager.repository;

import com.cyn.setupmanager.domain.Team;
import com.cyn.setupmanager.domain.Track;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TeamRepository extends JpaRepository<Team, Integer>
{
    Optional<Team> findById (Integer id);
    List<Team> findAll();
    Team saveAndFlush(Team team);
    void deleteById(Integer id);
}
