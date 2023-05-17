//package com.ekene.hotelmanagement.service;
//
//import com.ekene.hotelmanagement.model.Admin;
//import com.ekene.hotelmanagement.model.RoomType;
//import com.ekene.hotelmanagement.repository.AdminRepository;
//import com.ekene.hotelmanagement.repository.RoomTypeRepository;
//import com.ekene.hotelmanagement.utility.*;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Service;
//
//import java.util.Optional;
//
//@RequiredArgsConstructor
//@Service
//public class AdminServiceImpl implements AdminService{
//    private final AdminRepository adminRepository;
//    private final RoomTypeRepository roomTypeRepository;
//
//
//    @Override
//    public void createAdmin(AdminDto adminDto) {
//        Admin admin = Admin.builder()
//                .firstName(adminDto.getFirstName())
//                .lastName(adminDto.getLastName())
//                .email(adminDto.getEmail())
//                .role(adminDto.getRole())
//                .salary(0.0)
//                .nationality(adminDto.getNationality())
//                .password(adminDto.getPassword())
//                .address(buildAddress(adminDto))
//                .build();
//
//        adminRepository.save(admin);
//    }
//
//    @Override
//    public Admin loginAdmin(LoginDto loginDto) {
//        Optional<Admin> adminByEmail = adminRepository.findAdminByEmail(loginDto.getEmail());
//        Admin admin = null;
//        if (adminByEmail.isPresent()){
//            admin = adminByEmail.get();
//        }
//
//        return admin;
//    }
//
//    @Override
//    public void addRoomType(RoomTypeDto roomTypeDto) {
//        RoomType roomType = RoomType.builder()
//                .image(roomTypeDto.getImage())
//                .title(roomTypeDto.getTitle())
//                .build();
//
//        roomTypeRepository.save(roomType);
//    }
//
//    @Override
//    public void updateRoomType(Long id, RoomType roomType) {
//    }
//
//
//    private Address buildAddress(AdminDto adminDto){
//        return   Address.builder()
//                .street(adminDto.getStreet())
//                .city(adminDto.getCity())
//                .state(adminDto.getState())
//                .postalCode(adminDto.getPostalCode())
//                .country(adminDto.getCountry())
//                .build();
//    }
//}
