package com.project.qrclub.controllers;

import com.project.qrclub.dto.UserDto;
import com.project.qrclub.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/")
public class EntryController {
    private final UserService userService;
    public EntryController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<UserDto> entryToClub(@PathVariable UUID uuid){
        UserDto result = userService.entryToClub(uuid);
        return ResponseEntity
                .ok(result);
    }


}
