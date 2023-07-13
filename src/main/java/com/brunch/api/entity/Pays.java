package com.brunch.api.entity;


import com.brunch.api.utils.BaseEntity;
import com.brunch.api.validators.interfaces.UniqueLibelle;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id_pays")
public class Pays extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_pays;
    @NotNull(message = "Abbreviation ne peut etre null")
    private String pays_abbreviation;

    @NotNull(message = "Le nom du pays ne peut etre null")
    @Column(nullable = false, unique = true, name = "libelle")
    @UniqueLibelle(message = "Libelle existe")
    private String libelle;
    @JsonManagedReference("paysDepartement")
    @OneToMany(mappedBy = "pays", cascade = CascadeType.REMOVE)
    private List<Departement> departements;




}
