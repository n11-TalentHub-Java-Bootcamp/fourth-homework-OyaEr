package com.oyaerdayi.converter;

import com.oyaerdayi.dto.UserDto;
import com.oyaerdayi.entity.User;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserConverter {

    UserConverter INSTANCE = Mappers.getMapper(UserConverter.class);

    List<UserDto> convertAllUserListToUserDtoList (List<User> userList);

    User convertAllUserDtoListToUserList(UserDto userDto);

    @AfterMapping
    default void setNulls(@MappingTarget User user, UserDto userDto){
        if (userDto.getId() == null){
            user.setId(null);
        }
    }

}
