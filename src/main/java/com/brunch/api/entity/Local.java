package com.brunch.api.entity;

import com.brunch.api.utils.BaseEntity;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.List;

@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id_local")
public class Local extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_local;
    @Email
    @Column(name = "email_responsable")
    private String email_responsable;
    @NotNull(message = "Ce champ est obligatoire")
    @Column(name = "capacite_table")
    private Long capacite_table;
    @NotNull(message = "Ce champ est obligatoire")
    @Column(name = "capacite_totale")
    private Long capacite_totale;
    @NotNull(message = "Ce champ est obligatoire")
    @Column(name = "seuil_alerte")
    private Long seuil_alerte;
    @NotNull(message = "Ce champ est obligatoire")
    @Column(name = "nb_reservation")
    private Long nb_reservation;

    @NotNull(message = "Ce champ est obligatoire")
    @Column(name = "adresse_no_rue")
    private String adresse_no_rue;

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    @NotNull(message = "Ce champ est obligatoire")
    @Column(name = "libelle")
    private String libelle;

    @NotNull(message = "Ce champ est obligatoire")
    @Column(name = "montant_participation")
    private Double montant_participation;


    @ManyToOne(fetch = FetchType.LAZY, optional = false)
//    @NotNull
    @JoinColumn(name = "id_event", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonBackReference("localEvenement")
    private Event local_evenement;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
//    @NotNull
    @JoinColumn(name = "id_ville", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonBackReference("localVille")
    private Ville local_ville;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
//    @NotNull
    @JoinColumn(name = "id_devise", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonBackReference("localDevise")
    private Devise local_devise;

    public List<Participant> getParticipants() {
        return participants;
    }

    public void setParticipants(List<Participant> participants) {
        this.participants = participants;
    }

    @JsonManagedReference("local_participant")
    @OneToMany(mappedBy = "local_participant", cascade = CascadeType.REMOVE)
    private List<Participant> participants;
    public Long getId_local() {
        return id_local;
    }

    public void setId_local(Long id_local) {
        this.id_local = id_local;
    }

    public String getEmail_responsable() {
        return email_responsable;
    }

    public void setEmail_responsable(String email_responsable) {
        this.email_responsable = email_responsable;
    }

    public Long getCapacite_table() {
        return capacite_table;
    }

    public void setCapacite_table(Long capacite_table) {
        this.capacite_table = capacite_table;
    }

    public Long getCapacite_totale() {
        return capacite_totale;
    }

    public void setCapacite_totale(Long capacite_totale) {
        this.capacite_totale = capacite_totale;
    }

    public Long getSeuil_alerte() {
        return seuil_alerte;
    }

    public void setSeuil_alerte(Long seuil_alerte) {
        this.seuil_alerte = seuil_alerte;
    }

    public Long getNb_reservation() {
        return nb_reservation;
    }

    public void setNb_reservation(Long nb_reservation) {
        this.nb_reservation = nb_reservation;
    }

    public String getAdresse_no_rue() {
        return adresse_no_rue;
    }

    public void setAdresse_no_rue(String adresse_no_rue) {
        this.adresse_no_rue = adresse_no_rue;
    }

    public Double getMontant_participation() {
        return montant_participation;
    }

    public void setMontant_participation(Double montant_participation) {
        this.montant_participation = montant_participation;
    }

    public Event getLocal_evenement() {
        return local_evenement;
    }

    public void setLocal_evenement(Event local_evenement) {
        this.local_evenement = local_evenement;
    }

    public Ville getLocal_ville() {
        return local_ville;
    }

    public void setLocal_ville(Ville local_ville) {
        this.local_ville = local_ville;
    }

    public Devise getLocal_devise() {
        return local_devise;
    }

    public void setLocal_devise(Devise local_devise) {
        this.local_devise = local_devise;
    }

    public String getCodeDevise(){
        return this.local_devise.getCode_devise();
    }
    public String getVille(){
        return local_ville.getLibelle();
    }

    public Long getIdVille(){
        return local_ville.getId_ville();
    }

    public Long getIdDevise(){
        return local_devise.getId_devise();
    }

    public Long getIdEvent(){
        return local_evenement.getId_event();
    }

    public String getPays(){
        return local_ville.getDepartement().getPays().getLibelle();
    }
}
