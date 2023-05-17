package com.ekene.hotelmanagement.service;

import com.ekene.hotelmanagement.payload.RoomDto;
import com.ekene.hotelmanagement.payload.RoomTypeDto;
import com.ekene.hotelmanagement.payload.UserDto;
import com.ekene.hotelmanagement.response.RoomResponseVO;
import com.ekene.hotelmanagement.response.RoomTypeResponseVO;
import com.ekene.hotelmanagement.response.UserResponseVO;

public interface UserService {
    UserResponseVO createUser(UserDto userDto);
    String deleteUser(Long id);
    RoomTypeResponseVO addRoomType(RoomTypeDto roomTypeDto);
    RoomTypeResponseVO updateRoomType(Long id, RoomTypeDto roomTypeDto);
    String deleteRoomType(Long id);
    RoomResponseVO addRoom(RoomDto roomDto);
    RoomResponseVO updateRoom (Long id, RoomDto roomDto);
    String deleteRoom(Long id);

}
