package org.bambacompany.springboot.mapper;

import org.bambacompany.springboot.dto.UserDto;
import org.mapstruct.Mapper;
import org.bambacompany.springboot.entity.User;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AutoUserMapper {

    AutoUserMapper MAPPER = Mappers.getMapper(AutoUserMapper.class);

   // @Mapping(source = "email", target = "emailAddress")
    UserDto mapToUserDto(User user);

    User mapToUser(UserDto userDto);


}
