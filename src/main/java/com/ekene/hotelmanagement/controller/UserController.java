package com.ekene.hotelmanagement.controller;

import com.ekene.hotelmanagement.payload.AuthenticateRequest;
import com.ekene.hotelmanagement.payload.RoomDto;
import com.ekene.hotelmanagement.payload.RoomTypeDto;
import com.ekene.hotelmanagement.payload.UserDto;
import com.ekene.hotelmanagement.service.user.UserServiceImpl;
import com.ekene.hotelmanagement.utility.BaseController;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/user")
@CrossOrigin("*")
public class UserController extends BaseController {
    private final UserServiceImpl userService;

    @PostMapping("/register")
    public ResponseEntity<?> saveUser(@RequestBody UserDto userDto){
        return getAppResponse(CREATED, "Success", userService.createUser(userDto));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<?> authenticateUser (@RequestBody AuthenticateRequest authenticateRequest){
        System.out.println("AUTH REQUEST" + authenticateRequest);
        return getAppResponse(OK, "Success", userService.authenticateUser(authenticateRequest));
    }

    @DeleteMapping("deleteUser/{id}")
    @Transactional
    public ResponseEntity<?> deleteUser(@PathVariable(value = "id") Long id){
        return  getAppResponse(OK , "Deleted" , userService.deleteUser(id));
    }

    @PostMapping("addRoom")
    public ResponseEntity<?> addRoom(@RequestBody RoomDto roomDto){
        return getAppResponse(CREATED, "Success", userService.addRoom(roomDto));
    }
//    @PostMapping("addRoom")
//    public ResponseEntity<?> addRoom(@RequestBody RoomDto roomDto, @RequestPart MultipartFile image){
//        return getAppResponse(CREATED, "Success", userService.addRoom(image, roomDto));
//    }

    @DeleteMapping("deleteRoom/{id}")
    @Transactional
    public ResponseEntity<?> deleteRoom(@PathVariable(value = "id") Long id){
        return getAppResponse(OK, "Deleted!", userService.deleteRoom(id));
    }

    @PutMapping("updateRoom/{id}")
    public ResponseEntity<?> updateRoom(@PathVariable(value = "id") Long id, @RequestPart MultipartFile image, @RequestBody RoomDto roomDto){
        return getAppResponse(OK, "Updated", userService.updateRoom(id, image, roomDto));
    }

    @PostMapping("addRoomType")
    public ResponseEntity<?> addRoomType(@RequestParam RoomTypeDto roomTypeDto ){
        return getAppResponse(CREATED, "Success", userService.addRoomType(roomTypeDto));
    }

    @PutMapping("updateRoomType/{id}")
    public ResponseEntity<?> updateRoomType (@PathVariable(value = "id") Long id, @RequestBody RoomTypeDto roomTypeDto, @RequestPart MultipartFile image){
        return getAppResponse(OK, "Updated", userService.updateRoomType(id, image, roomTypeDto));
    }
}
