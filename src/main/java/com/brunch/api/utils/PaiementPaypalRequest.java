package com.brunch.api.utils;

import lombok.Data;

@Data
public class PaiementPaypalRequest {
    private String payeur;
    private String email_payeur;
    private Double montant_paye;
}
