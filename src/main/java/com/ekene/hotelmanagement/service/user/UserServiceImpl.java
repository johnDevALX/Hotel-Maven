package com.ekene.hotelmanagement.service.user;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.ekene.hotelmanagement.config.security.jwt.JwtUtil;
import com.ekene.hotelmanagement.enums.Role;
import com.ekene.hotelmanagement.model.*;
import com.ekene.hotelmanagement.model.account.HotelAccount;
import com.ekene.hotelmanagement.model.hotel.Room;
import com.ekene.hotelmanagement.model.hotel.RoomType;
import com.ekene.hotelmanagement.payload.*;
import com.ekene.hotelmanagement.repository.AccountRepository;
import com.ekene.hotelmanagement.repository.RoomRepository;
import com.ekene.hotelmanagement.repository.RoomTypeRepository;
import com.ekene.hotelmanagement.repository.UserRepository;
import com.ekene.hotelmanagement.response.RoomResponseVO;
import com.ekene.hotelmanagement.response.RoomTypeResponseVO;
import com.ekene.hotelmanagement.response.UserResponseVO;
import com.ekene.hotelmanagement.service.email.EmailServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
public class UserServiceImpl implements UserService {
    private final Cloudinary cloudinary;
    private final UserRepository userRepository;
    private final RoomTypeRepository roomTypeRepository;
    private final RoomRepository roomRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;
    private final EmailServiceImpl emailService;
    private final AccountRepository accountRepository;


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
                .nationality(userDto.getNationality())
                .password(passwordEncoder.encode(userDto.getPassword()))
//                .password((userDto.getPassword()))
                .address(buildAddress(userDto))
                .build();
        userRepository.save(user);

        String subject = "Hotel-Maven Registration Confirmation";
        String text = ("Welcome to Hotel-Maven " + user.getFirstName() +
                " /n Please proceed in making a reservation. Thank You!");
        Email email = Email.builder()
                .to(user.getEmail())
                .subject(subject)
                .text(text)
                .build();
        emailService.sendEmail(email);
        return mapToUserResponse(user);
    }

    @Override
    public UserResponseVO authenticateUser(AuthenticateRequest authenticateRequest) {
        log.info("AUTH REQUEST {}", authenticateRequest);
        System.out.println("AUTH REQUEST  (from service) " + authenticateRequest);
        Authentication authentication;
        authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authenticateRequest.getEmail(),
                        authenticateRequest.getPassword()
                )
        );
        log.info("AUTH REQUEST {}", authenticateRequest);
        System.out.println("AUTH OBJECT" + authentication);
        var user = userRepository.findByEmailIgnoreCase(authenticateRequest.getEmail())
                .orElseThrow();
        SecurityContextHolder.getContext().setAuthentication(authentication);
        log.info("USER {}", authenticateRequest.getEmail());
        System.out.println("AUTH USER    " + user);

        return mapToAuthUserResponse(user);
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
    public RoomTypeResponseVO addRoomType( RoomTypeDto roomTypeDto) {
        RoomType roomType = RoomType.builder()
//                .image(upload(image))
                .title(roomTypeDto.getTitle())
                .build();

        roomTypeRepository.save(roomType);
        return mapToRoomTypeResponse(roomType);
    }

    @Override
    public RoomTypeResponseVO updateRoomType(Long id, MultipartFile image, RoomTypeDto roomTypeDto) {
        RoomType roomType1 = roomTypeRepository.findById(id).get();
//        roomType1.setImage(upload(image));
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
    public RoomResponseVO addRoom(RoomDto roomDto) {
//        MultipartFile image,
        Room room = Room.builder()
                .title(roomDto.getTitle())
//                .image(upload(image))
                .roomType(roomDto.getRoomType())
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
        room.setTitle(roomDto.getTitle());
        room.setImage(upload(image));
        room.setRoomType(roomDto.getRoomType());
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
       return UserResponseVO.builder()
                .name(user.getFirstName())
                .email(user.getEmail())
                .role(user.getRole())
                .build();
    }
    private UserResponseVO mapToAuthUserResponse(Users user){
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
    @Scheduled(cron = "0 0 */24 * * *")
    public void updateStaffWages(){
        HotelAccount hotelAccount = accountRepository.findById(1L).get();
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
            double totalSalary = Role.ADMIN.getSalary() + Role.BARMAN.getSalary() +
                    Role.CHEF.getSalary() +  Role.CLEANER.getSalary()
                    + Role.SPA_MANAGER.getSalary();

            hotelAccount.setHotelAccountBal(hotelAccount.getHotelAccountBal() - totalSalary);
            accountRepository.save(hotelAccount);
            userRepository.save(user);
        }
        System.out.println("All Salaries Paid =====> " + LocalDateTime.now());
        userRepository.saveAll(allUser);
    }
}
