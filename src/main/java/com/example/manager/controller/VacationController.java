package com.example.manager.controller;

import com.example.manager.repository.Vacation;
import com.example.manager.repository.VacationRepository;
import com.example.manager.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/vacations")
public class VacationController {

    @Autowired
    private ManagerService managerService;

    @GetMapping
    public ResponseEntity<List<Vacation>> getAllVacations() {
        List<Vacation> vacations = managerService.getAllVacations();
        return ResponseEntity.ok(vacations);
    }

    @GetMapping("/employee/{employeeId}")
    public ResponseEntity<Set<Vacation>> getVacationsByEmployeeId(@PathVariable Long employeeId) {
        try {
            Set<Vacation> vacations = managerService.getVacationsByEmployeeId(employeeId);
            return ResponseEntity.ok(vacations);
        } catch (ManagerService.NotFoundException ex) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Vacation> createVacation(@RequestBody Vacation vacation) {
        Vacation savedVacation = managerService.createVacation(vacation);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedVacation);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Vacation> updateVacation(@PathVariable Long id, @RequestBody Vacation updatedVacation) {
        try {
            Vacation vacation = managerService.updateVacation(id, updatedVacation);
            return ResponseEntity.ok(vacation);
        } catch (ManagerService.NotFoundException ex) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVacation(@PathVariable Long id) {
        if (managerService.deleteVacation(id)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
