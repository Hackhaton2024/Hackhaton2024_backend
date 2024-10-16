package fr.lequipedechoc.hackathon_api.dto;

import fr.lequipedechoc.hackathon_api.entity.Role;

public record PublicUserResponseDto(
        String username,
        String pseudonyme,
        String avatar,
        boolean actif,
        Role role) {}
