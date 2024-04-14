package com.GroupE.Assignment4.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.GroupE.Assignment4.dto.RoomDto;
import com.GroupE.Assignment4.dto.UserDto;
import com.GroupE.Assignment4.model.Role;
import com.GroupE.Assignment4.model.User;
import com.GroupE.Assignment4.service.RoomService;
import com.GroupE.Assignment4.service.UserService;

@RestController
@CrossOrigin(origins = "*")
public class DemoController {

    @GetMapping("/demo")
    public ResponseEntity<String> demo() {
        return ResponseEntity.ok("Hello from secured url");
    }

    @GetMapping("/admin_only")
    public ResponseEntity<String> adminOnly() {
        return ResponseEntity.ok("Hello from admin only url");
    }
    
    
    /*--------------------User-------------------*/
    
    private UserService userService;
    
   @Autowired
   public void UserController(UserService userService) {
   	this.userService = userService;
   }   
    // -> Get all users
    @GetMapping("/getUser")
    public ResponseEntity<List<UserDto>> getAllUser() {
    	
        List<UserDto> users = userService.getUser();
        
        return new ResponseEntity<>(users, HttpStatus.OK);
    }
    
    // -> Get all specified user's role
    @GetMapping("/getRole/{username}")
    public ResponseEntity<Role> FindRole(@PathVariable String username){
    	
    	Role role = userService.FindUserRole(username);
    	if(role ==  null) {
    		role = role.UNDEFINED;
    	}
		return new ResponseEntity<>(role, HttpStatus.OK);
    }
    
    // -> Get all specified user's details
    @GetMapping("/getUser/details/{username}")
    public ResponseEntity<UserDto> getUser(@PathVariable String username){
    	
    	UserDto user = userService.FindUserDetails(username);
    	user.setPassword(null);
    	
		return new ResponseEntity<>(user, HttpStatus.OK);
    }
    
    
    // -> Delete specified user (not working in database)  ¯_(ツ)_/¯
    @GetMapping("/getUser/delete/{username}")
    public ResponseEntity<String> deleteUser(@PathVariable String username){
    	
    	return new ResponseEntity<>(userService.deletUser(username),HttpStatus.OK);
    }
    
    // -> Modify specified user's password
    @PutMapping("/getUser/c-password/{username}")
    public ResponseEntity<UserDto> ChangePassword(@PathVariable String username,@RequestBody Map<String, String> request){
    	
    	String password = request.get("password");
    	
    	UserDto userDto = userService.changePassword(username, password);
    	
    	return new ResponseEntity<>(userDto,HttpStatus.OK);
    }
    
    /*--------------------ROOM-------------------*/
    
    private RoomService roomService;
    
    @Autowired
    public void RoomController(RoomService roomService) {
    	this.roomService = roomService;
    }  
    
    // -> Get all room
    @GetMapping("/getRoom")
    public ResponseEntity<List<RoomDto>> getAllRoom() {
        System.out.println("Get all room");
    	
        List<RoomDto> rooms = roomService.getAllRoom();
        
        return new ResponseEntity<>(rooms, HttpStatus.OK);
    }
    
    // -> Create new room
    @PostMapping(value = "/addRoom", produces = "application/json")
    public ResponseEntity<RoomDto> addCalculation(@RequestBody RoomDto roomDto){
    	
    	return new ResponseEntity<>(roomService.createRoom(roomDto),HttpStatus.CREATED);
    }
    
    
    @GetMapping("/getRoom/delete/{room_id}")
    public ResponseEntity<String> deleteRoom(@PathVariable int room_id) {
    	
        roomService.deleteRoom(room_id);
        
        return new ResponseEntity<>( "Room : " + room_id + " has been deleted" , HttpStatus.OK);
    }
    
    
}
