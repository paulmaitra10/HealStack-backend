package org.example.Entity;


import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String specialization;

    @Column(nullable = false, unique = true)
    private String email;


    @OneToMany(cascade = CascadeType.ALL,orphanRemoval = true, fetch = FetchType.LAZY,mappedBy = "doctor")
    @ToString.Exclude //Inverse Side
    private List<Appointment> appointments = new ArrayList<>();

    @ManyToMany(fetch = FetchType.LAZY,mappedBy = "doctors")
    @ToString.Exclude
    private Set<Department> Departments = new HashSet<>();

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;
}
