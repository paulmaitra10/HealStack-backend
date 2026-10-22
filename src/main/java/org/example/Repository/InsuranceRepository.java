package org.example.Repository;

import org.example.Entity.Insurance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.Repository;

import java.time.LocalDate;
import java.util.List;

public interface InsuranceRepository extends JpaRepository<Insurance, Long> {

    Insurance findByPolicyNumber(String policyNumber);

    List<Insurance> findAllByProvider(String provider);

    List<Insurance> findAllByValidUntilBefore(LocalDate validUntil);

    List<Insurance> findAllByProviderAndValidUntilBefore(String provider, LocalDate validUntil);

    List<Insurance> findAllByValidUntil(LocalDate now);
}