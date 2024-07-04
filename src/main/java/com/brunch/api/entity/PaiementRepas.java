package com.brunch.api.entity;

import com.brunch.api.utils.BaseEntity;
import com.brunch.api.utils.ModePaiement;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.Date;

@Entity
@Data
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
    private Double don;
    private Double reste_a_payer;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_statut")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonBackReference("statutPaiement")
    private Statut statut;

    @Column(name = "payeur")
    private String payeur;
    @Column(name = "email_payeur")
    private String email_payeur;

    @Enumerated
    @Column(name = "mode_paiement")
    private ModePaiement mode_paiement;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_devise", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonBackReference("devisePaiement")
    private Devise devise;

    public String getLibelle() {
        if (devise == null) {
            return null;
        }
        return devise.getDevise();
    }

    public String getLibelleStatut() {
        if (statut == null) {
            return null;
        }
        return statut.getLibelle();
    }

    public String getNom() {
        if (participant != null) {
            return participant.getNom();

        }
        return null;
    }
    public String getPrenom() {
        if (participant != null) {
            return participant.getPrenom();

        }
        return null;
    }
}
