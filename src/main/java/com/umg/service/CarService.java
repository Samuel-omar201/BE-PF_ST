package com.umg.service;

import com.umg.data.bo.Car;

import java.util.List;
import java.util.Optional;

public interface CarService {

    List<Car> findAll();
    Optional<Car> findById(Integer id);
    Car update(Car car);
    Car deleteById(Integer id);

}
