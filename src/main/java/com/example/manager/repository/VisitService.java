package com.example.manager.repository;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "visit_service")
public class VisitService {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "end_date")
    private LocalDate endDate;

    @Column(name = "description")
    private String description;

    @ManyToOne
    @JoinColumn(name = "trailer_id")
    private Trailer trailer;

    @ManyToOne
    @JoinColumn(name = "truck_id")
    private Truck truck;

    public VisitService() {

    }

    public VisitService(LocalDate startDate, LocalDate endDate, String description, Trailer trailer, Truck truck) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.description = description;
        this.trailer = trailer;
        this.truck = truck;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Trailer getTrailer() {
        return trailer;
    }

    public void setTrailer(Trailer trailer) {
        this.trailer = trailer;
    }

    public Truck getTruck() {
        return truck;
    }

    public void setTruck(Truck truck) {
        this.truck = truck;
    }
}
