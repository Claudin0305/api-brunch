package com.brunch.api.entity;

import com.brunch.api.utils.BaseEntity;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Affiliation extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long affiliationId;

    @Column(name = "nom_affiliation")
    private String nom_affiliation;

    @JsonManagedReference("affiliation")
    @OneToMany(mappedBy = "affiliation")
    private List<Participant> participants;


}
