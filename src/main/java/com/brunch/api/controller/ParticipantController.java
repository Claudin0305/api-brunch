package com.brunch.api.controller;


import com.brunch.api.entity.*;
import com.brunch.api.service.classes.*;
import com.brunch.api.utils.MessageType;
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
@RequestMapping("/participants")
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
    @GetMapping
    public ResponseEntity<List<Participant>> getAllParticipants(){
        return ResponseEntity.ok(participantServiceImplement.getAllParticipants());
    }
    @GetMapping("/{id_participant}")
    public Participant getParticipantById(@PathVariable Long id_participant){
        return participantServiceImplement.getParticipantById(id_participant);
    }
    @PostMapping
    public ResponseEntity<Participant> createParticipant(@Valid @ModelAttribute Participant participant, @RequestParam(value = "id_event") Long id_event, @RequestParam(value = "id_civilite") Long id_civilite, @RequestParam(value = "id_ville") Long id_ville, @RequestParam(value = "id_tranche_age") Long id_tranche_age, @RequestParam(value = "id_local", required = false) String id_local, @RequestParam(value = "id_affiliation", required = false) String id_affiliation) throws MessagingException {
        String username = generateUsername(participant.getNom(), participant.getPrenom());
        while (participantServiceImplement.existsByUsername(username)){
            username = generateUsername(participant.getNom(), participant.getPrenom());
        }
        participant.setUsername(username);
        Ville ville = villeServiceImplement.getVilleById(id_ville);
        TrancheAge trancheAge = tranchesAgeServiceImplement.getTrancheAgeById(id_tranche_age);
        Civilite civilite = civiliteServiceImplement.getCiviliteById(id_civilite);
        if(!id_local.equals("0")){
            Local local = localServiceImplement.getLocalById(Long.parseLong(id_local));
            participant.setLocal_participant(local);
        } if(!id_affiliation.equals("0")){
           Affiliation affiliation = affiliationService.getAffiliationById(Long.parseLong(id_affiliation));
           participant.setAffiliation(affiliation);
        }

        Event event = eventServiceImplement.getEventById(id_event);
        participant.setEvent(event);
        participant.setCivilite_participant(civilite);
        participant.setVille(ville);
        participant.setTranche_age(trancheAge);
        Participant createParticipant = participantServiceImplement.createParticipant(participant);
        Message message = messageServiceImplement.findByMessageType(MessageType.INSCRIPTION);
        emailService.sendEmailFromTemplate(participant.getEmail(), event.getAdr_email_event(), message.getSubject(), createParticipant, message.getLibelleTexte());
        if(!id_local.equals("0")){
            Local local = localServiceImplement.getLocalById(Long.parseLong(id_local));
            local.setNb_reservation(local.getNb_reservation() + 1);
            localServiceImplement.updateLocal(Long.parseLong(id_local), local);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(createParticipant);

    }
@GetMapping("/par-evenement/{id_event}")
public ResponseEntity<List<Participant>> getByAuthorisationListeAndIdEvent(@PathVariable Long id_event){
        List<Participant> participants = participantServiceImplement.findByAuthorisationListe();
        List<Participant> participantList = new ArrayList<>();
    for (Participant p:participants
         ) {
        if(p.getIdEvent() == id_event){
            participantList.add(p);
        }

    }
        return ResponseEntity.ok().body(participantList);
}
    @PutMapping("/{id_civilite}/{id_ville}/{id_tranches_age}/{id_participant}")
    public ResponseEntity<Participant> updateParticipant(@Valid @RequestBody Participant participant, @PathVariable Long id_civilite, @PathVariable Long id_ville, @PathVariable(name = "id_tranches_age", required = false) Long id_tranches_age, @PathVariable Long id_participant){
        Ville ville = villeServiceImplement.getVilleById(id_ville);
        TrancheAge trancheAge = tranchesAgeServiceImplement.getTrancheAgeById(id_tranches_age);
        Civilite civilite = civiliteServiceImplement.getCiviliteById(id_civilite);
        participant.setCivilite_participant(civilite);
        participant.setVille(ville);
//        participant.setTrancheAge(trancheAge);
        Participant updatedParticipant = participantServiceImplement.updateParticipant(id_participant, participant);
        return  ResponseEntity.ok(updatedParticipant);
    }

    @DeleteMapping("/{id_participant}")
    public ResponseEntity<?> deleteResponsableTable(@PathVariable Long id_participant){
        participantServiceImplement.deleteParticipant(id_participant);
        return  ResponseEntity.ok().build();
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

    private static String generateUsername(String nom, String prenom) {
        // Convert the first name to lowercase and remove any spaces
        String formattedFirstName = nom.substring(0, 2)+ "-"+ prenom.substring(0, 2);

        // Generate a random number
        Random random = new Random();
        int randomNumber = random.nextInt(10000); // Change the range as needed

        // Combine the formatted first name and random number to create the username
        String username = formattedFirstName.toLowerCase()+"-" + randomNumber;

        return username;
    }
}