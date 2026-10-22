package org.example.Controller;


import lombok.RequiredArgsConstructor;
import org.example.DTO.DoctorDTO.AddDoctorDTO;
import org.example.DTO.DoctorDTO.DoctorDTO;
import org.example.Service.DoctorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/doctors")
public class DoctorController {

    private final DoctorService doctorService;

    @GetMapping("/{id}")
    public ResponseEntity<DoctorDTO> getDoctorById(@PathVariable Long id){
        return ResponseEntity.ok(doctorService.getDoctorById(id));
    }

    @GetMapping("")
    public ResponseEntity<List<DoctorDTO>> getAllDoctors(){
        return ResponseEntity.ok(doctorService.getDoctorList());
    }

    @GetMapping("/specialization/{spc}")
    public ResponseEntity<List<DoctorDTO>> getAllDoctorsBySpecialization(@PathVariable String spc){
        return ResponseEntity.ok(doctorService.getDoctorBySpecialization(spc));
    }

    @PostMapping("")
    public ResponseEntity<DoctorDTO> createDoctor(@RequestBody AddDoctorDTO addDoctorDTO){
        DoctorDTO saved = doctorService.createDoctor(addDoctorDTO);
        return ResponseEntity.created(
                URI.create("/"+saved.getId()))
                .body(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DoctorDTO> updateDoctor(@PathVariable Long id, @RequestBody AddDoctorDTO addDoctorDTO){
        DoctorDTO saved = doctorService.updateDoctor(id, addDoctorDTO);
        return ResponseEntity.ok(saved);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<DoctorDTO> deleteDoctor(@PathVariable Long id){
        DoctorDTO saved = doctorService.deleteDoctor(id);
        return ResponseEntity.ok(saved);
    }

}
