package com.project.qrclub.controllers;

import com.project.qrclub.dto.UserDto;
import com.project.qrclub.services.UserService;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.UUID;

@RestController
@RequestMapping("/api/club/entry")
@RequiredArgsConstructor
public class EntryController {
    private final UserService userService;

    @PostMapping("/{uuid}")
    public ResponseEntity<UserDto> entryToClub(@PathVariable UUID uuid){
        return ResponseEntity
                .ok(userService.entryToClub(uuid));
    }


}
