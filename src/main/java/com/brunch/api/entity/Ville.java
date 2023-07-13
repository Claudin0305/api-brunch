package com.brunch.api.entity;


import com.brunch.api.utils.BaseEntity;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.List;

@Entity
//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id_ville")
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"id_departement", "libelle"})})
public class Ville extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_ville;

    @NotNull(message = "Libelle ne peut etre null")
    @Column(nullable = false, unique = false, name = "libelle")

    private String libelle;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
//    @NotNull
    @JoinColumn(name = "id_departement", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonBackReference("departementVille")
    private Departement departement;



//    @JsonManagedReference("localVille")
//    @OneToMany(mappedBy = "local_ville", cascade = CascadeType.REMOVE)
//    private List<Local> localVilles;
//    @JsonManagedReference("participantVille")
//    @OneToMany(mappedBy = "ville_participant", cascade = CascadeType.REMOVE)
//    private List<Participant> participants;



//    public List<Participant> getParticipants() {
//        return participants;
//    }

//    public void setParticipants(List<Participant> participants) {
//        this.participants = participants;
//    }





    public Long getId_ville() {
        return id_ville;
    }

    public void setId_ville(Long id_ville) {
        this.id_ville = id_ville;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public Departement getDepartement() {
        return departement;
    }

    public void setDepartement(Departement departement) {
        this.departement = departement;
    }
    public Long getDepartementId(){
        if(departement == null){
            return null;
        }

        return departement.getId_departement();
    }
    public  String getLibelleDepartement(){
        if(departement == null){
            return null;
        }
        return departement.getLibelle();
    }
}
