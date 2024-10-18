package com.brunch.api.entity;

import com.brunch.api.utils.BaseEntity;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table
public class DonObjectifPromesse extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double objectif = 0.0;
    private Double promesse = 0.0;
    private Double don = 0.0;
}
