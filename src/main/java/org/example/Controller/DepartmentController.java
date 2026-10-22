package org.example.Controller;


import lombok.RequiredArgsConstructor;
import org.example.DTO.DepartmentDTO.AddDepartmentDTO;
import org.example.DTO.DepartmentDTO.DepartmentDTO;
import org.example.Service.DepartmentService;
import org.example.Service.PatientService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/departments")
public class DepartmentController {
    private final DepartmentService departmentService;

    @GetMapping("")
    public ResponseEntity<List<DepartmentDTO>> getDepartments() {
        return ResponseEntity.ok(departmentService.getAllDepartments());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DepartmentDTO> getDepartment(@PathVariable Long id) {
        return ResponseEntity.ok(departmentService.getDepartmentById(id));
    }

    @GetMapping("/{name}")
    public ResponseEntity<DepartmentDTO> getDepartmentByName(@PathVariable String name) {
        return ResponseEntity.ok(departmentService.getDepartmentByName(name));
    }

    @GetMapping("/{headDoctorId}")
    public ResponseEntity<DepartmentDTO> getDepartmentByHeadDoctorId(@PathVariable Long headDoctorId) {
        return ResponseEntity.ok(departmentService.getDepartmentByHeadDoctorId(headDoctorId));
    }

    @PostMapping("")
    public ResponseEntity<DepartmentDTO> createDepartment(@RequestBody AddDepartmentDTO addDepartmentDTO) {
        DepartmentDTO saved = departmentService.insertDepartment(addDepartmentDTO);
        return ResponseEntity.created(URI.create("/"+saved.getId()))
                .body(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DepartmentDTO> updateDepartment(@PathVariable Long id, @RequestBody AddDepartmentDTO addDepartmentDTO) {
        return ResponseEntity.ok(departmentService.updateDepartmentById(id, addDepartmentDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<DepartmentDTO> deleteDepartment(@PathVariable Long id) {
        return ResponseEntity.ok(departmentService.deleteDepartmentById(id));
    }
}
