package org.example.Controller;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.DTO.AppointmentDTO.AppointmentDTO;
import org.example.DTO.PatientDTO.AddPatientDTO;
import org.example.DTO.PatientDTO.PatientDTO;
import org.example.Entity.Type.BloodGroupType;
import org.example.Repository.DoctorRepository;
import org.example.Repository.PatientRepository;
import org.example.Service.AppointmentService;
import org.example.Service.PatientService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/patients")
@RequiredArgsConstructor
public class PatientController {

    private final PatientService patientService;
    private final AppointmentService appointmentService;

    @GetMapping("/{id}")
    public ResponseEntity<PatientDTO> getPatientById(@PathVariable Long id){
        return ResponseEntity.ok(patientService.getPatientById(id));
    }

    @GetMapping("/{id}/appointments")
    public ResponseEntity<List<AppointmentDTO>> getPatientAppointments(@PathVariable Long id){
        return ResponseEntity.ok(appointmentService.getAppointmentsByPatientId(id));
    }

    @GetMapping("")
    public ResponseEntity<List<PatientDTO>> getAllPatients(){
        return ResponseEntity.ok(patientService.getAllPatients());
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<List<PatientDTO>> getPatientByName(@PathVariable String name){
        return ResponseEntity.ok(patientService.getPatientByName(name));
    }

    @GetMapping("/gender/{gender}")
    public ResponseEntity<List<PatientDTO>> getPatientByGender(@PathVariable String gender){
        return ResponseEntity.ok(patientService.getPatientByGender(gender));
    }

    @GetMapping("/dobafter/{date}")
    public ResponseEntity<List<PatientDTO>> getPatientByDOBAfter(@PathVariable LocalDate date){
        return ResponseEntity.ok(patientService.getPatientByDOBGreaterThan(date));
    }

    @GetMapping("/bloodgroup/{bloodGroup}")
    public ResponseEntity<List<PatientDTO>> getPatientByBloodGroup(@PathVariable BloodGroupType bloodGroup){
        return ResponseEntity.ok(patientService.getPatientByBloodType(bloodGroup));
    }


    @PostMapping("")
    public ResponseEntity<PatientDTO> createPatient(@RequestBody @Valid AddPatientDTO addPatientDTO){
        PatientDTO saved = patientService.createPatient(addPatientDTO);
        return ResponseEntity
                .created(URI.create("/" + saved.getId()))
                .body(saved);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<PatientDTO> updatePatient(@PathVariable Long id, @RequestBody @Valid AddPatientDTO addPatientDTO){
        PatientDTO saved = patientService.updatePatient(id, addPatientDTO);
        return ResponseEntity.ok(saved);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<PatientDTO> deletePatient(@PathVariable Long id){
        return ResponseEntity.ok(patientService.deletePatient(id));
    }

}
