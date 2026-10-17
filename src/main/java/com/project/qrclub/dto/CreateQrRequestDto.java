package com.project.qrclub.dto;

import com.project.qrclub.Constants;
import jakarta.validation.constraints.NotBlank;

public record CreateQrRequestDto(
        @NotBlank(message = Constants.EmailNotBlankMessage)
        String email
) {
}
