package com.cyn.setupmanager.repository;

import com.cyn.setupmanager.domain.Car;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CarRepository extends JpaRepository<Car, Integer>
{
    Optional<Car> findById(Integer id);
    List<Car> findAll();
    Car saveAndFlush(Car c);
    void deleteAllByIdInBatch(Iterable<Integer> ids);
}
