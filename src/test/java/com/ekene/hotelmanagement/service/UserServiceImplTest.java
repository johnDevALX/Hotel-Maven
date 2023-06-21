package com.ekene.hotelmanagement.service;

import com.ekene.hotelmanagement.repository.UserRepository;
import com.ekene.hotelmanagement.service.user.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
@RequiredArgsConstructor
public class UserServiceImplTest {
    @InjectMocks
    private final UserServiceImpl userService;
    @MockBean
    private UserRepository userRepository;

    @BeforeEach
    void setup(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateUser_Success(){
    }

}
