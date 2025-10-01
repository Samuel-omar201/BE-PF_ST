package com.umg.service;

import com.umg.data.bo.Car;
import com.umg.data.repository.CarRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CarServiceImpl implements CarService {

    private final CarRepository repository;

    public CarServiceImpl(CarRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Car> findAll() {
        List<Car> response = repository.findAll();
        return response;
    }

    @Override
    public Optional<Car> findById(Integer id) {
        return repository.findById(id);
    }

    @Override
    public Car update(Car car) {
        return repository.save(car);
    }

    @Override
    public Car deleteById(Integer id) {
        Optional<Car> carToDelete = repository.findById(id);

        if (carToDelete.isPresent()) {
            repository.deleteById(id);
            return carToDelete.get();
        } else {
            return null;
        }
    }
}
