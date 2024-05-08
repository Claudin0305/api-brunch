package com.brunch.api.entity;

import com.brunch.api.utils.BaseEntity;
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




}
