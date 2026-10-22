package org.example.Service;

import org.example.DTO.InsuranceDTO.AddInsuranceDTO;
import org.example.DTO.InsuranceDTO.InsuranceDTO;

import java.time.LocalDate;
import java.util.List;

public interface InsuranceService {
    InsuranceDTO findById(Long id);

    InsuranceDTO createInsurance(AddInsuranceDTO addInsuranceDTO);

    InsuranceDTO updateInsurance(Long Id,AddInsuranceDTO addInsuranceDTO);

    InsuranceDTO deleteInsurance(Long id);

    InsuranceDTO getInsuranceByPolicyNumber(String policyNumber);

    List<InsuranceDTO> findAll();

    List<InsuranceDTO> findAllByProvider(String provider);

    List<InsuranceDTO> findAllByValidUntil(LocalDate validUntil);

    List<InsuranceDTO> findAllByProviderAndValidUntil(String provider, LocalDate validUntil);

    List<InsuranceDTO> expiringToday();


}
