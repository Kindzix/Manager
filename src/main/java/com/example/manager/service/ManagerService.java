package com.example.manager.service;

import com.example.manager.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.util.*;

@Service
public class ManagerService {

    @Autowired
    private EmployeesRepository employeesRepository;

    @Autowired
    private TrailersRepository trailersRepository;

    @Autowired
    private TrucksRepository trucksRepository;

    @Autowired
    private VacationRepository vacationRepository;

    @Autowired
    private VisitserviceRepository visitserviceRepository;

    public class NotFoundException extends RuntimeException {
        public NotFoundException(String message) {
            super(message);
        }
    }

    // Employee methods
    public List<Employee> getAllEmployees() {
        return (List<Employee>) employeesRepository.findAll();
    }

    public Optional<Employee> getEmployeeById(Long id) {
        return employeesRepository.findById(id);
    }

    public Employee createEmployee(Employee employee) {
        return employeesRepository.save(employee);
    }

    public Employee updateEmployee(Long id, Employee updatedEmployee) {
        Optional<Employee> existingEmployee = employeesRepository.findById(id);

        if (existingEmployee.isPresent()) {
            Employee employee = existingEmployee.get();
            employee.setFirstName(updatedEmployee.getFirstName());
            employee.setLastName(updatedEmployee.getLastName());
            employee.setEmail(updatedEmployee.getEmail());
            employee.setPhoneNumber(updatedEmployee.getPhoneNumber());
            employee.setTrailer(updatedEmployee.getTrailer());
            employee.setTruck(updatedEmployee.getTruck());
            return employeesRepository.save(employee);
        } else {
            throw new NotFoundException("Employee not found");
        }
    }

    public void deleteEmployee(Long id) {
        employeesRepository.deleteById(id);
    }

    // Trailer methods
    public List<Trailer> getAllTrailers() {
        return (List<Trailer>) trailersRepository.findAll();
    }

    public Optional<Trailer> getTrailerById(Long id) {
        return trailersRepository.findById(id);
    }

    public Trailer createTrailer(Trailer trailer) {
        return trailersRepository.save(trailer);
    }

    public Trailer updateTrailer(Long id, Trailer updatedTrailer) {
        Optional<Trailer> existingTrailer = trailersRepository.findById(id);

        if (existingTrailer.isPresent()) {
            Trailer trailer = existingTrailer.get();
            trailer.setRegistration(updatedTrailer.getRegistration());
            trailer.setReview(updatedTrailer.getReview());
            trailer.setEmployee(updatedTrailer.getEmployee());
            return trailersRepository.save(trailer);
        } else {
            throw new NotFoundException("Trailer not found");
        }
    }

    public Set<Trailer> getTrailersByEmployeeId(Long employeeId) {
        Optional<Employee> employee = employeesRepository.findById(employeeId);

        if (employee.isPresent()) {
            Employee employeeEntity = employee.get();
            if (employeeEntity.getTrailer() != null) {
                Set<Trailer> trailers = new HashSet<>();
                trailers.add(employeeEntity.getTrailer());
                return trailers;
            } else {
                return Collections.emptySet();
            }
        } else {
            throw new NotFoundException("Employee not found");
        }
    }

    public void deleteTrailer(Long id) {
        trailersRepository.deleteById(id);
    }

    // Truck methods
    public List<Truck> getAllTrucks() {
        return (List<Truck>) trucksRepository.findAll();
    }

    public Optional<Truck> getTruckById(Long id) {
        return trucksRepository.findById(id);
    }

    public Set<Truck> getTrucksByEmployeeId(Long employeeId) {
        Optional<Employee> employee = employeesRepository.findById(employeeId);

        if (employee.isPresent()) {
            Employee employeeEntity = employee.get();
            if (employeeEntity.getTruck() != null) {
                Set<Truck> trucks = new HashSet<>();
                trucks.add(employeeEntity.getTruck());
                return trucks;
            } else {
                return Collections.emptySet();
            }
        } else {
            throw new NotFoundException("Employee not found");
        }
    }

    public Truck createTruck(Truck truck) {
        return trucksRepository.save(truck);
    }

    public Truck updateTruck(Long id, Truck updatedTruck) {
        Optional<Truck> existingTruck = trucksRepository.findById(id);

        if (existingTruck.isPresent()) {
            Truck truck = existingTruck.get();
            truck.setRegistration(updatedTruck.getRegistration());
            truck.setReview(updatedTruck.getReview());
            truck.setEmployee(updatedTruck.getEmployee());
            return trucksRepository.save(truck);
        } else {
            throw new NotFoundException("Truck not found");
        }
    }

    public void deleteTruck(Long id) {
        if (trucksRepository.existsById(id)) {
            trucksRepository.deleteById(id);
        } else {
            throw new NotFoundException("Truck not found");
        }
    }

    // Vacation methods
    public List<Vacation> getAllVacations() {
        return (List<Vacation>) vacationRepository.findAll();
    }

    public Set<Vacation> getVacationsByEmployeeId(Long employeeId) {
        Optional<Employee> employee = employeesRepository.findById(employeeId);

        if (employee.isPresent()) {
            return employee.get().getVacations();
        } else {
            throw new NotFoundException("Employee not found");
        }
    }

    public Vacation createVacation(Vacation vacation) {
        return vacationRepository.save(vacation);
    }

    public Vacation updateVacation(Long id, Vacation updatedVacation) {
        Optional<Vacation> existingVacation = vacationRepository.findById(id);

        if (existingVacation.isPresent()) {
            Vacation vacation = existingVacation.get();
            vacation.setStartDate(updatedVacation.getStartDate());
            vacation.setEndDate(updatedVacation.getEndDate());
            vacation.setEmployee(updatedVacation.getEmployee());
            return vacationRepository.save(vacation);
        } else {
            throw new NotFoundException("Vacation not found");
        }
    }

    public boolean deleteVacation(Long id) {
        if (vacationRepository.existsById(id)) {
            vacationRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    // VisitService methods
    public List<VisitService> getAllVisitServices() {
        return (List<VisitService>) visitserviceRepository.findAll();
    }

    public Set<VisitService> getVisitServicesByTruckId(Long truckId) {
        return visitserviceRepository.findByTruckId(truckId);
    }

    public Set<VisitService> getVisitServicesByTrailerId(Long trailerId) {
        return visitserviceRepository.findByTrailerId(trailerId);
    }

    public VisitService createVisitService(VisitService visitService) {
        return visitserviceRepository.save(visitService);
    }

    public VisitService updateVisitService(Long id, VisitService updatedVisitService) {
        Optional<VisitService> existingVisitService = visitserviceRepository.findById(id);

        if (existingVisitService.isPresent()) {
            VisitService visitService = existingVisitService.get();
            visitService.setStartDate(updatedVisitService.getStartDate());
            visitService.setEndDate(updatedVisitService.getEndDate());
            visitService.setDescription(updatedVisitService.getDescription());
            visitService.setTrailer(updatedVisitService.getTrailer());
            visitService.setTruck(updatedVisitService.getTruck());
            return visitserviceRepository.save(visitService);
        } else {
            throw new NotFoundException("VisitService not found");
        }
    }

    public void deleteVisitService(Long id) {
        visitserviceRepository.deleteById(id);
    }

//    @PostConstruct
//    public void initializeSampleData() {
//        Employee employee1 = new Employee("John", "Doe", "john@example.com", "123456789");
//        createEmployee(employee1);
//
//        Trailer trailer1 = new Trailer("ABC123", LocalDate.now(), employee1);
//        createTrailer(trailer1);
//
//        Truck truck1 = new Truck("XYZ789", LocalDate.now(), employee1);
//        createTruck(truck1);
//
//        LocalDate startDate = LocalDate.of(2023, 8, 1);
//        LocalDate endDate = LocalDate.of(2023, 8, 15);
//        Vacation vacation1 = new Vacation(startDate, endDate, employee1);
//        createVacation(vacation1);
//
//        VisitService visitService1 = new VisitService(startDate, endDate, "Service description", trailer1, truck1);
//        createVisitService(visitService1);
//    }

}
