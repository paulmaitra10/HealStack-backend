package org.example.Service;

import org.example.DTO.DepartmentDTO.AddDepartmentDTO;
import org.example.DTO.DepartmentDTO.DepartmentDTO;

import java.util.List;

public interface DepartmentService {

    DepartmentDTO insertDepartment(AddDepartmentDTO AddDepartmentDTO);

    DepartmentDTO updateDepartmentById(Long id, AddDepartmentDTO AddDepartmentDTO);

    DepartmentDTO deleteDepartmentById(Long id);

    DepartmentDTO getDepartmentById(Long id);

    List<DepartmentDTO> getAllDepartments();

    DepartmentDTO getDepartmentByName(String name);

    DepartmentDTO getDepartmentByHeadDoctorId(Long headDoctorId);
}
