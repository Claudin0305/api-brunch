package com.brunch.api.utils;

import jakarta.validation.constraints.Min;
import lombok.Data;

@Data
public class DonObjectifRequest {
    private Long id;
    @Min(value = 0, message = "La valeur doit être superieur ou egale 0")
    private Double objectif;
    @Min(value = 0, message = "La valeur doit être superieur ou egale 0")
    private Double don;
    @Min(value = 0, message = "La valeur doit être superieur ou egale 0")
    private Double promesse;
    private String option;
    private String operation;
}
