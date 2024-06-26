package com.GroupE.Assignment4.service;

import java.util.List;

import com.GroupE.Assignment4.dto.UserDto;
import com.GroupE.Assignment4.model.Role;
import com.GroupE.Assignment4.model.User;

public interface UserService {
	
	List<UserDto> getUser();
	
	Role FindUserRole(String username);
	
	UserDto FindUserDetails(String username);

	UserDto getUserById(Integer id);


	String deletUser(String username);
	String deleteUser(Integer id);
	
	UserDto changePassword(String username, String password);
	
	UserDto DisableUser(String username);

	UserDto updateUser(Integer id, UserDto userDto);
}
