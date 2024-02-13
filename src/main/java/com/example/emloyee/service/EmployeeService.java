package com.example.emloyee.service;

import com.example.emloyee.exception.EmployeeNotFoundException;
import com.example.emloyee.repository.EmployeeRepository;
import com.example.emloyee.repository.entity.EmployeeEntity;
import com.example.emloyee.service.DTO.CreateEmployeeDTO;
import com.example.emloyee.service.DTO.EmployeeDTO;
import com.example.emloyee.service.DTO.UpdateEmployeeDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor

public class EmployeeService {
    private final EmployeeRepository employeeRepository;

    public List<EmployeeDTO> findAll() {
        var employees = employeeRepository.findAllByIsDeleted(false);
        var employeeDTOs = new ArrayList<EmployeeDTO>();

        for (EmployeeEntity employee : employees) {
            employeeDTOs.add(mapToEmployeeDTO(employee));
        }

        return employeeDTOs;
    }

    public EmployeeDTO findById(Long id) {
        var employee = employeeRepository.findByIdAndIsDeleted(id, false);
        return mapToEmployeeDTO(employee.orElseThrow(() -> new EmployeeNotFoundException("Employee not found with id:" + id)));
    }

    public void create(CreateEmployeeDTO createEmployeeDTO) {
        var employee = mapToEmployeeEntity(createEmployeeDTO);
        employeeRepository.save(employee);
    }

    public void update(Long id, UpdateEmployeeDTO updateEmployeeDTO) {
        EmployeeEntity employee = getEmployeeEntity(id);
        employee.setName(updateEmployeeDTO.getName());
        employee.setJob(updateEmployeeDTO.getJob());
        employee.setAge(updateEmployeeDTO.getAge());

        employeeRepository.save(employee);
    }

    public void delete(Long id) {
        EmployeeEntity employee = getEmployeeEntity(id);
        employee.setDeleted(true);

        employeeRepository.save(employee);
    }

    private EmployeeEntity getEmployeeEntity(Long id) {
        return employeeRepository.findByIdAndIsDeleted(id, false)
                .orElseThrow(() -> new EmployeeNotFoundException("Employee not found with id:" + id));
    }

    private static EmployeeEntity mapToEmployeeEntity(CreateEmployeeDTO createEmployeeDTO) {
        var employee = new EmployeeEntity();
        employee.setName(createEmployeeDTO.getName());
        employee.setJob(createEmployeeDTO.getJob());
        employee.setAge(createEmployeeDTO.getAge());
        return employee;
    }

    private static EmployeeDTO mapToEmployeeDTO(EmployeeEntity employee) {
        var employeeDTO = new EmployeeDTO();
        employeeDTO.setId(employee.getId());
        employeeDTO.setName(employee.getName());
        employeeDTO.setAge(employee.getAge());
        employeeDTO.setJob(employee.getJob());

        return employeeDTO;
    }
}
