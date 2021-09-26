package com.udacity.jdnd.course3.critter.repository;

import com.udacity.jdnd.course3.critter.entity.Employee;
import com.udacity.jdnd.course3.critter.entity.Pet;
import com.udacity.jdnd.course3.critter.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
    List<Schedule> findAll();
    Schedule save(Schedule schedule);
    List<Schedule> findSchedulesByEmployeeList(Employee employee);
    List<Schedule> findSchedulesByPets(Pet pet);
    List<Schedule> findSchedulesByPetsIn(List<Pet> pets);
}
