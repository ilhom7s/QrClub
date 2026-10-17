package com.project.qrclub.services;



import com.project.qrclub.Mapper.UserMapper;
import com.project.qrclub.dto.CreateUserRequestDto;
import com.project.qrclub.dto.CreateUserResponseDto;
import com.project.qrclub.dto.UpdateUserRequestDto;
import com.project.qrclub.dto.UserDto;
import com.project.qrclub.entity.User;
import com.project.qrclub.entity.UsersUUID;
import com.project.qrclub.exceptions.UserAlreadryInBaseException;
import com.project.qrclub.exceptions.UserNotFoundException;
import com.project.qrclub.exceptions.UuidNotFoundException;
import com.project.qrclub.repository.UserRepository;
import com.project.qrclub.repository.UserUUIDRepository;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.UUID;

@Service
public class UserService {

    private final UserUUIDRepository userUUIDRepository;
    private final UUIDCRUDService uuidCrudService;
    private final UserMapper userMapper;
    private final UserRepository userRepository;

    public UserService(UserUUIDRepository userUUIDRepository, UUIDCRUDService uuidcrudService, UserMapper userMapper, UserRepository userRepository) {
        this.userUUIDRepository = userUUIDRepository;
        this.uuidCrudService = uuidcrudService;
        this.userMapper = userMapper;
        this.userRepository = userRepository;
    }
    @Transactional
    public UserDto entryToClub(UUID uuid){
        UsersUUID existing = userUUIDRepository.
                findByUuid(uuid).orElseThrow(
                        ()-> new UuidNotFoundException(String.format("Такой UUID не найден в базе данных %s",uuid)));
        UUID newUuid = UUID.randomUUID();
        existing.setUuid(newUuid);
        userUUIDRepository.save(existing);
        User user = existing.getUser();
        UserDto result = userMapper.toUserDto(user);
        return result;
    }
    @Transactional
    public CreateUserResponseDto createNewUser(CreateUserRequestDto createUserRequestDto){
        if (userRepository.findByEmail(createUserRequestDto.email()).isPresent()) {
            throw new UserAlreadryInBaseException(
                    "Пользователь с таким email уже существует в базе данных"
            );
        }

        User user = new User();
        user.setName(createUserRequestDto.name());
        user.setLastName(createUserRequestDto.lastName());
        user.setEmail(createUserRequestDto.email());

        UsersUUID usersUUID = new UsersUUID();
        UUID newUsersUUID = UUID.randomUUID();
        usersUUID.setUuid(newUsersUUID);

        user.getUsersUUIDList().add(usersUUID);
        usersUUID.setUser(user);

        userRepository.save(user);

        return new CreateUserResponseDto(
                user.getName(),
                user.getLastName(),
                user.getEmail(),
                usersUUID.getUuid()
        );
    }

    public void deleteUserFromDB(String email){
       var userFound = find(email);
        userRepository.delete(userFound);

    }

    public User find(String email){
        var user =  userRepository.findByEmail(email).orElseThrow(
                ()->new UserNotFoundException("Пользователь с таким email не существует")
        );
        return user;
    }
    @Transactional
    public UserDto updateUser(String email,UpdateUserRequestDto updateUserRequest){
        var user = find(email);
        user.setEmail(updateUserRequest.newEmail());
        user.setName(updateUserRequest.newName());
        user.setLastName(updateUserRequest.newLastName());
        userRepository.save(user);
        return userMapper.toUserDto(user);
    }

}
