package com.project.qrclub.services;

import com.project.qrclub.dto.CreateQrResponseDto;
import com.project.qrclub.entity.User;
import com.project.qrclub.entity.UsersUUID;
import com.project.qrclub.exceptions.UuidNotFoundException;
import com.project.qrclub.repository.UserRepository;
import com.project.qrclub.repository.UserUUIDRepository;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UUIDCRUDService {
    private final UserUUIDRepository userUuidRepository;
    private final UserRepository userRepository;


    public UUIDCRUDService(UserUUIDRepository userUuidRepository, UserRepository userRepository) {
        this.userUuidRepository = userUuidRepository;
        this.userRepository = userRepository;
    }

    public CreateQrResponseDto createUuid(User user){
        UUID uuid = UUID.randomUUID();
        UsersUUID usersUUID = new UsersUUID();
        usersUUID.setUuid(uuid);
        user.getUsersUUIDList().add(usersUUID);
        usersUUID.setUser(user);
        userRepository.save(user);
        return new CreateQrResponseDto(
                uuid,
                user.getId()
        );
    }

    public void deleteQrById(Long id){
         UsersUUID usersUUID = userUuidRepository.findById(id).orElseThrow(
                 ()->new UuidNotFoundException("Qr код не найден")
         );

         var user = usersUUID.getUser();

         List<UsersUUID> listOfUsersUuid = user.getUsersUUIDList();
         listOfUsersUuid.remove(usersUUID);

         userUuidRepository.delete(usersUUID);

    }


}
