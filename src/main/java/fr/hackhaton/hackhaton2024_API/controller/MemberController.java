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
            MemberResponseDto memberToReturn = memberService.createMember(memberRequestDto);

            return ResponseEntity.status(HttpStatus.CREATED).body(memberToReturn);

        } catch (Exception e) {
            logger.error("Error occurred while creating member", e);
            throw e;
        }

    }
}
