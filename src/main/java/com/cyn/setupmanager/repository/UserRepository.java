package com.cyn.setupmanager.repository;

import com.cyn.setupmanager.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer>
{
    Optional<User> findByUsername(String username);
    List<User> findAll();
    Page<User> findAll(Pageable page);
}
