package com.example.manager.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface VacationRepository extends CrudRepository<Vacation, Long> {
    Set<Vacation> findByEmployeeId(Long employeeId);
}
