package com.GroupE.Assignment4.mapper;

import com.GroupE.Assignment4.dto.UserDto;
import com.GroupE.Assignment4.model.User;

public class UserMapper {
	
	public static User mapToUser(UserDto userDto) {
		
		User user = new User();
		user.setId(userDto.getId());
		user.setUsername(userDto.getUsername());
		user.setEmail(userDto.getEmail());
		user.setFirstName(userDto.getFirstName());
		user.setLastName(userDto.getLastName());
		user.setPassword(userDto.getPassword());
		user.setRole(userDto.getRole());
		user.setRegister_date(userDto.getRegister_date());
		
		return user;
	}
	
	public static UserDto mapToUserDto(User user) {
		
		UserDto userDto = new UserDto();
		userDto.setId(user.getId());
		userDto.setUsername(user.getUsername());
		userDto.setEmail(user.getEmail());
		userDto.setFirstName(user.getFirstName());
		userDto.setLastName(user.getLastName());
		userDto.setPassword(user.getPassword());
		userDto.setRole(user.getRole());
		userDto.setRegister_date(user.getRegister_date());
		
		return userDto;
	}
	

}
