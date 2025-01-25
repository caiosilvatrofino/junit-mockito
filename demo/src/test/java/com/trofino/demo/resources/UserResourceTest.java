package com.trofino.demo.resources;

import com.trofino.demo.domain.User;
import com.trofino.demo.domain.dto.UserDTO;
import com.trofino.demo.services.impl.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
class UserResourceTest {

    public static final int ID = 1;
    public static final String CAIO = "caio";
    public static final String DADSAD = "dadsad";
    public static final String MAIL = "caios.trofino@gmail.com";

    private User user;
    private UserDTO userDTO;

    @InjectMocks
    private UserResource resource;

    @Mock
    private ModelMapper mapper;

    @Mock
    private UserServiceImpl serviceImpl;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        startUser();
    }

    @Test
    void findById() {
    }

    @Test
    void findAllUsers() {
    }

    @Test
    void save() {
    }

    @Test
    void updateUser() {
    }

    @Test
    void deleteUser() {
    }


    private void startUser() {
        user = new User(ID, CAIO, DADSAD, MAIL);
        userDTO = new UserDTO(ID, CAIO, MAIL, DADSAD);

    }
}