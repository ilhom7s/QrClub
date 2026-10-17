package com.project.qrclub.services;

import com.project.qrclub.dto.CreateQrResponseDto;
import com.project.qrclub.entity.User;
import com.project.qrclub.entity.UsersUUID;
import com.project.qrclub.exceptions.UuidNotFoundException;
import com.project.qrclub.repository.UserRepository;
import com.project.qrclub.repository.UserUUIDRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

import java.util.UUID;

@Service
public class UUIDCRUDService {
    private final UserUUIDRepository userUuidRepository;
    private final UserRepository userRepository;



    public UUIDCRUDService(UserUUIDRepository userUuidRepository, UserRepository userRepository) {
        this.userUuidRepository = userUuidRepository;
        this.userRepository = userRepository;

    }
    @Transactional
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
    @Transactional
    public void deleteQrById(Long id){
         UsersUUID usersUUID = findUuidById(id);

         var user = usersUUID.getUser();

         List<UsersUUID> listOfUsersUuid = user.getUsersUUIDList();
         listOfUsersUuid.remove(usersUUID);

         userUuidRepository.delete(usersUUID);

    }
    @Transactional
    public void updateUuid(Long id){
        var userUuid = findUuidById(id);
        UUID newUUID = UUID.randomUUID();
        userUuid.setUuid(newUUID);
        userUuidRepository.save(userUuid);
    }

    public UsersUUID findUuidById(Long id){
        var found = userUuidRepository.findById(id).orElseThrow(
                ()->new UuidNotFoundException("Qr код не найден")
        );
        return found;
    }

}
