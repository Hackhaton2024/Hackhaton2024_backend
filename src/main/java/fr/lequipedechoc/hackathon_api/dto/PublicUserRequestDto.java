package fr.lequipedechoc.hackathon_api.dto;

public record PublicUserRequestDto(
        String pseudo,
        String username,
        String avatar,
        boolean actif,
        String role) {
}