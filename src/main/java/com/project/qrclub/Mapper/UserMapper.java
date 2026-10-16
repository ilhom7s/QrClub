package com.project.qrclub.Mapper;

import com.project.qrclub.dto.UserDto;
import com.project.qrclub.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;


@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE,
        componentModel = MappingConstants.ComponentModel.SPRING, uses = {UserMapper.class})
public interface UserMapper {

    UserDto toUserDto(User user);
}
