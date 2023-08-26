package com.example.manager.controller;

import com.example.manager.repository.Truck;
import com.example.manager.repository.TrucksRepository;
import com.example.manager.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/trucks")
public class TruckController {

    @Autowired
    private ManagerService managerService;

    @GetMapping
    public ResponseEntity<List<Truck>> getAllTrucks() {
        List<Truck> trucks = managerService.getAllTrucks();
        return ResponseEntity.ok(trucks);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Truck> getTruckById(@PathVariable Long id) {
        Optional<Truck> truck = managerService.getTruckById(id);
        return truck.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/employee/{employeeId}")
    public ResponseEntity<Set<Truck>> getTrucksByEmployeeId(@PathVariable Long employeeId) {
        Set<Truck> trucks = managerService.getTrucksByEmployeeId(employeeId);
        return ResponseEntity.ok(trucks);
    }

    @PostMapping
    public ResponseEntity<Truck> createTruck(@RequestBody Truck truck) {
        Truck savedTruck = managerService.createTruck(truck);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedTruck);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Truck> updateTruck(@PathVariable Long id, @RequestBody Truck updatedTruck) {
        try {
            Truck truck = managerService.updateTruck(id, updatedTruck);
            return ResponseEntity.ok(truck);
        } catch (ManagerService.NotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTruck(@PathVariable Long id) {
        try {
            managerService.deleteTruck(id);
            return ResponseEntity.noContent().build();
        } catch (ManagerService.NotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
