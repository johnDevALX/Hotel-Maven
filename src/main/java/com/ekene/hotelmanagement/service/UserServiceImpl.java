package com.ekene.hotelmanagement.service;

import com.ekene.hotelmanagement.enums.Availability;
import com.ekene.hotelmanagement.model.Room;
import com.ekene.hotelmanagement.model.RoomType;
import com.ekene.hotelmanagement.model.User;
import com.ekene.hotelmanagement.repository.RoomRepository;
import com.ekene.hotelmanagement.repository.RoomTypeRepository;
import com.ekene.hotelmanagement.repository.UserRepository;
import com.ekene.hotelmanagement.model.Address;
import com.ekene.hotelmanagement.payload.RoomDto;
import com.ekene.hotelmanagement.payload.RoomTypeDto;
import com.ekene.hotelmanagement.payload.UserDto;
import com.ekene.hotelmanagement.response.RoomResponseVO;
import com.ekene.hotelmanagement.response.RoomTypeResponseVO;
import com.ekene.hotelmanagement.response.UserResponseVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;
    private final RoomTypeRepository roomTypeRepository;
    private final RoomRepository roomRepository;

    @Override
    public UserResponseVO createUser(UserDto userDto) {
        User user = User.builder()
                .firstName(userDto.getFirstName())
                .lastName(userDto.getLastName())
                .email(userDto.getEmail())
                .DOB(userDto.getDOB())
                .gender(userDto.getGender())
                .mobile(userDto.getMobile())
                .role(userDto.getRole())
                .salary(0.0)
                .nationality(userDto.getNationality())
                .password(userDto.getPassword())
                .address(buildAddress(userDto))
                .build();

        userRepository.save(user);
        return mapToUserResponse(user);
    }

    @Override
    public String deleteUser(Long id) {
        String response = "";
       try {
           userRepository.deleteById(id);
           response =  "Successfully deleted";
       }catch (IllegalArgumentException e){
           response = e.getMessage();
           throw new IllegalArgumentException(e.getMessage());
       }
        return response;
    }

    @Override
    public RoomTypeResponseVO addRoomType(RoomTypeDto roomTypeDto) {
        RoomType roomType = RoomType.builder()
                .image(roomTypeDto.getImage())
                .title(roomTypeDto.getTitle())
                .build();

        roomTypeRepository.save(roomType);
        return mapToRoomTypeResponse(roomType);
    }

    @Override
    public RoomTypeResponseVO updateRoomType(Long id, RoomTypeDto roomTypeDto) {
        RoomType roomType1 = roomTypeRepository.findById(id).get();
        roomType1.setImage(roomTypeDto.getImage());
        roomType1.setTitle(roomTypeDto.getTitle());
        roomTypeRepository.save(roomType1);
        return mapToRoomTypeResponse(roomType1);
    }

    @Override
    public String deleteRoomType(Long id) {
        String response = "";
        try {
            roomTypeRepository.deleteById(id);
            response = "Successfully deleted";
        } catch (IllegalArgumentException e){
            response = e.getMessage();
            throw new IllegalArgumentException(e.getMessage());
        }
        return response;
    }

    @Override
    public RoomResponseVO addRoom(RoomDto roomDto) {
        RoomType roomType = roomTypeRepository.findById(roomDto.getRoomTypeDto().getId()).get();
        Room room = Room.builder()
                .title(roomDto.getTitle())
                .image(roomDto.getImage())
                .roomType(roomType)
                .cost(roomDto.getCost())
                .bed(roomDto.getBed())
                .maxOccupants(roomDto.getMaxOccupants())
                .availability(Availability.AVAILABLE)
                .build();

        roomRepository.save(room);
        return mapToRoomResponse(room);
    }

    @Override
    public RoomResponseVO updateRoom(Long id, RoomDto roomDto) {
        Room room = roomRepository.findById(id).get();
        RoomType roomType = roomTypeRepository.findById(roomDto.getRoomTypeDto().getId()).get();
        room.setTitle(roomDto.getTitle());
        room.setImage(roomDto.getImage());
        room.setRoomType(roomType);
        room.setCost(roomDto.getCost());
        room.setBed(roomDto.getBed());
        room.setMaxOccupants(roomDto.getMaxOccupants());

        roomRepository.save(room);
        return mapToRoomResponse(room);
    }

    @Override
    public String deleteRoom(Long id) {
        String response = "";
        try {
            roomRepository.deleteById(id);
            response = "Successfully deleted";
        } catch (IllegalArgumentException e){
            response = e.getMessage();
            throw new IllegalArgumentException(e.getMessage());
        }
        return response;
    }

    private Address buildAddress(UserDto userDto){
        return   Address.builder()
                .street(userDto.getStreet())
                .city(userDto.getCity())
                .state(userDto.getState())
                .postalCode(userDto.getPostalCode())
                .country(userDto.getCountry())
                .build();
    }
    private UserResponseVO mapToUserResponse(User user){
       return UserResponseVO.builder()
                .name(user.getFirstName())
                .email(user.getEmail())
                .role(user.getRole())
                .build();
    }
    private RoomResponseVO mapToRoomResponse(Room room){
        return RoomResponseVO.builder()
                .image(room.getImage())
                .title(room.getTitle())
                .roomType(room.getRoomType())
                .build();
    }
    private RoomTypeResponseVO mapToRoomTypeResponse(RoomType roomType){
        return RoomTypeResponseVO.builder()
                .title(roomType.getTitle())
                .build();
    }
}
