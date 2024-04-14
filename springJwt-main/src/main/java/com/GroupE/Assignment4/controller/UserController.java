package com.GroupE.Assignment4.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.GroupE.Assignment4.model.Room;
import com.GroupE.Assignment4.service.RoomService;
import com.GroupE.Assignment4.service.UserService;
import com.GroupE.Assignment4.dto.UserDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import com.GroupE.Assignment4.dto.RoomDto;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;



@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/admin/users")
    public String getAllUsers(Model model) {
        List<UserDto> users = userService.getUser();
        model.addAttribute("users", users);
        return "users.html";
    }

    @PostMapping("/admin/editUser/{id}")
    public String editUser(@PathVariable Integer id, @ModelAttribute UserDto userDto) {
        userService.updateUser(id, userDto);
        return "redirect:/admin/users";
    }

    @PostMapping("/admin/deleteUser/{id}")
    public String deleteUser(@PathVariable Integer id) {
        System.out.println("Deleting user");
        UserDto userDto = userService.getUserById(id);
        System.out.println("User to delete: " + userDto.getUsername());
        String output = userService.deleteUser(id);
        System.out.println(output);
        System.out.println("User deleted");
        return "redirect:/admin/users";
    }


}
