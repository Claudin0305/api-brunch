package com.brunch.api.entity;


import com.brunch.api.utils.BaseEntity;
import com.brunch.api.utils.FormatEvent;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.Date;
import java.util.List;

@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id_participant")
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"id_event", "email", "nom", "prenom"})})
public class Participant extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_participant;
    @Column(name = "date_paiement")
    private Date datePaiement;

    @Column(name = "mode_paiement")
    private String modePiement;

    @NotNull(message = "Ce champ est obligatoire")
    @Column(name = "nom")
//    @Pattern(regexp = "[a-zA-ZÀÂÆÇÈÉÊËÎÏÔŒÙÛÜÝàâæçèéêëîïôœùûüý -\s]", message = "Le format du nom est invalide")
    private String nom;

    @NotNull(message = "Ce champ est obligatoire")
    @Column(name = "prenom")
//    @Pattern(regexp  = "[a-zA-ZÀÂÆÇÈÉÊËÎÏÔŒÙÛÜÝàâæçèéêëîïôœùûüý -\s]", message = "Le format du prénom est invalide")
    private String prenom;
    @Email(message = "Email invalide")
    @Column(name = "email")
    private String email;
    @JsonManagedReference("participantPaiement")
    @OneToMany(mappedBy = "participant", cascade = CascadeType.REMOVE)
    private List<PaiementRepas> paiementRepas;
    @JsonManagedReference("participantHistoriquePaiement")
    @OneToMany(mappedBy = "participant", cascade = CascadeType.REMOVE)
    private List<HistoriquePaiementRepas> historiquePaiementRepas;

    @JsonManagedReference("participantDon")
    @OneToMany(mappedBy = "participant", cascade = CascadeType.REMOVE)
    private List<Don> donList;
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Column(name = "username")
    private String username;
    @Column(name = "email_payeur")
    private String email_payeur;
    @Column(name = "payeur")
    private String payeur;
    @Column(name = "statut_payment", columnDefinition = "bit(1) default false")
    private boolean statut_payment;

    @Column(name = "statut_inscription", columnDefinition = "bit(1) default false")
    private boolean statutParticipant;


    //    @Pattern(regexp = "\\\\+(?:[0-9]?){6,14}[0-9]$", message = "Le numero est invalide")
    @Column(name = "tel_participant")
    private String telParticipant;



    @Column(name = "authorisation_liste", columnDefinition = "bit(1) default false")
    private boolean authorisationListe;
    @Column(name = "email_valide", columnDefinition = "bit(1) default false")
    private boolean email_valide;
    @Column(name = "abonnement_newsletter", columnDefinition = "bit(1) default false")
    private boolean abonnement_newsletter;

//    @NotNull(message = "Ce champ est obligatoire")
//    @Column(name = "affiliation")
//    private String affiliation;

    public void setAffiliation(Affiliation affiliation) {
        this.affiliation = affiliation;
    }

    public Affiliation getAffiliation() {
        return affiliation;
    }

    @ManyToOne(fetch = FetchType.LAZY)
//    @NotNull
    @JoinColumn(name = "id_affiliation", nullable = true)
    @OnDelete(action = OnDeleteAction.RESTRICT)
    @JsonBackReference("affiliation")
    private Affiliation affiliation;

    public String getInscritPar() {
        return inscritPar;
    }

    public void setInscritPar(String inscrit_par) {
        this.inscritPar = inscrit_par;
    }

    public Ville getVille_participant() {
        return ville_participant;
    }

    @Column(name = "inscrit_par")
    private String inscritPar;

    public Local getLocal_participant() {
        return local_participant;
    }

    public void setLocal_participant(Local local_participant) {
        this.local_participant = local_participant;
    }

    @ManyToOne(fetch = FetchType.LAZY)
//    @NotNull
    @JoinColumn(name = "id_local", nullable = true)
    @OnDelete(action = OnDeleteAction.RESTRICT)
    @JsonBackReference("local_participant")
    private Local local_participant;

    public FormatEvent getModeParticipation() {
        return modeParticipation;
    }

    public void setModeParticipation(FormatEvent mode_participation) {
        this.modeParticipation = mode_participation;
    }


    public Event getParticipantEvent() {
        return participantEvent;
    }

    public void setParticipantEvent(Event participantEvent) {
        this.participantEvent = participantEvent;
    }

    @Enumerated(EnumType.STRING)
    @Column(name = "mode_participation")
    private FormatEvent modeParticipation;

//    public ModePaiement getModePaiement() {
//        return modePaiement;
//    }
//
//    public void setModePaiement(ModePaiement modePaiement) {
//        this.modePaiement = modePaiement;
//    }

//    @Enumerated(EnumType.STRING)
//    @Column(name = "mode_paiement")
//    private ModePaiement modePaiement;
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


    public String getTelParticipant() {
        return telParticipant;
    }

    public void setTelParticipant(String telParticipant) {
        this.telParticipant = telParticipant;
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


    public Long getIdEvent() {
        return participantEvent.getId_event();
    }

    public String getNomPays() {
        return this.ville_participant.getDepartement().getPays().getLibelle();
    }

    public String getNomAffiliation() {
        if (affiliation != null) {
            return affiliation.getNom_affiliation();
        }
        return null;
    }

    public Long getIdAffiliation() {
        if (affiliation != null) {
            return affiliation.getAffiliationId();
        }
        return null;
    }

    public Long getIdLocal() {
        if (local_participant != null) {
            return local_participant.getId_local();
        }
        return null;
    }

    public String getDevise() {
        if (local_participant != null) {
            return local_participant.getCodeDevise();
        }
        return null;
    }

    public Long getIdDevise() {
        if (local_participant != null) {
            return local_participant.getIdDevise();
        }
        return null;
    }

    public Double getMontant_participation() {
        if (local_participant != null) {
            return local_participant.getMontant_participation();
        }

        return null;
    }
//    public Long getIdTrancheAge(){
//        return tranche_age.getId_tranche_age();
//    }

    public Long getIdCivilite() {
        return civilite_participant.getId_civilite();
    }

    public String getLibelleLocal() {
        if (local_participant != null) {
            return local_participant.getLibelle();
        }
        return null;
    }

    public String getNomCivilite() {
        return civilite_participant.getLibelle();
    }

    public List<PaiementRepas> getPaiementRepas() {
        return paiementRepas;
    }

    public void setPaiementRepas(List<PaiementRepas> paiementRepas) {
        this.paiementRepas = paiementRepas;
    }

    public List<HistoriquePaiementRepas> getHistoriquePaiementRepas() {
        return historiquePaiementRepas;
    }

    public void setHistoriquePaiementRepas(List<HistoriquePaiementRepas> historiquePaiementRepas) {
        this.historiquePaiementRepas = historiquePaiementRepas;
    }

    public String getPayeur() {
        return this.payeur;
    }

    public String getEmail_payeur() {
        return this.email_payeur;
    }

    public void setEmail_payeur(String email_payeur) {
        this.email_payeur = email_payeur;
    }

    public void setPayeur(String payeur) {
        this.payeur = payeur;
    }

    public boolean getStatut_payment() {
        return statut_payment;
    }

    public void setStatut_payment(boolean statut_payment) {
        this.statut_payment = statut_payment;
    }

    public boolean getStatutParticipant() {
        return this.statutParticipant;
    }

    public void setStatutParticipant(boolean statut_participant) {
        this.statutParticipant = statut_participant;
    }

    public Date getDatePaiement() {
        return datePaiement;
    }

    public void setDatePaiement(Date datePaiement) {
        this.datePaiement = datePaiement;
    }

    public String getModePiement() {
        return modePiement;
    }

    public void setModePiement(String modePiement) {
        this.modePiement = modePiement;
    }

    public List<Don> getDonList(){
        return donList;
    }

    public boolean getStatusAffiliation(){
        if(affiliation != null){
            return affiliation.getValidate();
        }
        return false;
    }
}
