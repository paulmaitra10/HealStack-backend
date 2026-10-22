package org.example.Service.ServiceImpl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.DTO.DepartmentDTO.AddDepartmentDTO;
import org.example.DTO.DepartmentDTO.DepartmentDTO;
import org.example.Entity.Department;
import org.example.Entity.Doctor;
import org.example.Repository.DepartmentRepository;
import org.example.Repository.DoctorRepository;
import org.example.Service.DepartmentService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepository departmentRepository;
    private final DoctorRepository doctorRepository;
    private final ModelMapper modelMapper;


    @Override
    @Transactional
    public DepartmentDTO insertDepartment(AddDepartmentDTO AddDepartmentDTO) {
        Department department = modelMapper.map(AddDepartmentDTO, Department.class);
        department = departmentRepository.save(department);
        return modelMapper.map(department, DepartmentDTO.class);
    }

    @Override
    @Transactional
    public DepartmentDTO updateDepartmentById(Long id, AddDepartmentDTO AddDepartmentDTO) {
        Department department = departmentRepository.findById(id).orElseThrow();
        modelMapper.map(AddDepartmentDTO, department);
        department = departmentRepository.save(department);
        return modelMapper.map(department, DepartmentDTO.class);
    }

    @Override
    @Transactional
    public DepartmentDTO deleteDepartmentById(Long id) {
        Department department = departmentRepository.findById(id).orElseThrow();
        departmentRepository.delete(department);
        return modelMapper.map(department, DepartmentDTO.class);
    }

    @Override
    @Transactional
    public DepartmentDTO getDepartmentById(Long id) {
        Department department = departmentRepository.findById(id).orElseThrow();
        return modelMapper.map(department, DepartmentDTO.class);
    }

    @Override
    @Transactional
    public List<DepartmentDTO> getAllDepartments() {
        List<Department> departments = departmentRepository.findAll();
        List<DepartmentDTO> departmentDTOS = new ArrayList<>();
        for (Department department : departments) {
            departmentDTOS.add(modelMapper.map(department, DepartmentDTO.class));
        }
        return departmentDTOS;
    }

    @Override
    @Transactional
    public DepartmentDTO getDepartmentByName(String name) {
        Department department = departmentRepository.findByName(name);
        return modelMapper.map(department, DepartmentDTO.class);
    }

    @Override
    @Transactional
    public DepartmentDTO getDepartmentByHeadDoctorId(Long headDoctorId) {
        Doctor doctor = doctorRepository.findById(headDoctorId).orElseThrow();
        Department department = departmentRepository.findByHeadDoctor(doctor);
        return modelMapper.map(department, DepartmentDTO.class);
    }
}
