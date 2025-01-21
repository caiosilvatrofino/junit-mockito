package com.trofino.demo.services.impl;

import com.trofino.demo.domain.User;
import com.trofino.demo.domain.dto.UserDTO;
import com.trofino.demo.repositories.UserRepository;
import com.trofino.demo.services.exceptions.ObjectNotFoundExceptions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;

@ExtendWith(MockitoExtension.class)  // Usando MockitoExtension em vez de SpringBootTest
class UserServiceImplTest {

    public static final int ID = 1;
    public static final String CAIO = "caio";
    public static final String DADSAD = "dadsad";
    public static final String MAIL = "caios.trofino@gmail.com";

    @InjectMocks   // cria uma instancia real do service.
    private UserServiceImpl service;

    @Mock   //fingir
    private UserRepository repository;

    private User user;
    private UserDTO userDTO;
    private Optional<User> optionalUser;

    @BeforeEach
    void setUp() {
        startUser();
    }

    @Test
    void whenFindByIdThenReturnAnUserInstance0() {
        Mockito.when(repository.findById(anyInt())).thenReturn(optionalUser);

        User response = service.findById(ID);

        assertNotNull(response);
        assertEquals(User.class, response.getClass());
        assertEquals(ID, response.getId());
        assertEquals(CAIO,response.getName());
        assertEquals(MAIL, response.getEmail());
    }

    @Test
    void whenFindByIdThenReturnObjectNotFoundException(){
        Mockito.when(repository.findById(anyInt())).thenThrow(new ObjectNotFoundExceptions("Objeto não encontrado"));

        try {
            service.findById(ID);

        }catch (Exception exception) {
            assertEquals(ObjectNotFoundExceptions.class, exception.getClass());
            assertEquals("Objeto não encontrado", exception.getMessage());
        }

    }

    private void startUser() {
        user = new User(ID, CAIO, DADSAD, MAIL);
        userDTO = new UserDTO(ID, CAIO, MAIL, DADSAD);
        optionalUser = Optional.of(new User(ID, CAIO, DADSAD, MAIL));
    }
}
