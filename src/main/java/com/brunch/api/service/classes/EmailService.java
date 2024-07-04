package com.brunch.api.service.classes;

import com.brunch.api.entity.Event;
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

    public void sendEmailFromTemplate(String to, String from, String subject, Participant participant, String content, Event event) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        message.setFrom(new InternetAddress(from));
        message.setRecipients(Message.RecipientType.TO, to);
        message.setRecipients(Message.RecipientType.CC, event.getAdr_email_event());
        message.setSubject(subject);

        // Do something here to remplace variable data
        content = content.replace("${nom}", participant.getNom());
        content = content.replace("${prenom}", participant.getPrenom());
        content = content.replace("${username}", participant.getUsername());
        content = content.replace("${email}", participant.getEmail());
        content = content.replace("${pays}", participant.getNomPays());
//        str.substring(0, 1).toUpperCase() + str.substring(1)
        String mode = participant.getModeParticipation().toString();
        content = content.replace("${mode_participation}", mode.substring(0,1).toUpperCase() + mode.substring(1).toLowerCase());
        content = content.replace("${civilite}", participant.getCivilite_participant().getLibelle());

        message.setContent(content, "text/html; charset=utf-8");
        mailSender.send(message);
    }
}
