package com.project.qrclub.dto;

import java.util.UUID;

public record CreateQrResponseDto(
        UUID uuid,
        Long userId
) {
}
