package com.udacity.jdnd.course3.critter.service;

import com.udacity.jdnd.course3.critter.entity.Customer;
import com.udacity.jdnd.course3.critter.entity.Employee;
import com.udacity.jdnd.course3.critter.entity.Pet;
import com.udacity.jdnd.course3.critter.entity.Schedule;
import com.udacity.jdnd.course3.critter.repository.EmployeeRepository;
import com.udacity.jdnd.course3.critter.repository.PetRepository;
import com.udacity.jdnd.course3.critter.repository.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScheduleService {

    @Autowired
    ScheduleRepository scheduleRepository;

    public List<Schedule> getAllSchedules() {
        return scheduleRepository.findAll();
    }

    public Schedule saveSchedule(List<Employee> employees, List<Pet> pets, Schedule schedule) {
        schedule.setEmployeeList(employees);
        schedule.setPetList(pets);
        return scheduleRepository.save(schedule);
    }

    public List<Schedule> getScheduleForEmployee(Employee employee) {
        return scheduleRepository.findSchedulesByEmployeeList(employee);
    }

    public List<Schedule> getScheduleForPet(Pet pet) {
        return scheduleRepository.findSchedulesByPetList(pet);
    }

    public List<Schedule> getScheduleForCustomer(Customer customer) {
        return scheduleRepository.findSchedulesByPetListIn(customer.getPets());
    }
}
