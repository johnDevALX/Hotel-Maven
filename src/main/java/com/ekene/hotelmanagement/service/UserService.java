package com.ekene.hotelmanagement.service;

import com.ekene.hotelmanagement.payload.AuthenticateRequest;
import com.ekene.hotelmanagement.payload.RoomDto;
import com.ekene.hotelmanagement.payload.RoomTypeDto;
import com.ekene.hotelmanagement.payload.UserDto;
import com.ekene.hotelmanagement.response.RoomResponseVO;
import com.ekene.hotelmanagement.response.RoomTypeResponseVO;
import com.ekene.hotelmanagement.response.UserResponseVO;
import org.springframework.web.multipart.MultipartFile;

public interface UserService {
    UserResponseVO createUser(UserDto userDto);
    UserResponseVO authenticateUser(AuthenticateRequest authenticateRequest);
    String deleteUser(Long id);
    RoomTypeResponseVO addRoomType(  MultipartFile image, RoomTypeDto roomTypeDto);
    RoomTypeResponseVO updateRoomType(Long id, MultipartFile image, RoomTypeDto roomTypeDto);
    String deleteRoomType(Long id);
    RoomResponseVO addRoom(MultipartFile image, RoomDto roomDto);
    RoomResponseVO updateRoom (Long id, MultipartFile image, RoomDto roomDto);
    String deleteRoom(Long id);

}
