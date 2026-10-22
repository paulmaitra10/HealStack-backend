package org.example.Repository;

import org.example.Entity.Department;
import org.example.Entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.Repository;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
    
    Department findByName(String name);

    Department findByHeadDoctor(Doctor doctor);
}