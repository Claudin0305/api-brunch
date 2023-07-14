package com.brunch.api.entity;

import com.brunch.api.utils.BaseEntity;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id_responsable")
public class ResponsableTable extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_responsable;

    @NotNull(message = "Ce champ est obligatoire")
    @Column(name = "nom_responsable")
    @Pattern(regexp = "^[a-zA-ZÀ-ú\\\\-\\\\s]*", message = "Le format du nom est invalide")
    private String nomResponsable;

    @NotNull(message = "Ce champ est obligatoire")
    @Column(name = "prenom_responsable")
    @Pattern(regexp = "^[a-zA-ZÀ-ú\\\\-\\\\s]*", message = "Le format du prénom est invalide")
    private String prenomResponsable;
    @Email(message = "Email invalide")
    @Column(name = "email_responsable")
    private String emailResponsable;
    @Pattern(regexp = "\\\\+(?:[0-9]?){6,14}[0-9]$", message = "Le numero est invalide")
    @Column(name = "tel_responsable")
    private String telResponsable;
    @NotNull(message = "Ce champ est obligatoire")
    @Column(name = "objectifs")
    private Long objectifs;
    @NotNull(message = "Ce champ est obligatoire")
    @Column(name = "realisation")
    private Long realisation;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
//    @NotNull
    @JoinColumn(name = "id_event", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonBackReference("eventResponsableTable")
    private Event event_;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
//    @NotNull
    @JoinColumn(name = "id_civilite", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonBackReference("civiliteResponsable")
    private Civilite civilite;

    public Civilite getCivilite() {
        return civilite;
    }

    public void setCivilite(Civilite civilite) {
        this.civilite = civilite;
    }

    public Long getId_responsable() {
        return id_responsable;
    }

    public void setId_responsable(Long id_responsable) {
        this.id_responsable = id_responsable;
    }

    public String getNomResponsable() {
        return nomResponsable;
    }

    public void setNomResponsable(String nomResponsable) {
        this.nomResponsable = nomResponsable;
    }

    public String getPrenomResponsable() {
        return prenomResponsable;
    }

    public void setPrenomResponsable(String prenomResponsable) {
        this.prenomResponsable = prenomResponsable;
    }

    public String getEmailResponsable() {
        return emailResponsable;
    }

    public void setEmailResponsable(String emailResponsable) {
        this.emailResponsable = emailResponsable;
    }

    public String getTelResponsable() {
        return telResponsable;
    }

    public void setTelResponsable(String telResponsable) {
        this.telResponsable = telResponsable;
    }

    public Long getObjectifs() {
        return objectifs;
    }

    public void setObjectifs(Long objectifs) {
        this.objectifs = objectifs;
    }

    public Long getRealisation() {
        return realisation;
    }

    public void setRealisation(Long realisation) {
        this.realisation = realisation;
    }

    public Event getEvent_() {
        return event_;
    }

    public void setEvent_(Event event_) {
        this.event_ = event_;
    }
}
