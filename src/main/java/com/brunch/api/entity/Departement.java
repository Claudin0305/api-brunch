package com.brunch.api.entity;


import com.brunch.api.utils.BaseEntity;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.List;

@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id_departement")
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"id_pays", "libelle"})})
//@UniqueLibelleDept
//@NamedEntityGraph(name = "pays", attributeNodes = @NamedAttributeNode("pays"))
public class Departement extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_departement;
    @NotNull(message = "Abbreviation ne peut etre null")
    private String dept_abbreviation;
    @NotNull(message = "Libelle ne peut etre null")
    @Column(nullable = false, unique = false, name = "libelle")

    private String libelle;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
//    @NotNull
    @JoinColumn(name = "id_pays", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonBackReference("paysDepartement")
    private Pays pays;
    @JsonManagedReference("departementVille")
    @OneToMany(mappedBy = "departement", cascade = CascadeType.REMOVE)
//    private List<Ville> villes;
    public Long getId_departement() {
        return id_departement;
    }

//    public List<Ville> getVilles() {
//        return villes;
//    }

//    public void setVilles(List<Ville> villes) {
//        this.villes = villes;
//    }

    public void setId_departement(Long id_departement) {
        this.id_departement = id_departement;
    }

    public Pays getPays() {
        return pays;
    }

    public void setPays(Pays pays) {
        this.pays = pays;
    }

    public Long getId() {
        return id_departement;
    }


    public void setId(Long id_departement) {
        this.id_departement = id_departement;
    }

    public String getDept_abbreviation() {
        return dept_abbreviation;
    }

    public void setDept_abbreviation(String dept_abbreviation) {
        this.dept_abbreviation = dept_abbreviation;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public Long getPaysId(){
        if(pays == null){
            return null;
        }

        return pays.getId_pays();
    }
    public  String getLibellePays(){
        if(pays == null){
            return null;
        }
        return pays.getLibelle();
    }

}
