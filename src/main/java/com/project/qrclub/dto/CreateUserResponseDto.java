package com.project.qrclub.dto;

import java.util.UUID;

public record CreateUserResponseDto(
        String name,
        String lastName,
        String email,
        UUID uuid

)
{
}
