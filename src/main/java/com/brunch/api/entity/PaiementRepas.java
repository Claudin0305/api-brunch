package com.brunch.api.entity;

import com.brunch.api.utils.BaseEntity;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.Date;

@Entity
public class PaiementRepas extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_paiement;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_participant", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonBackReference("participantPaiement")
    private Participant participant;
    private Date date_dernier_paiement;
    private Double montant_du;
    private Double montant_paye;
    private Double reste_a_payer;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_statut", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonBackReference("statutPaiement")
    private Statut statut;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_devise", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonBackReference("devisePaiement")
    private Devise devise;

    public Long getId_paiement() {
        return id_paiement;
    }

    public void setId_paiement(Long id_paiement) {
        this.id_paiement = id_paiement;
    }

    public Participant getParticipant() {
        return participant;
    }

    public void setParticipant(Participant participant) {
        this.participant = participant;
    }

    public Date getDate_dernier_paiement() {
        return date_dernier_paiement;
    }

    public void setDate_dernier_paiement(Date date_dernier_paiement) {
        this.date_dernier_paiement = date_dernier_paiement;
    }

    public Double getMontant_du() {
        return montant_du;
    }

    public void setMontant_du(Double montant_du) {
        this.montant_du = montant_du;
    }

    public Double getMontant_paye() {
        return montant_paye;
    }

    public void setMontant_paye(Double montant_paye) {
        this.montant_paye = montant_paye;
    }

    public Double getReste_a_payer() {
        return reste_a_payer;
    }

    public void setReste_a_payer(Double reste_a_payer) {
        this.reste_a_payer = reste_a_payer;
    }

    public Statut getStatut() {
        return statut;
    }

    public void setStatut(Statut statut) {
        this.statut = statut;
    }

    public Devise getDevise() {
        return devise;
    }

    public void setDevise(Devise devise) {
        this.devise = devise;
    }
}
