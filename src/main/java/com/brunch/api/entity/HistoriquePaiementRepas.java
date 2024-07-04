package com.brunch.api.entity;

import com.brunch.api.utils.BaseEntity;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.Date;

@Entity
public class HistoriquePaiementRepas extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_historique_paiement;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_participant", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonBackReference("participantHistoriquePaiement")
    private Participant participant;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_devise", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonBackReference("deviseHistoriquePaiement")
    private Devise deviseHistoriquePaiement;
    private Date date_paiement;
    private Double montant_paye;

    public Long getId_historique_paiement() {
        return id_historique_paiement;
    }

    public void setId_historique_paiement(Long id_historique_paiement) {
        this.id_historique_paiement = id_historique_paiement;
    }

    public Participant getParticipant() {
        return participant;
    }

    public void setParticipant(Participant participant) {
        this.participant = participant;
    }

    public Devise getDeviseHistoriquePaiement() {
        return deviseHistoriquePaiement;
    }

    public void setDeviseHistoriquePaiement(Devise devise) {
        this.deviseHistoriquePaiement = devise;
    }

    public Date getDate_paiement() {
        return date_paiement;
    }

    public void setDate_paiement(Date date_paiement) {
        this.date_paiement = date_paiement;
    }

    public Double getMontant_paye() {
        return montant_paye;
    }

    public void setMontant_paye(Double montant_paye) {
        this.montant_paye = montant_paye;
    }
}
