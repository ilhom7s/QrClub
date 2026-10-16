package com.project.qrclub.dto;

import jakarta.validation.constraints.NotBlank;

public record CreateQrRequestDto(
        @NotBlank(message = "Email не может быть пустым")
        String email
) {
}
