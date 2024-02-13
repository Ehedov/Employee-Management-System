package com.example.emloyee.repository;

import com.example.emloyee.repository.entity.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Long> {
    List<EmployeeEntity> findAllByIsDeleted(boolean isDeleted);

    Optional<EmployeeEntity> findByIdAndIsDeleted(Long id, boolean isDeleted);
}
