package org.bambacompany.springboot.mapper;

import org.bambacompany.springboot.dto.UserDto;
import org.bambacompany.springboot.entity.User;

import java.util.ArrayList;
import java.util.List;

public class UserMapper {

    public static UserDto mapDtoToUser(User user) {
        return new UserDto(user.getId(), user.getFirstName(), user.getLastName(), user.getEmail());
    }


    public static  User mapUserToUserDto(UserDto userDto) {
        return new User(userDto.getId(), userDto.getFirstName(), userDto.getLastName(), userDto.getEmail());
    }

  /*  public static List<UserDto> mapDtoToUser(List<User> users) {
        List<UserDto> userDtoList = new ArrayList<>();
        for (User user : users) {
            userDtoList.add(new UserDto(user.getId(), user.getFirstName(), user.getLastName(), user.getEmail()));
        }
        return userDtoList;
    }*/
}
