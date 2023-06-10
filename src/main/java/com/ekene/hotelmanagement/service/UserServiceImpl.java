package com.ekene.hotelmanagement.service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.ekene.hotelmanagement.config.security.jwt.JwtUtil;
import com.ekene.hotelmanagement.enums.Role;
import com.ekene.hotelmanagement.model.*;
import com.ekene.hotelmanagement.payload.AuthenticateRequest;
import com.ekene.hotelmanagement.repository.RoomRepository;
import com.ekene.hotelmanagement.repository.RoomTypeRepository;
import com.ekene.hotelmanagement.repository.UserRepository;
import com.ekene.hotelmanagement.payload.RoomDto;
import com.ekene.hotelmanagement.payload.RoomTypeDto;
import com.ekene.hotelmanagement.payload.UserDto;
import com.ekene.hotelmanagement.response.RoomResponseVO;
import com.ekene.hotelmanagement.response.RoomTypeResponseVO;
import com.ekene.hotelmanagement.response.UserResponseVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Service
@Slf4j
public class UserServiceImpl implements UserService{
    private final Cloudinary cloudinary;
    private final UserRepository userRepository;
    private final RoomTypeRepository roomTypeRepository;
    private final RoomRepository roomRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;


    @Override
    public UserResponseVO createUser(UserDto userDto) {
        Users user = Users.builder()
                .firstName(userDto.getFirstName())
                .lastName(userDto.getLastName())
                .email(userDto.getEmail())
                .DOB(userDto.getDOB())
                .gender(userDto.getGender())
                .mobile(userDto.getMobile())
                .role(userDto.getRole())
                .salary(0.0)
                .nationality(userDto.getNationality())
                .password(passwordEncoder.encode(userDto.getPassword()))
//                .password((userDto.getPassword()))
                .address(buildAddress(userDto))
                .build();
        userRepository.save(user);
        return mapToUserResponse(user);
    }

    @Override
    public UserResponseVO authenticateUser(AuthenticateRequest authenticateRequest) {
        Authentication authentication;
        authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authenticateRequest.getEmail(),
                        authenticateRequest.getPassword()
                )
        );
        var user = userRepository.findByEmailIgnoreCase(authenticateRequest.getEmail())
                .orElseThrow();
        SecurityContextHolder.getContext().setAuthentication(authentication);
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
           throw new IllegalArgumentException(response);
       }
        return response;
    }

    @Override
    public RoomTypeResponseVO addRoomType(MultipartFile image, RoomTypeDto roomTypeDto) {
        RoomType roomType = RoomType.builder()
                .image(upload(image))
                .title(roomTypeDto.getTitle())
                .build();

        roomTypeRepository.save(roomType);
        return mapToRoomTypeResponse(roomType);
    }

    @Override
    public RoomTypeResponseVO updateRoomType(Long id, MultipartFile image, RoomTypeDto roomTypeDto) {
        RoomType roomType1 = roomTypeRepository.findById(id).get();
        roomType1.setImage(upload(image));
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
            throw new IllegalArgumentException(response);
        }
        return response;
    }

    @Override
    public RoomResponseVO addRoom(MultipartFile image, RoomDto roomDto) {
        RoomType roomType = roomTypeRepository.findByTitleIgnoreCase(roomDto.getRoomTypeDtoTitle()).get();
        Room room = Room.builder()
                .title(roomDto.getTitle())
                .image(upload(image))
                .roomType(roomType)
                .cost(roomDto.getCost())
                .bed(roomDto.getBed())
                .maxOccupants(roomDto.getMaxOccupants())
                .isAvailable(true)
                .build();

        roomRepository.save(room);
        return mapToRoomResponse(room);
    }

    @Override
    public RoomResponseVO updateRoom(Long id, MultipartFile image, RoomDto roomDto) {
        Room room = roomRepository.findById(id).get();
        RoomType roomType = roomTypeRepository.findByTitleIgnoreCase(roomDto.getRoomTypeDtoTitle()).get();
        room.setTitle(roomDto.getTitle());
        room.setImage(upload(image));
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
            throw new IllegalArgumentException(response);
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
    private UserResponseVO mapToUserResponse(Users user){
        var jwkToken = jwtUtil.generateToken(user.getEmail());
       return UserResponseVO.builder()
                .name(user.getFirstName())
                .email(user.getEmail())
                .role(user.getRole())
                .token(jwkToken)
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
    private String upload(MultipartFile image){
        try{
            Map<?, ?> uploadResult = cloudinary.uploader().upload(image.getBytes(), ObjectUtils.emptyMap());
            System.out.println("Map of cloudinary result ===========> " + uploadResult);
            return uploadResult.get("secure_url").toString();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @Scheduled(cron = "0 */5 * ? * *")
    public void updateStaffWages(){
        List<Users> allUser = userRepository.findAll();
        for (Users user: allUser) {
            if (user.getRole().equals(Role.ADMIN)){
                user.setSalary(user.getSalary() + Role.ADMIN.getSalary());
                System.out.println(user.getRole() + " salary Paid");
            } else if (user.getRole().equals(Role.BARMAN)) {
                user.setSalary(user.getSalary() + Role.BARMAN.getSalary());
                System.out.println(user.getRole() + " salary Paid");
            } else if (user.getRole().equals(Role.CHEF)) {
                user.setSalary(user.getSalary() + Role.CHEF.getSalary());
                System.out.println(user.getRole() + " salary Paid");
            }else if (user.getRole().equals(Role.CLEANER)) {
                user.setSalary(user.getSalary() + Role.CLEANER.getSalary());
                System.out.println(user.getRole() + " salary Paid");
            }else {
                //user.getRole().equals(Role.SPA_MANAGER)
                user.setSalary(user.getSalary() + Role.SPA_MANAGER.getSalary());
                System.out.println(user.getRole() + " salary Paid");
            }
          //  userRepository.save(user);
        }
        System.out.println("All Salaries Paid =====> " + LocalDateTime.now());
        userRepository.saveAll(allUser);
    }
}
