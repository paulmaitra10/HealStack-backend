package org.example.Controller;


import lombok.RequiredArgsConstructor;
import org.example.DTO.InsuranceDTO.AddInsuranceDTO;
import org.example.DTO.InsuranceDTO.InsuranceDTO;
import org.example.Service.InsuranceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/insurances")
public class InsuranceController {
    private final InsuranceService insuranceService;

    @GetMapping("")
    public ResponseEntity<List<InsuranceDTO>> getInsurances() {
        return ResponseEntity.ok(insuranceService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<InsuranceDTO> getInsuranceById(@PathVariable Long id) {
        return ResponseEntity.ok(insuranceService.findById(id));
    }

    @GetMapping("/{policyNumber}")
    public ResponseEntity<InsuranceDTO> getInsuranceByPolicyNumber(@PathVariable String policyNumber) {
        return ResponseEntity.ok(insuranceService.getInsuranceByPolicyNumber(policyNumber));
    }

    @GetMapping("/{provider}")
    public ResponseEntity<List<InsuranceDTO>> getInsurancesByProvider(@PathVariable String provider) {
        return ResponseEntity.ok(insuranceService.findAllByProvider(provider));
    }

    @GetMapping("/expiring/{validUntil}")
    public ResponseEntity<List<InsuranceDTO>> getInsurancesByValidUntil(@PathVariable LocalDate validUntil) {
        return ResponseEntity.ok(insuranceService.findAllByValidUntil(validUntil));
    }

    @GetMapping("/{provider}/expiring/{validUntil}")
    public ResponseEntity<List<InsuranceDTO>> getInsurancesByValidUntil(@PathVariable String provider, @PathVariable LocalDate validUntil) {
        return ResponseEntity.ok(insuranceService.findAllByProviderAndValidUntil(provider, validUntil));
    }

    @GetMapping("/expiring/today")
    public ResponseEntity<List<InsuranceDTO>> getInsurancesToday() {
        return ResponseEntity.ok(insuranceService.expiringToday());
    }

    @PostMapping("")
    public ResponseEntity<InsuranceDTO>  createInsurance(@RequestBody AddInsuranceDTO addInsuranceDTO) {
        InsuranceDTO saved = insuranceService.createInsurance(addInsuranceDTO);
        return ResponseEntity.created(URI.create("/"+saved.getId())).body(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<InsuranceDTO> updateInsurance(@PathVariable Long id, @RequestBody AddInsuranceDTO addInsuranceDTO) {
        return ResponseEntity.ok(insuranceService.updateInsurance(id, addInsuranceDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<InsuranceDTO> deleteInsurance(@PathVariable Long id) {
        return ResponseEntity.ok(insuranceService.deleteInsurance(id));
    }
}
