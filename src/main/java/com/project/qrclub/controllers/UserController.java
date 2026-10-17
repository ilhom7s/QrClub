package com.project.qrclub.controllers;

import com.project.qrclub.dto.CreateUserRequestDto;
import com.project.qrclub.dto.CreateUserResponseDto;
import com.project.qrclub.dto.UpdateUserRequestDto;
import com.project.qrclub.dto.UserDto;
import com.project.qrclub.services.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("api/club/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/create")
    public ResponseEntity<CreateUserResponseDto> createUser(@Valid @RequestBody CreateUserRequestDto createUserRequestDto){

        var response = userService.createNewUser(createUserRequestDto);

        return ResponseEntity.ok(response);
    }
    @DeleteMapping("/{email}")
    public ResponseEntity<String> deleteUser(@PathVariable String email){
        userService.deleteUserFromDB(email);
        return ResponseEntity
                .status(HttpStatus.ACCEPTED)
                .body("Пользователь успешно удален");
    }
    @PutMapping("/{email}")
    public ResponseEntity<UserDto> updateUser(@PathVariable String email, @RequestBody UpdateUserRequestDto updateRequest){
        var updated = userService.updateUser(email,updateRequest);
        return ResponseEntity
                .ok(updated);
    }


}
