package com.cyn.setupmanager.repository;

import com.cyn.setupmanager.domain.SetupVariant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SetupVariantRepository extends JpaRepository<SetupVariant, Integer>
{
    Optional<SetupVariant> findById(Integer id);
}
