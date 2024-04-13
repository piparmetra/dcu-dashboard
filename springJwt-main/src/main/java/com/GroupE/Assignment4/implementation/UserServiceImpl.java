package com.GroupE.Assignment4.implementation;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import com.GroupE.Assignment4.dto.UserDto;
import com.GroupE.Assignment4.mapper.UserMapper;
import com.GroupE.Assignment4.model.Role;
import com.GroupE.Assignment4.model.Token;
import com.GroupE.Assignment4.model.User;
import com.GroupE.Assignment4.repository.TokenRepository;
import com.GroupE.Assignment4.repository.UserRepository;
import com.GroupE.Assignment4.service.UserService;

import jakarta.transaction.Transactional;

@Service
public class UserServiceImpl implements UserService {

	private UserRepository userRepository;
	private TokenRepository tokenRepository;

	@Autowired
	public UserServiceImpl(UserRepository userRepository, TokenRepository tokenRepository) {
		this.userRepository = userRepository;
		this.tokenRepository = tokenRepository;
	}
	
	
	@Override
	public List<UserDto> getUser() {
		List<User> users = userRepository.findAll();
        return users.stream()
                           .map(UserMapper::mapToUserDto)
                           .collect(Collectors.toList());

	}

	@Override
	public Role FindUserRole(String username) {
		
		User user = userRepository.findByUsername(username).orElseThrow(()-> new RuntimeException("User not found"));
		
		Role role = user.getRole();
		
		return role;
	}
	
	@Override
	public UserDto FindUserDetails(String username) {
		
		UserMapper userMapper = new UserMapper();
		
		User user = userRepository.findByUsername(username).orElseThrow(()-> new RuntimeException("User not found"));
		
		UserDto userDto = UserMapper.mapToUserDto(user);
		
		return userDto;
	}

	@Override
	@Transactional
	public String deletUser(String username) {
		
		User user = userRepository.findByUsername(username).orElseThrow(()-> new RuntimeException("User not found"));
		
		//List<Token> token = tokenRepository.findAllTokensByUser(user.getId());
		
		//List<Calculation> calculations = calcRepository.findCalcbyUsername(username);
		
		tokenRepository.deleteById(user.getId());;
		
		userRepository.deleteById(user.getId());
		
		return "User -> "+ username +" is deleted";
	}


	@Override
	@Transactional
	public String deleteUser(Integer id) {
		User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
		List<Token> tokens = tokenRepository.findAllTokensByUser(user.getId());
		for (Token token : tokens) {
			tokenRepository.delete(token);
		}
		tokenRepository.deleteById(user.getId());
		userRepository.deleteById(user.getId());
		return "User -> " + user.getUsername() + " is deleted";
	}



	@Override
	public UserDto changePassword(String username, String newPassword) {
		
		User user = userRepository.findByUsername(username).orElseThrow(()-> new RuntimeException("User not found"));
		
		String hashedPassword = BCrypt.hashpw(newPassword, BCrypt.gensalt()); //need to encrypt the password for further login
		
		user.setPassword(hashedPassword);
		
		User savedUser = userRepository.save(user);
	
		return UserMapper.mapToUserDto(savedUser);
	}


	@Override
	public UserDto DisableUser(String username) {
		
		User user = userRepository.findByUsername(username).orElseThrow(()-> new RuntimeException("User not found"));
		
		user.setUsername(null);
		
		User savedUser =  userRepository.save(user);
		
		return UserMapper.mapToUserDto(savedUser);
	}
	

	@Override
	public UserDto getUserById(Integer id) {
		User user = userRepository.findById(id).orElseThrow(()-> new RuntimeException("User not found"));
		return UserMapper.mapToUserDto(user);
	}

	@Override
	public UserDto updateUser(Integer id, UserDto userDto) {
		User user = userRepository.findById(id).orElseThrow(()-> new RuntimeException("User not found"));
		user.setFirstName(userDto.getFirstName());
		user.setLastName(userDto.getLastName());
		user.setEmail(userDto.getEmail());
		user.setRole(userDto.getRole());
		User savedUser = userRepository.save(user);
		return UserMapper.mapToUserDto(savedUser);
	}
}
