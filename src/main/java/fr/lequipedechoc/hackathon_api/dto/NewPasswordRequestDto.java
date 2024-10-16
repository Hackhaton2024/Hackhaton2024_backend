package fr.lequipedechoc.hackathon_api.dto;

public record NewPasswordRequestDto(
        String email,
        String code,
        String password
) {}




