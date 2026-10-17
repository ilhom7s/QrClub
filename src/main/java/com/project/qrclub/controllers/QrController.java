package com.project.qrclub.controllers;

import com.project.qrclub.dto.CreateQrRequestDto;
import com.project.qrclub.dto.CreateQrResponseDto;
import com.project.qrclub.entity.User;
import com.project.qrclub.services.UUIDCRUDService;
import com.project.qrclub.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/club/qr")
@RequiredArgsConstructor
public class QrController {
    private final UserService userService;
    private final UUIDCRUDService uuidService;



    @PostMapping
    public ResponseEntity<CreateQrResponseDto> createQr(@RequestBody CreateQrRequestDto createQrRequestDto) {

        User user = userService.find(createQrRequestDto.email());
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(uuidService.createUuid(user));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteQrById(@PathVariable Long id) {
        uuidService.deleteQrById(id);

        return ResponseEntity
                .status(HttpStatus.ACCEPTED)
                .body("Qr code успешно удален");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateUUID(@PathVariable Long id){
        uuidService.updateUuid(id);
        return ResponseEntity
                .ok("Qr code Успешно обновился");
    }

}