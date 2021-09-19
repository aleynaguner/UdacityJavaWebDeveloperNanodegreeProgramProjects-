package com.udacity.jdnd.course3.critter.service;

import com.udacity.jdnd.course3.critter.entity.Employee;
import com.udacity.jdnd.course3.critter.enums.EmployeeSkill;
import com.udacity.jdnd.course3.critter.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class EmployeeService {

    @Autowired
    EmployeeRepository employeeRepository;

    public Employee getEmployeeById(Long employeeId) { return employeeRepository.findEmployeeById(employeeId); }

    public Employee saveEmployee(Employee employee){
        return employeeRepository.save(employee);
    }

    public List<Employee> getEmployeesForService(LocalDate date, Set<EmployeeSkill> skills) {
        return employeeRepository.findEmployeesByDaysAvailable(date.getDayOfWeek())
                .stream().filter(employee -> employee.getSkills().containsAll(skills))
                .collect(Collectors.toList());
    }

    public void setEmployeeAvailableDays(Set<DayOfWeek> availableDays, Long employeeId) {
        if(employeeRepository.findEmployeeById(employeeId) != null) {
            Employee employee = employeeRepository.findEmployeeById(employeeId);
            employee.setDaysAvailable(availableDays);
            employeeRepository.save(employee);
        }
    }

}
