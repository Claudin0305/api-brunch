package com.brunch.api.entity;

import com.brunch.api.utils.BaseEntity;
import com.brunch.api.utils.EventType;
import com.brunch.api.utils.FormatEvent;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

@Entity
//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id_event")
public class Event extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_event;
    @Enumerated(EnumType.STRING)
    @Column(name = "format_event")
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
    @Column(name = "text_descriptif")
    @NotNull(message = "Ce champ est obligatoire")
    private String text_descriptif;
    @Email(message = "Valeur invalide")
    @Column(name = "adr_email_event")
    private String adr_email_event;
//    @Lob
//    @NotNull(message = "Ce champ est obligatoire")
//    @Column(name = "logo_image_event")
//    private byte[] imageEvent;

    @Column(name = "cible_participation")
    @NotNull(message = "Ce champ est obligatoire")
    private Long cible_participation;

    @Column(name = "code")
    private String code = "00" + this.id_event;
    public EventType getEventType() {
        return eventType;
    }

    public void setEventType(EventType eventType) {
        this.eventType = eventType;
    }

    @Column(name = "event_type")
    @Enumerated(EnumType.STRING)
    @NotNull(message = "Ce champ est obligatoire")
    private EventType eventType;
//    @JsonManagedReference("localEvenement")
//    @OneToMany(mappedBy = "local_evenement", cascade = CascadeType.REMOVE)
//    private List<Local> locals;

    public List<EventImage> getEventImages() {
        return eventImages;
    }

    public void setEventImages(List<EventImage> eventImages) {
        this.eventImages = eventImages;
    }

//    @URL
    @Column(name = "url")
    private String url;
//    @JsonManagedReference("eventLocal")
//    @OneToMany(mappedBy = "event_local", cascade = CascadeType.REMOVE)
//    private List<LocalBrunch> localBrunches;

//    @JsonManagedReference("eventResponsableTable")
//    @OneToMany(mappedBy = "event_", cascade = CascadeType.REMOVE)
//    private List<ResponsableTable> responsableTables;
    @JsonManagedReference("eventImage")
    @OneToMany(mappedBy = "event__", cascade = CascadeType.REMOVE)
    private List<EventImage> eventImages;
//    @JsonManagedReference("participantEvent")
//    @OneToMany(mappedBy = "participantEvent", cascade = CascadeType.REMOVE)
//    private List<Participant> participants;

//    public List<Participant> getParticipants() {
//        return participants;
//    }

//    public void setParticipants(List<Participant> participants) {
//        this.participants = participants;
//    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }



//    public List<ResponsableTable> getResponsableTables() {
//        return responsableTables;
//    }

//    public void setResponsableTables(List<ResponsableTable> responsableTables) {
//        this.responsableTables = responsableTables;
//    }

    public Long getId_event() {
        return id_event;
    }

    public void setId_event(Long id_event) {
        this.id_event = id_event;
    }

    public FormatEvent getFormat_event() {
        return format_event;
    }

    public void setFormat_event(FormatEvent formatEvent) {
        this.format_event = formatEvent;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Date getDate_debut() {
        return date_debut;
    }

    public void setDate_debut(Date dateDebut) {
        this.date_debut = dateDebut;
    }

    public Date getDate_fin() {
        return date_fin;
    }

    public void setDate_fin(Date dateFin) {
        this.date_fin = dateFin;
    }

    public String getHeure_debut() {
        return heure_debut;
    }

    public void setHeure_debut(String heureDebut) {
        this.heure_debut = heureDebut;
    }

    public String getHeure_fin() {
        return heure_fin;
    }

    public void setHeure_fin(String heureFin) {
        this.heure_fin = heureFin;
    }

    public String getText_descriptif() {
        return text_descriptif;
    }

    public void setText_descriptif(String textDescriptif) {
        this.text_descriptif = textDescriptif;
    }

    public String getAdr_email_event() {
        return adr_email_event;
    }

    public void setAdr_email_event(String adrEmailEvent) {
        this.adr_email_event = adrEmailEvent;
    }

//    public byte[] getImageEvent() {
//        return imageEvent;
//    }
//
//    public void setImageEvent(byte[] imageEvent) {
//        this.imageEvent = imageEvent;
//    }

    public Long getCible_participation() {
        return cible_participation;
    }

    public void setCible_participation(Long cibleParticipation) {
        this.cible_participation = cibleParticipation;
    }
}
