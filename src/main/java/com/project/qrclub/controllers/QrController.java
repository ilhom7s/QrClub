package com.project.qrclub.controllers;

import com.project.qrclub.dto.CreateQrRequestDto;
import com.project.qrclub.dto.CreateQrResponseDto;
import com.project.qrclub.services.UUIDCRUDService;
import com.project.qrclub.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/qr")
public class QrController {
    private final UserService userService;
    private final UUIDCRUDService uuidService;


    public QrController(UserService userService, UUIDCRUDService uuidService) {
        this.userService = userService;
        this.uuidService = uuidService;
    }

    @PostMapping
    public ResponseEntity<CreateQrResponseDto> createQr(@RequestBody CreateQrRequestDto createQrRequestDto){

       var found =  userService.findUser(createQrRequestDto.email());
       var response = uuidService.createUuid(found);
       return ResponseEntity
               .status(HttpStatus.CREATED)
               .body(response);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteQrById(@PathVariable Long id){

    }
}
