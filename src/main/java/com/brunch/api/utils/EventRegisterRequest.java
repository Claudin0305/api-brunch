package com.brunch.api.utils;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;

@Data
@Valid
public class EventRegisterRequest {
    @Enumerated(EnumType.STRING)
    private FormatEvent format_event;
    @NotNull(message = "Ce champ est obligatoire")
//    @Future(message = "La date doit etre superieure à la date du jour")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date date_debut;
    @NotNull(message = "Ce champ est obligatoire")
//    @Future(message = "La date doit etre superieure à la date du jour")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date date_fin;

    @NotNull(message = "Ce champ est obligatoire")
    @Pattern(regexp = "^(?:2[0-3]|[01][0-9]):[0-5][0-9]$", message = "Le format de l'heure est invalide")
    private String heure_debut;

    @NotNull(message = "Ce champ est obligatoire")
    @Pattern(regexp = "^(?:2[0-3]|[01][0-9]):[0-5][0-9]$", message = "Le format de l'heure est invalide")
    private String heure_fin;
    @Lob
    @NotNull(message = "Ce champ est obligatoire")
    private String text_descriptif;
    @Email(message = "Valeur invalide")
    private String adr_email_event;

    @NotNull(message = "Ce champ est obligatoire")
    private Long cible_participation;


    @Enumerated(EnumType.STRING)
    @NotNull(message = "Ce champ est obligatoire")
    private EventType eventType;

//    @NotNull(message = "Ce champ est obligatoire")
    private MultipartFile file;
    private String url;
    private String image_change;


}
