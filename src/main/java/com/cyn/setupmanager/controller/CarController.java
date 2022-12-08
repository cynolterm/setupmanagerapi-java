package com.cyn.setupmanager.controller;

import com.cyn.setupmanager.domain.Car;
import com.cyn.setupmanager.domain.Track;
import com.cyn.setupmanager.repository.CarRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/cars")
public class CarController
{
    private final CarRepository carRepository;

    @GetMapping(value="/get/{id}")
    public Car getCarById(@PathVariable("id") Integer id) {
        return carRepository.findById(id).get();
    }

    @GetMapping(value="/get")
    public List<Car> getAllCars() {
        return carRepository.findAll();
    }

    @PostMapping()
    public boolean createTrack(@RequestBody Car car)
    {
        Car c = carRepository.saveAndFlush(car);
        return c != null;
    }

    @PutMapping(value = "/{id}")
    public boolean updateCar(@RequestBody Car car, @PathVariable Integer id) {
        Optional<Car> optC = carRepository.findById(id);
        if (optC.isPresent()) {
            Car c = optC.get();
            c.setBrand(car.getBrand());
            c.setModel(car.getModel());
            c.setCategory(car.getCategory());

            carRepository.saveAndFlush(c);
            return true;
        }
        else {
            return false;
        }
    }

    @DeleteMapping(value ="/{id}")
    public boolean deleteCar(@PathVariable Integer id) {
        List<Integer> idsToDelete = new ArrayList<>();
        idsToDelete.add(id);
        carRepository.deleteAllByIdInBatch(idsToDelete);
        return true;
    }
}
