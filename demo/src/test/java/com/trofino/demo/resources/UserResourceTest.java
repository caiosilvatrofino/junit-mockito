package com.trofino.demo.resources;

import com.trofino.demo.domain.User;
import com.trofino.demo.domain.dto.UserDTO;
import com.trofino.demo.services.impl.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;


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
    void whenFindByIdThenReturnSuccess() {
        Mockito.when(serviceImpl.findById(Mockito.anyInt())).thenReturn(user);
        Mockito.when(mapper.map(any(), any())).thenReturn(userDTO);

        ResponseEntity<UserDTO> response = resource.findById(ID);

        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(UserDTO.class, response.getBody().getClass());

        assertEquals(ID, response.getBody().getId());
        assertEquals(CAIO, response.getBody().getName());
        assertEquals(MAIL, response.getBody().getEmail());
    }

    @Test
    void whenFindAllUsersThenReturnUsers() {
        Mockito.when(serviceImpl.findAll()).thenReturn(List.of(user));
        Mockito.when(mapper.map(any(), any())).thenReturn(userDTO);

        ResponseEntity<List<UserDTO>> response = resource.findAllUsers();

        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(ArrayList.class, response.getBody().getClass());

    }

    @Test
    void whenCreateThenReturnCreated() {
        Mockito.when(serviceImpl.create(any())).thenReturn(user);
        ResponseEntity<UserDTO> response = resource.save(userDTO);

        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getHeaders().get("Location"));

    }

    @Test
    void whenUpdatedTheUserThenReturnSuccess() {
        Mockito.when(serviceImpl.update(userDTO)).thenReturn(user);
        Mockito.when(mapper.map(any(), any())).thenReturn(userDTO);

        ResponseEntity<UserDTO> response = resource.updateUser(ID, userDTO);

        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(UserDTO.class, response.getBody().getClass());
        assertEquals(HttpStatus.OK, response.getStatusCode());


        assertEquals(ID, response.getBody().getId());
        assertEquals(MAIL, response.getBody().getEmail());



    }

    @Test
    void deleteUser() {
    }


    private void startUser() {
        user = new User(ID, CAIO, DADSAD, MAIL);
        userDTO = new UserDTO(ID, CAIO, MAIL, DADSAD);

    }
}