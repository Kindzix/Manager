package com.example.manager.repository;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "trailers")
public class Trailer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "registration")
    private String registration;

    @Column(name = "review")
    private LocalDate review;

    @OneToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

    @Column(name = "identifier")
    private String identifier = "T"; // Wartość "T" jako identyfikator

    public Trailer(String registration, LocalDate review, Employee employee) {
        this.registration = registration;
        this.review = review;
        this.employee = employee;
    }

    public Trailer() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRegistration() {
        return registration;
    }

    public void setRegistration(String registration) {
        this.registration = registration;
    }

    public LocalDate getReview() {
        return review;
    }

    public void setReview(LocalDate review) {
        this.review = review;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public String getIdentifier() {
        return identifier;
    }
}
