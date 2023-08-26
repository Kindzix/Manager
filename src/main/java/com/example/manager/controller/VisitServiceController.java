package com.example.manager.controller;
import com.example.manager.repository.VisitService;
import com.example.manager.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/visit-services")
public class VisitServiceController {

    @Autowired
    private ManagerService managerService;

    @GetMapping
    public ResponseEntity<List<VisitService>> getAllVisitServices() {
        List<VisitService> visitServices = managerService.getAllVisitServices();
        return ResponseEntity.ok(visitServices);
    }

    @GetMapping("/truck/{truckId}")
    public ResponseEntity<Set<VisitService>> getVisitServicesByTruckId(@PathVariable Long truckId) {
        Set<VisitService> visitServices = managerService.getVisitServicesByTruckId(truckId);
        return ResponseEntity.ok(visitServices);
    }

    @GetMapping("/trailer/{trailerId}")
    public ResponseEntity<Set<VisitService>> getVisitServicesByTrailerId(@PathVariable Long trailerId) {
        Set<VisitService> visitServices = managerService.getVisitServicesByTrailerId(trailerId);
        return ResponseEntity.ok(visitServices);
    }

    @PostMapping
    public ResponseEntity<VisitService> createVisitService(@RequestBody VisitService visitService) {
        VisitService savedVisitService = managerService.createVisitService(visitService);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedVisitService);
    }

    @PutMapping("/{id}")
    public ResponseEntity<VisitService> updateVisitService(@PathVariable Long id, @RequestBody VisitService updatedVisitService) {
        VisitService visitService = managerService.updateVisitService(id, updatedVisitService);
        return ResponseEntity.ok(visitService);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVisitService(@PathVariable Long id) {
        managerService.deleteVisitService(id);
        return ResponseEntity.noContent().build();
    }
}
