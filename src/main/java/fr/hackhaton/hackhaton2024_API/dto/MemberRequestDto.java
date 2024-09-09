package fr.hackhaton.hackhaton2024_API.dto;

import jakarta.validation.constraints.NotNull;

public record MemberRequestDto(
        @NotNull
    String firstName

) {
    @Override
    public @NotNull String firstName() {
        return firstName;
    }
}
