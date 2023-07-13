package com.brunch.api.service.classes;

import com.brunch.api.entity.Participant;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;


@Service
public class EmailService {
    @Autowired
    private JavaMailSender mailSender;

    public void sendEmailFromTemplate(String to, String from, String subject, Participant participant, String content) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        message.setFrom(new InternetAddress(from));
        message.setRecipients(Message.RecipientType.TO, to);
        message.setSubject(subject);

        // Do something here to remplace variable data
        content = content.replace("${nom}", participant.getNom());
        content = content.replace("${prenom}", participant.getPrenom());
        content = content.replace("${username}", participant.getUsername());
        content = content.replace("${civilite}", participant.getCivilite_participant().getLibelle());

        message.setContent(content, "text/html; charset=utf-8");
        mailSender.send(message);
    }
}