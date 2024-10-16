package fr.lequipedechoc.hackathon_api.dto;

public record SignInResponseDto(
                String bearer,
                String refresh) {}
