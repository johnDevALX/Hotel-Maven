//package com.ekene.hotelmanagement.service;
//
//import com.ekene.hotelmanagement.model.hotel.RoomType;
//import com.ekene.hotelmanagement.payload.RoomTypeDto;
//import com.ekene.hotelmanagement.repository.RoomTypeRepository;
//import com.ekene.hotelmanagement.service.user.UserService;
//import com.ekene.hotelmanagement.service.user.UserServiceImpl;
//import lombok.RequiredArgsConstructor;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import static org.mockito.Mockito.*;
//
//
//
//@RequiredArgsConstructor
//@ExtendWith(MockitoExtension.class)
//public class UserServiceImplTest {
//    @InjectMocks
//    final UserService userService;
//    @Mock
//    final RoomTypeRepository roomTypeRepository;
//
////    @BeforeEach
////    void setup(){
////        MockitoAnnotations.openMocks(this);
////    }
//// #adoptopenjdk/openjdk11:jre-11.0.8_10-alpine adoptopenjdk/openjdk:17-alpine
//    @Test
//    public void testCreateRoomCategory_Success(){
//        RoomTypeDto roomTypeDto = new RoomTypeDto("deluxe");
//        RoomType roomType = RoomType.builder().title(roomTypeDto.getTitle()).build();
////        when(roomTypeRepository.save(RoomType.class)).thenReturn(d);
//        roomTypeRepository.save(roomType);
//
//        verify(userService, times(1)).addRoomType(roomTypeDto);
//
//    }
//
//}
