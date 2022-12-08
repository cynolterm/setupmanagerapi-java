package com.cyn.setupmanager.repository;

import com.cyn.setupmanager.domain.Setup;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SetupRepository extends JpaRepository<Setup, Integer>
{
    Optional<Setup> findById(Integer id);
}
