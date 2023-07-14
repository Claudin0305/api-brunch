package com.brunch.api.entity;

import com.brunch.api.utils.BaseEntity;
import com.brunch.api.utils.MessageType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
public class Message extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Lob
    @Column(name = "libelle_texte")
    @NotNull(message = "Ce champ est obligatoire")
    private String libelleTexte;

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    @Column(name = "subject")
    @NotNull(message = "Ce champ est obligatoire")
    private String subject;
    @Enumerated(EnumType.STRING)
    @Column(name = "message_type")
    private MessageType messageType;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLibelleTexte() {
        return libelleTexte;
    }

    public void setLibelleTexte(String libelleTexte) {
        this.libelleTexte = libelleTexte;
    }

    public MessageType getMessageType() {
        return messageType;
    }

    public void setMessageType(MessageType messageType) {
        this.messageType = messageType;
    }
}
