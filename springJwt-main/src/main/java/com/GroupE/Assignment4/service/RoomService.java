package com.GroupE.Assignment4.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.GroupE.Assignment4.dto.RoomDto; 

@Service
public interface RoomService {
	
	RoomDto createRoom (RoomDto roomDto);

	RoomDto getRoomById(int room_id);
	
	List<RoomDto> getAllRoom();
	
	void deleteRoom(int room_id);

}
