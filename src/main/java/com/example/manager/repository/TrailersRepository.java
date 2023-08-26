package com.example.manager.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface TrailersRepository extends CrudRepository<Trailer, Long> {
    Set<Trailer> findByEmployeeId(Long ownerId);
}
