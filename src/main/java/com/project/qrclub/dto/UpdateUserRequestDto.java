package com.project.qrclub.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
public record UpdateUserRequestDto(
        @NotBlank(message = "Имя обязательно!")
        @Pattern(
                regexp = "^[А-Яа-яЁёA-Za-z\\-\\s]+$",
                message = "Имя может содержать только буквы"
        )
        String newName,
        @NotBlank(message = "Фамилия обязательно!")
        @Pattern(
                regexp = "^[А-Яа-яЁёA-Za-z\\-\\s]+$",
                message = "Фамилия может содержать только буквы"
        )
        String newLastName,
        @NotBlank
        String newEmail
) {
}
