package com.brunch.api.entity;

import com.brunch.api.utils.BaseEntity;
import com.brunch.api.validators.interfaces.UniqueLibelleCivilite;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.util.List;

@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id_civilite")
public class Civilite extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_civilite;
    @NotNull(message = "Libelle ne peut etre null")
    @Column(nullable = false, unique = true, name = "libelle")
    @UniqueLibelleCivilite(message = "Libelle existe")

    private String libelle;

    @JsonManagedReference("civiliteResponsable")
    @OneToMany(mappedBy = "civilite", cascade = CascadeType.REMOVE)
    private List<ResponsableTable> responsableTables;


    public List<Participant> getParticipants() {
        return participants;
    }

    public void setParticipants(List<Participant> participants) {
        this.participants = participants;
    }

    @JsonManagedReference("participantCivilite")
    @OneToMany(mappedBy = "civilite_participant", cascade = CascadeType.REMOVE)
    private List<Participant> participants;
    public Long getId_civilite() {
        return id_civilite;
    }


    public void setId_civilite(Long id_civilite) {
        this.id_civilite = id_civilite;
    }

    public List<ResponsableTable> getResponsableTables() {
        return responsableTables;
    }

    public void setResponsableTables(List<ResponsableTable> responsableTables) {
        this.responsableTables = responsableTables;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }
}
