package fr.lequipedechoc.hackathon_api.dto;

public record CommentResponseDto(
        Integer id,
        String message,
        Integer UserId
) {}
