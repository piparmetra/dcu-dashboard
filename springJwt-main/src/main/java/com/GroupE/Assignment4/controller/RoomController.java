package com.GroupE.Assignment4.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.GroupE.Assignment4.model.Room;
import com.GroupE.Assignment4.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import com.GroupE.Assignment4.dto.RoomDto;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;



@Controller
public class RoomController {
    @Autowired
    private RoomService roomService;

    @GetMapping("/rooms")
    public String getAllRooms(Model model) {
        System.out.println("PASSED");
        List<RoomDto> rooms = roomService.getAllRoom();
        model.addAttribute("rooms", rooms);
        return "rooms.html";
    }    

    @GetMapping("/rooms/{id}")
    public String getRoomById(Model model, @PathVariable Integer id) {
        if(id==null){
            System.out.println("No id provided");
            return "redirect:/rooms";
        }
        System.out.println(id);
        RoomDto room = roomService.getRoomById(id);
        model.addAttribute("room", room);
        return "room.html";
    }

    @PostMapping("/admin/addRoom")
    public String addRoom(Model model, @ModelAttribute RoomDto roomDto) {
        roomService.createRoom(roomDto);
        return "redirect:/rooms";
    }

    @GetMapping("/admin/editRoom/{id}")
    public String editRoom(Model model, @PathVariable Integer id) {
        if(id==null){
            System.out.println("No id provided");
            return "redirect:/rooms";
        }
        System.out.println(id);
        RoomDto room = roomService.getRoomById(id);
        model.addAttribute("room", room);
        return "editRoom.html";
    }

    @PostMapping("/admin/editRoom/{id}")
    public String editRoom(@PathVariable Integer id, Model model, @ModelAttribute RoomDto roomDto) {
        roomDto.setRoom_id(id);
        roomService.editRoom(roomDto);
        return "redirect:/rooms";
    }
}
