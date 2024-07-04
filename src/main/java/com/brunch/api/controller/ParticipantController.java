package com.brunch.api.controller;


import com.brunch.api.entity.*;
import com.brunch.api.service.classes.*;
import com.brunch.api.utils.FormatEvent;
import com.brunch.api.utils.MessageType;
import com.brunch.api.utils.MultiplePaymentRequest;
import com.brunch.api.utils.PaymentRequest;
import jakarta.mail.MessagingException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@ControllerAdvice
@RequestMapping("/api/participants")
public class ParticipantController {
    @Autowired
    private ParticipantServiceImplement participantServiceImplement;
    @Autowired
    private CiviliteServiceImplement civiliteServiceImplement;
    @Autowired
    private TranchesAgeServiceImplement tranchesAgeServiceImplement;
    @Autowired
    private VilleServiceImplement villeServiceImplement;
    @Autowired
    private LocalServiceImplement localServiceImplement;
    @Autowired
    private EventServiceImplement eventServiceImplement;
    @Autowired
    private AffiliationServiceImpl affiliationService;
    @Autowired
    private EmailService emailService;
    @Autowired
    private MessageServiceImplement messageServiceImplement;

    private static String generateUsername(String nom, String prenom) {
        // Convert the first name to lowercase and remove any spaces
        String formattedFirstName = nom.substring(0, 2) + "-" + prenom.substring(0, 2);

        // Generate a random number
        Random random = new Random();
        int randomNumber = random.nextInt(10000); // Change the range as needed

        // Combine the formatted first name and random number to create the username
        String username = formattedFirstName.toLowerCase() + "-" + randomNumber;

        return username;
    }

    @GetMapping
    public ResponseEntity<List<Participant>> getAllParticipants() {
        return ResponseEntity.ok(participantServiceImplement.getAllParticipants());
    }

    @GetMapping("/{id_participant}")
    public Participant getParticipantById(@PathVariable Long id_participant) {
        return participantServiceImplement.getParticipantById(id_participant);
    }

    @GetMapping("/by/{username}")
    public Participant getParticipantByUsername(@PathVariable String username) {
        return participantServiceImplement.findByUsername(username).orElse(null);
    }

    @PostMapping
    public ResponseEntity<Participant> createParticipant(@Valid @ModelAttribute Participant participant, @RequestParam(value = "id_event") Long id_event, @RequestParam(value = "id_civilite") Long id_civilite, @RequestParam(value = "id_ville") Long id_ville, @RequestParam(value = "id_local", required = false) String id_local, @RequestParam(value = "id_affiliation", required = false) String id_affiliation) throws MessagingException {
        String username = generateUsername(participant.getNom(), participant.getPrenom());
        while (participantServiceImplement.existsByUsername(username)) {
            username = generateUsername(participant.getNom(), participant.getPrenom());
        }
        participant.setUsername(username);
//        Ville ville = villeServiceImplement.getVilleById(id_ville);
//        TrancheAge trancheAge = tranchesAgeServiceImplement.getTrancheAgeById(id_tranche_age);
//        Civilite civilite = civiliteServiceImplement.getCiviliteById(id_civilite);
        if (!id_local.equals("0")) {
            Local local = localServiceImplement.getLocalById(Long.parseLong(id_local));
            participant.setLocal_participant(local);
        }
        if (!id_affiliation.equals("0")) {
//           Affiliation affiliation = affiliationService.getAffiliationById(Long.parseLong(id_affiliation));
            participant.setAffiliation(affiliationService.getAffiliationById(Long.parseLong(id_affiliation)));
        }

        Event event = eventServiceImplement.getEventById(id_event);
        participant.setEvent(eventServiceImplement.getEventById(id_event));
        participant.setCivilite_participant(civiliteServiceImplement.getCiviliteById(id_civilite));
        participant.setVille(villeServiceImplement.getVilleById(id_ville));
//        participant.setTranche_age(tranchesAgeServiceImplement.getTrancheAgeById(id_tranche_age));
        Participant createParticipant = participantServiceImplement.createParticipant(participant);
//        Message message = messageServiceImplement.findByMessageType(MessageType.INSCRIPTION);
//        emailService.sendEmailFromTemplate(participant.getEmail(), event.getAdr_email_event(), message.getSubject(), createParticipant, message.getLibelleTexte(), event);
        if (!id_local.equals("0")) {
            Local local = localServiceImplement.getLocalById(Long.parseLong(id_local));
            local.setNb_reservation(local.getNb_reservation() + 1);
            localServiceImplement.updateLocal(Long.parseLong(id_local), local);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(createParticipant);

    }

    @GetMapping("/par-evenement/{id_event}")
    public ResponseEntity<List<Participant>> getByAuthorisationListeAndIdEvent(@PathVariable Long id_event) {
        List<Participant> participants = participantServiceImplement.findByAuthorisationListe();
        List<Participant> participantList = new ArrayList<>();
        for (Participant p : participants) {
            if (p.getIdEvent() == id_event) {
                participantList.add(p);
            }

        }
        return ResponseEntity.ok().body(participantList);
    }

    @GetMapping("/all/{id_event}")
    public ResponseEntity<List<Participant>> getByIdEvent(@PathVariable Long id_event) {
        List<Participant> participants = participantServiceImplement.getAllParticipants();
        List<Participant> participantList = new ArrayList<>();
        for (Participant p : participants) {
            if (p.getIdEvent() == id_event) {
                participantList.add(p);
            }

        }
        return ResponseEntity.ok().body(participantList);
    }

    @PostMapping("/send-message")
    public ResponseEntity<?> sendMessage(@RequestParam(value = "id_event") Long id_event, @RequestParam(value = "id_participant") Long id_participant) throws MessagingException {
        Message message = messageServiceImplement.findByMessageType(MessageType.INSCRIPTION);
        Event event = eventServiceImplement.getEventById(id_event);
        Participant participant = participantServiceImplement.getParticipantById(id_participant);
        System.out.println("Message en cours...");
        emailService.sendEmailFromTemplate(participant.getEmail(), event.getAdr_email_event(), message.getSubject(), participant, message.getLibelleTexte(), event);
        return ResponseEntity.ok().body("Email send");
    }

    @PutMapping("/{username}")
    public ResponseEntity<?> updateParticipant(@Valid @ModelAttribute Participant participant, @RequestParam(value = "id_civilite") Long id_civilite, @RequestParam(value = "id_ville") Long id_ville, @RequestParam(value = "id_tranche_age") Long id_tranche_age, @RequestParam(value = "id_local", required = false) String id_local, @RequestParam(value = "id_affiliation", required = false) String id_affiliation, @PathVariable String username) {
        Ville ville = villeServiceImplement.getVilleById(id_ville);
        Participant p = participantServiceImplement.findByUsername(username).orElse(null);
        if (p != null) {
            Participant participantToUpdate = participantServiceImplement.getParticipantById(p.getId_participant());
            Local localBefore = localServiceImplement.getLocalById(participantToUpdate.getIdLocal());
            TrancheAge trancheAge = tranchesAgeServiceImplement.getTrancheAgeById(id_tranche_age);
            Civilite civilite = civiliteServiceImplement.getCiviliteById(id_civilite);
            participant.setCivilite_participant(civilite);
            participant.setVille(ville);
            participant.setTranche_age(trancheAge);
            if (!id_local.equals("0")) {
                Local local = localServiceImplement.getLocalById(Long.parseLong(id_local));
                participant.setLocal_participant(local);
            }
            if (!id_affiliation.equals("0")) {
                Affiliation affiliation = affiliationService.getAffiliationById(Long.parseLong(id_affiliation));
                participant.setAffiliation(affiliation);
            }
            Participant updatedParticipant = participantServiceImplement.updateParticipant(p.getId_participant(), participant);
            if (!id_local.equals("0")) {
                Local local = localServiceImplement.getLocalById(Long.parseLong(id_local));
                local.setNb_reservation(local.getNb_reservation() + 1);
                localServiceImplement.updateLocal(Long.parseLong(id_local), local);
            }
            localBefore.setNb_reservation(localBefore.getNb_reservation() - 1);
            localServiceImplement.updateLocal(localBefore.getId_local(), localBefore);
            return ResponseEntity.ok(updatedParticipant);
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error!");
    }

    @DeleteMapping("/{id_participant}")
//    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<?> deleteResponsableTable(@PathVariable Long id_participant) {
        participantServiceImplement.deleteParticipant(id_participant);
        return ResponseEntity.ok().build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<Map<String, String>> handleValidationException(MethodArgumentNotValidException exception) {
        Map<String, String> errors = new HashMap<>();
        exception.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return ResponseEntity.badRequest().body(errors);
    }
//    @DeleteMapping("/{id_participant}")
//    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
//    public ResponseEntity<?> deleteParticipant(@PathVariable Long id_participant){
//        participantServiceImplement.deleteParticipant(id_participant);
//        return  ResponseEntity.ok().build();
//    }

    @GetMapping("/find/{info}")
    public ResponseEntity<?> getParticipant(@PathVariable String info) {
        Participant participant = participantServiceImplement.findByUsernameOrEmailOrPhone(info);
        if (participant == null) {
            return ResponseEntity.notFound().build();
        }


        Map<String, Object> errors = new HashMap<>();
        if (!participant.getPaiementRepas().isEmpty()) {
            errors.put("participant", "Ce participant a déjà payé!");
            System.out.println(errors.get("participant"));
            return ResponseEntity.badRequest().body(errors);
            //return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors.put("participant", "Ce participant a déjà payé!"));
        }
        List<Participant> participants = participantServiceImplement.findByInscritPar(participant.getUsername());
        Map<String, Object> response = new HashMap<>();
        response.put("inscrit", participants);
        response.put("participant", participant);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/payments")
    public ResponseEntity<?> payment(@RequestBody PaymentRequest paymentRequest) {
        Participant participant = participantServiceImplement.getParticipantById(paymentRequest.getId());
        if (participant == null) {
            return ResponseEntity.notFound().build();
        }


        Map<String, Object> errors = new HashMap<>();
        if (!participant.getPaiementRepas().isEmpty()) {
            errors.put("participant", "Ce participant a déjà payé!");
            System.out.println(errors.get("participant"));
            return ResponseEntity.badRequest().body(errors);
            //return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors.put("participant", "Ce participant a déjà payé!"));
        }
        participant.setStatut_payment(true);
        participant.setStatut_participant(true);
        participant.setDatePaiement(new Date());
        return ResponseEntity.ok(participantServiceImplement.updateParticipant(paymentRequest.getId(), participant));
    }

    @PostMapping("/payments-multiple")
    public ResponseEntity<?> paymentMultiple(@RequestBody MultiplePaymentRequest multiplePaymentRequest) {
        Long[] data = multiplePaymentRequest.getIds();
        for (Long id : data) {
            Participant participant = participantServiceImplement.getParticipantById(id);
            if (participant != null && !participant.getStatut_payment() && !participant.getStatut_participant()) {
                participant.setStatut_payment(true);
                participant.setStatut_participant(true);
                participant.setDatePaiement(new Date());
                participantServiceImplement.updateParticipant(id, participant);
            }

        }
        return ResponseEntity.ok().build();
//        Participant participant = participantServiceImplement.getParticipantById(paymentRequest.getId());
//        if (participant == null) {
//            return ResponseEntity.notFound().build();
//        }
//
//
//        Map<String, Object> errors = new HashMap<>();
//        if (!participant.getPaiementRepas().isEmpty()) {
//            errors.put("participant", "Ce participant a déjà payé!");
//            System.out.println(errors.get("participant"));
//            return ResponseEntity.badRequest().body(errors);
//            //return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors.put("participant", "Ce participant a déjà payé!"));
//        }
//        participant.setStatut_payment(true);
//        participant.setStatut_participant(true);
//        participant.setDatePaiement(new Date());
//        return ResponseEntity.ok(participantServiceImplement.updateParticipant(paymentRequest.getId(), participant));
    }

    @GetMapping("/mode-participation")
    public ResponseEntity<List<Participant>> findModeParticipation() {
        return ResponseEntity.ok().body(participantServiceImplement.findByModeParticipation(FormatEvent.PRESENTIEL));
    }
}