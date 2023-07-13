package com.brunch.api.entity;


import com.brunch.api.utils.BaseEntity;
import com.brunch.api.utils.FormatEvent;
import com.brunch.api.utils.ModePaiement;
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
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id_participant")
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"id_event", "email"})})
public class Participant extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_participant;

    @NotNull(message = "Ce champ est obligatoire")
    @Column(name = "nom")
    @Pattern(regexp = "^[a-zA-ZÀ-ú\\\\-\\\\s]*", message = "Le format du nom est invalide")
    private String nom;

    @NotNull(message = "Ce champ est obligatoire")
    @Column(name = "prenom")
    @Pattern(regexp = "^[a-zA-ZÀ-ú\\\\-\\\\s ]*", message = "Le format du prénom est invalide")
    private String prenom;
    @Email(message = "Email invalide")
    @Column(name = "email")
    private String email;


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Column(name = "username")
    private String username;


//    @Pattern(regexp = "\\\\+(?:[0-9]?){6,14}[0-9]$", message = "Le numero est invalide")
    @Column(name = "tel_participant")
    private String tel_participant;


    @Column(name = "authorisation_liste", columnDefinition = "bit(1) default false")
    private boolean authorisationListe;
    @Column(name = "email_valide", columnDefinition = "bit(1) default false")
    private boolean email_valide;
    @Column(name = "abonnement_newsletter", columnDefinition = "bit(1) default false")
    private boolean abonnement_newsletter;

//    @NotNull(message = "Ce champ est obligatoire")
    @Column(name = "affiliation")
    private String affiliation;

    public String getInscrit_par() {
        return inscrit_par;
    }

    public void setInscrit_par(String inscrit_par) {
        this.inscrit_par = inscrit_par;
    }

    public Ville getVille_participant() {
        return ville_participant;
    }

    @Column(name = "inscrit_par")
    private String inscrit_par;

    public Local getLocal_participant() {
        return local_participant;
    }

    public void setLocal_participant(Local local_participant) {
        this.local_participant = local_participant;
    }

    @ManyToOne(fetch = FetchType.LAZY)
//    @NotNull
    @JoinColumn(name = "id_local", nullable = true)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonBackReference("local_participant")
    private Local local_participant;
    public FormatEvent getMode_participation() {
        return mode_participation;
    }

    public void setMode_participation(FormatEvent mode_participation) {
        this.mode_participation = mode_participation;
    }


    public Event getParticipantEvent() {
        return participantEvent;
    }

    public void setParticipantEvent(Event participantEvent) {
        this.participantEvent = participantEvent;
    }

    @Enumerated(EnumType.STRING)
    @Column(name = "mode_participation")
    private FormatEvent mode_participation;

    public ModePaiement getModePaiement() {
        return modePaiement;
    }

    public void setModePaiement(ModePaiement modePaiement) {
        this.modePaiement = modePaiement;
    }

    @Enumerated(EnumType.STRING)
    @Column(name = "mode_paiement")
    private ModePaiement modePaiement;
//    @OneToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "id_civilite", referencedColumnName = "id_civilite")
//    private Civilite civilite_participant;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
//    @NotNull
    @JoinColumn(name = "id_civilite", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonBackReference("participantCivilite")
    private Civilite civilite_participant;


    public TrancheAge getTranche_age() {
        return tranche_age;
    }

    public void setTranche_age(TrancheAge tranche_age) {
        this.tranche_age = tranche_age;
    }

    @ManyToOne(fetch = FetchType.LAZY)
//    @NotNull
    @JoinColumn(name = "id_tranche_age")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonBackReference("participantTrancheAge")
    private TrancheAge tranche_age;

    @ManyToOne(fetch = FetchType.LAZY)
//    @NotNull
    @JoinColumn(name = "id_event", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonBackReference("participantEvent")
    private Event participantEvent;


//    public Event getEventEvent() {
//        return participantEvent;
//    }

    public void setEvent(Event event) {
        this.participantEvent = event;
    }

//    public Ville getVille_participant() {
//        return ville_participant;
//    }

    public void setVille_participant(Ville ville_participant) {
        this.ville_participant = ville_participant;
    }


    public Long getId_participant() {
        return id_participant;
    }

    public void setId_participant(Long id_participant) {
        this.id_participant = id_participant;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public String getTel_participant() {
        return tel_participant;
    }

    public void setTel_participant(String telParticipant) {
        this.tel_participant = telParticipant;
    }


    public boolean isAuthorisationListe() {
        return authorisationListe;
    }

    public void setAuthorisationListe(boolean authorisationListe) {
        this.authorisationListe = authorisationListe;
    }

    public boolean isEmail_valide() {
        return email_valide;
    }

    public void setEmail_valide(boolean emailValide) {
        this.email_valide = emailValide;
    }

    public boolean isAbonnement_newsletter() {
        return abonnement_newsletter;
    }

    public void setAbonnement_newsletter(boolean abonnementNewletter) {
        this.abonnement_newsletter = abonnementNewletter;
    }

    public String getAffiliation() {
        return affiliation;
    }

    public void setAffiliation(String affiliation) {
        this.affiliation = affiliation;
    }

    public Civilite getCivilite_participant() {
        return civilite_participant;
    }

    public void setCivilite_participant(Civilite civilite_participant) {
        this.civilite_participant = civilite_participant;
    }

    public Ville getVille() {
        return ville_participant;
    }

    public void setVille(Ville ville) {
        this.ville_participant = ville;
    }

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
//    @NotNull
    @JoinColumn(name = "id_ville", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonBackReference("participantVille")
//    @JsonIgnore
    private Ville ville_participant;

    public Long getIdEvent(){
        return participantEvent.getId_event();
    }

    public String getNomPays(){
        return this.ville_participant.getDepartement().getPays().getLibelle();
    }

}