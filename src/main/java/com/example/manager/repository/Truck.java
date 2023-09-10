package com.example.manager.repository;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Optional;

@Entity
@Table(name = "trucks")
public class Truck {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "registration")
    private String registration;

    @Column(name = "date_review")
    private LocalDate dateReview;

    @Column(name = "next_review_date")
    private LocalDate nextReviewDate; // Nowe pole terminu przeglądu

    @OneToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

    public Truck() {
    }

    public Truck(String registration, LocalDate dateReview, LocalDate nextReviewDate, Employee employee) {
        this.registration = registration;
        this.dateReview = dateReview;
        this.nextReviewDate = nextReviewDate;
        this.employee = employee;
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

    public LocalDate getDateReview() {
        return dateReview;
    }

    public void setDateReview(LocalDate dateReview) {
        this.dateReview = dateReview;
    }

    public LocalDate getNextReviewDate() {
        return nextReviewDate;
    }

    public void setNextReviewDate(LocalDate nextReviewDate) {
        this.nextReviewDate = nextReviewDate;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
}
