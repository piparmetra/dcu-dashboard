package com.GroupE.Assignment4.implementation;


import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.GroupE.Assignment4.dto.RoomDto;
import com.GroupE.Assignment4.mapper.RoomMapper;
import com.GroupE.Assignment4.model.Room;
import com.GroupE.Assignment4.repository.RoomRepository;
import com.GroupE.Assignment4.service.RoomService;


@Service
public class RoomServiceImpl implements RoomService{

	private RoomRepository roomRepository;
	
	public RoomServiceImpl(RoomRepository roomRepository) {
		this.roomRepository = roomRepository;
	}


	@Override
	public RoomDto getRoomById(int room_id) {
		Room room = roomRepository.findById(room_id);
		return RoomMapper.mapToRoomDto(room);
	}
	
	@Override
	public List<RoomDto> getAllRoom() {
		
		List<Room> rooms = roomRepository.findAll();
        return rooms.stream()
                           .map(RoomMapper::mapToRoomDto)
                           .collect(Collectors.toList());
	}


	@Override
	public RoomDto createRoom(RoomDto roomDto) {
		Room room = RoomMapper.mapToRoom(roomDto);
		Room savedRoom = roomRepository.save(room);
		return RoomMapper.mapToRoomDto(savedRoom);
	}


	@Override
	public void deleteRoom(int room_id) {
		
		Room room = roomRepository.findById(room_id);
		roomRepository.delete(room);
	}
	
	@Override
	public RoomDto editRoom(RoomDto roomDto) {
		Room room = RoomMapper.mapToRoom(roomDto);
		Room savedRoom = roomRepository.save(room);
		return RoomMapper.mapToRoomDto(savedRoom);
	}

}
