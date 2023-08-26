package com.example.manager.controller;

import com.example.manager.repository.Trailer;
import com.example.manager.repository.TrailersRepository;
import com.example.manager.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/trailers")
public class TrailerController {

    @Autowired
    private TrailersRepository trailersRepository;

    @Autowired
    private ManagerService managerService;

    @GetMapping
    public ResponseEntity<List<Trailer>> getAllTrailers() {
        List<Trailer> trailers = managerService.getAllTrailers();
        return ResponseEntity.ok(trailers);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Trailer> getTrailerById(@PathVariable Long id) {
        Optional<Trailer> trailer = managerService.getTrailerById(id);
        return trailer.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/employee/{employeeId}")
    public ResponseEntity<Set<Trailer>> getTrailersByEmployeeId(@PathVariable Long employeeId) {
        Set<Trailer> trailers = managerService.getTrailersByEmployeeId(employeeId);
        return ResponseEntity.ok(trailers);
    }

    @PostMapping
    public ResponseEntity<Trailer> createTrailer(@RequestBody Trailer trailer) {
        Trailer savedTrailer = managerService.createTrailer(trailer);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedTrailer);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Trailer> updateTrailer(@PathVariable Long id, @RequestBody Trailer updatedTrailer) {
        try {
            Trailer trailer = managerService.updateTrailer(id, updatedTrailer);
            return ResponseEntity.ok(trailer);
        } catch (ManagerService.NotFoundException ex) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTrailer(@PathVariable Long id) {
        managerService.deleteTrailer(id);
        return ResponseEntity.noContent().build();
    }
}
