package com.example.manager.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface TrucksRepository extends CrudRepository<Truck, Long> {
    Set<Truck> findByEmployeeId(Long ownerId);
}
