package com.example.emloyee.controller;

import com.example.emloyee.service.DTO.CreateEmployeeDTO;
import com.example.emloyee.service.DTO.EmployeeDTO;
import com.example.emloyee.service.DTO.UpdateEmployeeDTO;
import com.example.emloyee.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/employees")
@RequiredArgsConstructor
public class EmployeeController {
    private final EmployeeService employeeService;

    @GetMapping
    public List<EmployeeDTO> findAll() {
        return employeeService.findAll();
    }

    @GetMapping("/{id}")
    public EmployeeDTO findById(@PathVariable Long id){
        return employeeService.findById(id);
    }

    @PostMapping
    public void create(@RequestBody CreateEmployeeDTO createEmployeeDTO){
        employeeService.create(createEmployeeDTO);
    }

    @PutMapping("/{id}")
    public void update(@PathVariable Long id, @RequestBody UpdateEmployeeDTO updateEmployeeDTO){
        employeeService.update(id,updateEmployeeDTO);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        employeeService.delete(id);
    }
}
