package fr.hackhaton.hackhaton2024_API.controller;

import fr.hackhaton.hackhaton2024_API.dto.MemberRequestDto;
import fr.hackhaton.hackhaton2024_API.dto.MemberResponseDto;
import fr.hackhaton.hackhaton2024_API.entity.Member;
import fr.hackhaton.hackhaton2024_API.service.MemberService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController // Obligatoire pour indiquer à Spring que c'est un controller
public class MemberController {

    private final MemberService memberService;
    private static final Logger logger = LoggerFactory.getLogger(MemberController.class);


    // Injection du service par le constructeur
    public MemberController(MemberService memberService){
        this.memberService = memberService;
    }

    @PostMapping(path="/api/member/create")  
    public ResponseEntity<MemberResponseDto> create(@RequestBody MemberRequestDto memberRequestDto){

        logger.info("Received request to create a new member with first name: " + memberRequestDto.firstName());

        try {
            // Création du membre à partir du dto (Data Transfer Object) reçu en provenance du front
            Member memberToCreate =
                    Member.builder()
                            .firstName(memberRequestDto.firstName())
                            .build();

            // On appelle le service dédié à cette tâche qui renvoie le membre
            // si tout c'est bien passé lors de l'enregistrement en base de données
            memberToCreate = this.memberService.create(memberToCreate);

            // On retransforme le membre en dto (un dto permet de contrôller les infos qui transitent dans la requête, si par exemple un membre possède un mot de passe, on veut pas forcemment renvoyer tout le membre, ce qui permettrait d'accéder à son mot de passe)
            MemberResponseDto memberToReturn =
                    new MemberResponseDto(memberToCreate.getId(), memberToCreate.getFirstName());

            // On renvoie la réponse (code 201), avec le dto du membre crée dans le corps de la requête
            return ResponseEntity.status(HttpStatus.CREATED).body(memberToReturn);


        } catch (Exception e) {
            logger.error("Error occurred while creating member", e);
            throw e;
        }

    }
}
