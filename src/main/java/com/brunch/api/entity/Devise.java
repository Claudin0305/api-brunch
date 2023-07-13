package com.brunch.api.entity;

import com.brunch.api.utils.BaseEntity;
import com.brunch.api.validators.interfaces.UniqueDevise;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.util.List;

@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id_devise")
public class Devise extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_devise;
    @NotNull(message = "Devise ne peut etre null")
    @Column(nullable = false, unique = true, name = "devise")
    @UniqueDevise(message = "Devise existe")
    private String devise;


//    @JsonManagedReference("localDevise")
//    @OneToMany(mappedBy = "local_devise", cascade = CascadeType.REMOVE)
//    private List<Local> locals;

    @NotNull(message = "Code devise ne peut etre null")
    private String code_devise;

    public Long getId_devise() {
        return id_devise;
    }



    public void setId_devise(Long id_devise) {
        this.id_devise = id_devise;
    }

    public String getDevise() {
        return devise;
    }

    public void setDevise(String devise) {
        this.devise = devise;
    }

    public String getCode_devise() {
        return code_devise;
    }

    public void setCode_devise(String code_devise) {
        this.code_devise = code_devise;
    }
}
