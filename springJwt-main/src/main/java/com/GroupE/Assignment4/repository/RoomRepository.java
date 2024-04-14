package com.GroupE.Assignment4.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.GroupE.Assignment4.model.Room;

public interface RoomRepository extends JpaRepository<Room, Integer>{

	Room findById(int room_id);
}
