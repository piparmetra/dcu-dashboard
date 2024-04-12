package com.GroupE.Assignment4.mapper;


import com.GroupE.Assignment4.dto.RoomDto;
import com.GroupE.Assignment4.model.Room;

public class RoomMapper {

	public static Room mapToRoom(RoomDto roomDto) {

		Room room = new Room();
		room.setRoom_id(roomDto.getRoom_id());
		room.setUsername(roomDto.getUsername());
		room.setHumidity(roomDto.getHumidity());
		room.setTemp(roomDto.getTemp());
		room.setOccupancy(roomDto.getOccupancy());
		room.setSize(roomDto.getSize());
		room.setRoom_name(roomDto.getRoom_name());
		
		return room;

	}
	
	public static RoomDto mapToRoomDto(Room room) {

		RoomDto roomDto = new RoomDto();
		roomDto.setRoom_id(room.getRoom_id());
		roomDto.setUsername(room.getUsername());
		roomDto.setHumidity(room.getHumidity());
		roomDto.setTemp(room.getTemp());
		roomDto.setOccupancy(room.getOccupancy());
		roomDto.setSize(room.getSize());
		roomDto.setRoom_name(room.getRoom_name());
		
		return roomDto;
	}
}
