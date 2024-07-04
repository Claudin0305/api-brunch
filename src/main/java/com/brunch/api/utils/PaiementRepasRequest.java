package com.brunch.api.utils;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class PaiementRepasRequest {
    @NotNull(message = "Ce champ est obligatoire!")
   private Long id_statut;
    @NotNull(message = "Ce champ est obligatoire!")
   private Long id_devise;
    @NotNull(message = "Ce champ est obligatoire!")
   private Long id_participant;
    @NotNull(message = "Ce champ est obligatoire!")
   private Double montant_du;
    @NotNull(message = "Ce champ est obligatoire!")
   private Double montant_paye;
    @NotBlank(message = "Ce champ est obligatoire")
    private String mode_paiement;
    private Double don;
}
