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
public class Affiliation extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long affiliationId;

    @Column(name = "nom_affiliation")
    private String nom_affiliation;

    @Column(name="validate", columnDefinition = "bit(1) default false")
    private boolean validate;

    @JsonManagedReference("affiliation")
    @OneToMany(mappedBy = "affiliation")
    private List<Participant> participants;

    public boolean getValidate(){
        return validate;
    }
    public void setValidate(boolean validate){
        this.validate = validate;
    }
}
