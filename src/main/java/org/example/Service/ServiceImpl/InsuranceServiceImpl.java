package org.example.Service.ServiceImpl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.DTO.InsuranceDTO.AddInsuranceDTO;
import org.example.DTO.InsuranceDTO.InsuranceDTO;
import org.example.Entity.Insurance;
import org.example.Entity.Patient;
import org.example.Repository.InsuranceRepository;
import org.example.Repository.PatientRepository;
import org.example.Service.InsuranceService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class InsuranceServiceImpl implements InsuranceService {

    private final InsuranceRepository insuranceRepository;
    private final PatientRepository patientRepository;
    private final ModelMapper modelMapper;


    @Override
    @Transactional
    public InsuranceDTO findById(Long id) {
        Insurance insurance = insuranceRepository.findById(id).orElseThrow();
        return modelMapper.map(insurance, InsuranceDTO.class);
    }


    @Override
    @Transactional
    public InsuranceDTO createInsurance(AddInsuranceDTO addInsuranceDTO) {
        Insurance insurance = modelMapper.map(addInsuranceDTO, Insurance.class);
        return modelMapper.map(insuranceRepository.save(insurance), InsuranceDTO.class);
    }

    @Override
    @Transactional
    public InsuranceDTO updateInsurance(Long Id,AddInsuranceDTO addInsuranceDTO) {
        Insurance insurance = insuranceRepository.findById(Id).orElseThrow();
        Patient patient = patientRepository.findById(addInsuranceDTO.getPatientId()).orElseThrow();

        insurance.setPatient(patient);
        insurance.setProvider(addInsuranceDTO.getProvider());
        insurance.setPolicyNumber(addInsuranceDTO.getPolicyNumber());
        insurance.setValidUntil(addInsuranceDTO.getValidUntil());

        return modelMapper.map(insuranceRepository.save(insurance), InsuranceDTO.class);
    }

    @Override
    @Transactional
    public InsuranceDTO deleteInsurance(Long id) {
        Insurance insurance = insuranceRepository.findById(id).orElseThrow();
        insuranceRepository.delete(insurance);
        return modelMapper.map(insurance, InsuranceDTO.class);
    }

    @Override
    public InsuranceDTO getInsuranceByPolicyNumber(String policyNumber) {
        Insurance insurance = insuranceRepository.findByPolicyNumber(policyNumber);
        return modelMapper.map(insurance, InsuranceDTO.class);
    }

    @Override
    public List<InsuranceDTO> findAll() {
        List<Insurance> insurances = insuranceRepository.findAll();
        return getInsuranceDTOList(insurances);
    }

    @Override
    public List<InsuranceDTO> findAllByProvider(String provider) {
        List<Insurance> insurances = insuranceRepository.findAllByProvider(provider);
        return getInsuranceDTOList(insurances);
    }

    @Override
    public List<InsuranceDTO> findAllByValidUntil(LocalDate validUntil) {
        List<Insurance> insurances = insuranceRepository.findAllByValidUntilBefore(validUntil);
        return getInsuranceDTOList(insurances);
    }

    @Override
    public List<InsuranceDTO> findAllByProviderAndValidUntil(String provider, LocalDate validUntil) {
        List<Insurance> insurances = insuranceRepository.findAllByProviderAndValidUntilBefore(provider,validUntil);
        return getInsuranceDTOList(insurances);
    }

    @Override
    public List<InsuranceDTO> expiringToday() {
        List<Insurance> insurances = insuranceRepository.findAllByValidUntil(LocalDate.now());
        return getInsuranceDTOList(insurances);
    }


    private List<InsuranceDTO> getInsuranceDTOList(List<Insurance> insurances) {
        List<InsuranceDTO> insuranceDTOList = new ArrayList<>();
        for (Insurance insurance : insurances) {
            insuranceDTOList.add(modelMapper.map(insurance, InsuranceDTO.class));
        }
        return insuranceDTOList;
    }
}
