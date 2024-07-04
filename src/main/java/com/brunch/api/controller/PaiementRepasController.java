package com.brunch.api.controller;


import com.brunch.api.entity.*;
import com.brunch.api.service.classes.*;
import com.brunch.api.utils.FormatEvent;
import com.brunch.api.utils.ModePaiement;
import com.brunch.api.utils.PaiementPaypalRequest;
import com.brunch.api.utils.PaiementRepasRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@ControllerAdvice
@RequestMapping("/api/paiement-repas")
public class PaiementRepasController {
    @Autowired
    private PaiementRepasServiceImpl paiementRepasService;
    @Autowired
    private DeviseServiceImplement deviseServiceImplement;
    @Autowired
    private ParticipantServiceImplement participantServiceImplement;
    @Autowired
    private StatutSeviceImplement statutSeviceImplement;


    @GetMapping
    public List<PaiementRepas> getAllPaiementRepas() {
        return paiementRepasService.getAllPaiementRepas();
    }

    @GetMapping("/{paiementRepasId}")
//    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public PaiementRepas getPaiementRepasById(@PathVariable Long paiementRepasId) {
        return paiementRepasService.getPaiementRepasById(paiementRepasId);
    }

    @PostMapping
//    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<?> createPaiementRepas(@Valid @RequestBody PaiementRepasRequest paiementRepasRequest) {
        Devise devise = deviseServiceImplement.getDeviseById(paiementRepasRequest.getId_devise());
        Statut statut = statutSeviceImplement.getStatutById(paiementRepasRequest.getId_statut());
        Participant participant = participantServiceImplement.getParticipantById(paiementRepasRequest.getId_participant());
        PaiementRepas paiementRepas = new PaiementRepas();
        paiementRepas.setMontant_du(paiementRepasRequest.getMontant_du());
        paiementRepas.setMontant_paye(paiementRepasRequest.getMontant_paye());
        paiementRepas.setDon(paiementRepasRequest.getDon());
        paiementRepas.setReste_a_payer(paiementRepasRequest.getMontant_du() - paiementRepasRequest.getMontant_paye());
        Map<String, String> errors = new HashMap<>();
        switch (paiementRepasRequest.getMode_paiement()) {
            case "DIFFERE":
                paiementRepas.setMode_paiement(ModePaiement.DIFFERE);
                break;
            case "IMMEDIAT":
                paiementRepas.setMode_paiement(ModePaiement.IMMEDIAT);
                break;
        }
        if (devise == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors.put("devise", "Devise introuvable!"));
        }
        if (statut == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors.put("statut", "Statut introuvable!"));
        }
        if (participant == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors.put("participant", "Participant introuvable!"));
        }
        if (participant.getModeParticipation() == FormatEvent.DISTANCIEL) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors.put("participant", "Ce participant ne peut payer!"));
        }
        if (!participant.getPaiementRepas().isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors.put("participant", "Ce participant a déjà payé!"));
        }
        paiementRepas.setDevise(devise);
        paiementRepas.setStatut(statut);
        paiementRepas.setParticipant(participant);
        PaiementRepas createPaiementRepas = paiementRepasService.createPaiementRepas(paiementRepas);
        return ResponseEntity.status(HttpStatus.CREATED).body(createPaiementRepas);
    }


    @PutMapping("/{paiementRepasId}")
//    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<?> updatePaiementRepas(@PathVariable Long paiementRepasId, @RequestBody PaiementRepasRequest paiementRepasRequest) {
        PaiementRepas paiementRepas = paiementRepasService.getPaiementRepasById(paiementRepasId);
        Devise devise = deviseServiceImplement.getDeviseById(paiementRepasRequest.getId_devise());
        Statut statut = statutSeviceImplement.getStatutById(paiementRepasRequest.getId_statut());
        Participant participant = participantServiceImplement.getParticipantById(paiementRepasRequest.getId_participant());

        paiementRepas.setMontant_du(paiementRepasRequest.getMontant_du());
        paiementRepas.setMontant_paye(paiementRepasRequest.getMontant_paye());
        switch (paiementRepasRequest.getMode_paiement()) {
            case "DIFFERE":
                paiementRepas.setMode_paiement(ModePaiement.DIFFERE);
                break;
            case "IMMEDIAT":
                paiementRepas.setMode_paiement(ModePaiement.IMMEDIAT);
                break;
        }
        Map<String, String> errors = new HashMap<>();
        if (paiementRepasService.getPaiementRepasById(paiementRepasId) == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Paiement introuvable");
        }
        if (devise == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors.put("devise", "Devise introuvable!"));
        }
        if (statut == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors.put("statut", "Statut introuvable!"));
        }
        if (participant == null) {
            errors.put("participant", "Participant introuvable!");
            System.out.println(errors.get("participant"));
            return ResponseEntity.badRequest().body(errors);
        }

        paiementRepas.setDevise(devise);
        paiementRepas.setStatut(statut);
        paiementRepas.setParticipant(participant);
        PaiementRepas updatePaiementRepas = paiementRepasService.updatePaiementRepas(paiementRepasId, paiementRepas);
        return ResponseEntity.ok(updatePaiementRepas);
    }

    @DeleteMapping("/{paiementRepasId}")
//    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<?> deletePaiementRepas(@PathVariable Long paiementRepasId) {
        paiementRepasService.deletePaiementRepas(paiementRepasId);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{idParticipant}")
    public ResponseEntity<?> registerPayment(@PathVariable Long idParticipant, @RequestBody PaiementPaypalRequest paiementPaypalRequest) {
        Participant participant = participantServiceImplement.getParticipantById(idParticipant);
//        System.out.println(paiementPaypalRequest.getMontant_paye());
        PaiementRepas paiementRepas = new PaiementRepas();
        Devise devise = deviseServiceImplement.getDeviseById(participant.getIdDevise());
        paiementRepas.setParticipant(participant);
        paiementRepas.setDevise(devise);
//        System.out.println(devise.getDevise());
        paiementRepas.setPayeur(paiementPaypalRequest.getPayeur());
        paiementRepas.setEmail_payeur(paiementPaypalRequest.getEmail_payeur());
        paiementRepas.setMode_paiement(ModePaiement.IMMEDIAT);
        paiementRepas.setMontant_du(participant.getLocal_participant().getMontant_participation());
        paiementRepas.setMontant_paye(paiementPaypalRequest.getMontant_paye());
        paiementRepas.setDate_dernier_paiement(new Date());
//        paiementRepas.setStatut(null);
        participant.setDatePaiement(new Date());
        participant.setStatut_participant(true);
        participant.setPayeur(paiementPaypalRequest.getPayeur());
        participant.setEmail_payeur(paiementPaypalRequest.getEmail_payeur());
        participant.setModePiement("PAYPAL");
        participant.setStatut_payment(true);

        paiementRepas.setReste_a_payer(participant.getLocal_participant().getMontant_participation() - paiementPaypalRequest.getMontant_paye());
//        //List<Participant> participants = participantServiceImplement.findByInscritPar(participant.getUsername());
//        paiementRepasService.createPaiementRepas(paiementRepas);
        return ResponseEntity.ok(participantServiceImplement.updateParticipant(idParticipant, participant));
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

}
