package com.brunch.api.entity;

import com.brunch.api.utils.BaseEntity;
import com.brunch.api.validators.interfaces.UniqueLibelleTranche;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id_tranche_age")
public class TrancheAge extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_tranche_age;
    @NotNull(message = "Libelle ne peut etre null")
    @Column(nullable = false, unique = true, name = "libelle")
    @UniqueLibelleTranche(message = "Libelle existe")
    private String libelle;

    @JsonManagedReference("participantTrancheAge")
    @OneToMany(mappedBy = "tranche_age", cascade = CascadeType.REMOVE)
    private List<Participant> participants;


//    public List<Participant> getParticipants() {
//        return participants;
//    }
//
//    public void setParticipants(List<Participant> participants) {
//        this.participants = participants;
//    }

//    public Long getId_tranche_age() {
//        if(id_tranche_age != null)
//            return id_tranche_age;
//        return (long) 0;
//    }

//    public void setId_tranche_age(Long id_tranche_age) {
//        this.id_tranche_age = id_tranche_age;
//    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }
}
