package com.brunch.api.entity;


import com.brunch.api.utils.BaseEntity;
import com.brunch.api.validators.interfaces.UniqueLibelleStatut;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id_statut")
public class Statut extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_statut;
    @NotNull(message = "Libelle ne peut etre null")
    @Column(nullable = false, unique = true, name = "libelle")
    @UniqueLibelleStatut(message = "Libelle existe")
    private String libelle;

    

    public Long getId_statut() {
        return id_statut;
    }

    public void setId_statut(Long id_statut) {
        this.id_statut = id_statut;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }
}
