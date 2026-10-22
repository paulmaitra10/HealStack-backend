package org.example.Entity;

import jakarta.persistence.*;
import lombok.*;
import org.example.Entity.Type.BloodGroupType;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String gender;

    @Column(nullable = false)
    private LocalDate birthDate;

    @Enumerated(EnumType.STRING)
    public BloodGroupType bloodGroupType;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn()    //Owner Side
    private Insurance insurance;


    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "patient", fetch = FetchType.LAZY)
    @ToString.Exclude // Inverse Side
    private List<Appointment> appointments = new ArrayList<>();



    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;
    @UpdateTimestamp
    @Column(nullable = false)
    private LocalDateTime updatedAt;


}
