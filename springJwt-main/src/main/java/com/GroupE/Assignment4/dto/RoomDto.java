package com.GroupE.Assignment4.dto;


public class RoomDto {
	private int room_id;
	private String username;
	private float temp;
	private float humidity;
	private float occupancy;
	private float size;
	private String room_name;

	public int getRoom_id() {
		return room_id;
	}

	public void setRoom_id(int room_id) {
		this.room_id = room_id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public float getTemp() {
		return temp;
	}

	public void setTemp(float temp) {
		this.temp = temp;
	}

	public float getHumidity() {
		return humidity;
	}

	public void setHumidity(float humidity) {
		this.humidity = humidity;
	}

	public float getOccupancy() {
		return occupancy;
	}

	public void setOccupancy(float occupancy) {
		this.occupancy = occupancy;
	}

	public float getSize() {
		return size;
	}

	public void setSize(float size) {
		this.size = size;
	}

	public String getRoom_name() {
		return room_name;
	}

	public void setRoom_name(String room_name) {
		this.room_name = room_name;
	}

	public RoomDto() {
		
	}

}