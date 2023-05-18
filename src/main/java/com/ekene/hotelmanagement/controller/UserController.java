package com.ekene.hotelmanagement.controller;

import com.ekene.hotelmanagement.payload.RoomDto;
import com.ekene.hotelmanagement.payload.RoomTypeDto;
import com.ekene.hotelmanagement.payload.UserDto;
import com.ekene.hotelmanagement.service.UserServiceImpl;
import com.ekene.hotelmanagement.utility.BaseController;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/user")
public class UserController extends BaseController {
    private final UserServiceImpl userService;

    @PostMapping("saveUser")
    public ResponseEntity<?> saveUser(@RequestBody UserDto userDto){
        return getAppResponse(CREATED, "Success", userService.createUser(userDto));
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

    @DeleteMapping("deleteRoom/{id}")
    @Transactional
    public ResponseEntity<?> deleteRoom(@PathVariable(value = "id") Long id){
        return getAppResponse(OK, "Deleted!", userService.deleteRoom(id));
    }

    @PutMapping("updateRoom/{id}")
    public ResponseEntity<?> updateRoom(@PathVariable(value = "id") Long id, @RequestBody RoomDto roomDto){
        return getAppResponse(OK, "Updated", userService.updateRoom(id, roomDto));
    }

    @PostMapping("addRoomType")
    public ResponseEntity<?> addRoomType(@RequestBody RoomTypeDto roomTypeDto){
        return getAppResponse(CREATED, "Success", userService.addRoomType(roomTypeDto));
    }

    @PutMapping("updateRoomType/{id}")
    public ResponseEntity<?> updateRoomType (@PathVariable(value = "id") Long id, @RequestBody RoomTypeDto roomTypeDto){
        return getAppResponse(OK, "Updated", userService.updateRoomType(id, roomTypeDto));
    }
}
