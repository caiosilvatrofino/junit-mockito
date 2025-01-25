package com.trofino.demo.services.impl;

import com.trofino.demo.domain.User;
import com.trofino.demo.domain.dto.UserDTO;
import com.trofino.demo.repositories.UserRepository;
import com.trofino.demo.services.exceptions.DataIntegratyViolationException;
import com.trofino.demo.services.exceptions.ObjectNotFoundExceptions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

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

    @Mock
    private ModelMapper mapper;

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

    @Test
    void whenFindAllThenReturnListOfUsers() {
        Mockito.when(service.findAll()).thenReturn(List.of(user));

        List<User> result = service.findAll();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(User.class, result.get(0).getClass());
        assertEquals(ID, result.get(0).getId());
        assertEquals(CAIO, result.get(0).getName());
        assertEquals(MAIL, result.get(0).getEmail());
    }

    @Test
    void whenCreateThenReturnSuccess() {
        Mockito.when(repository.save(any())).thenReturn(user);

        User response = service.create(userDTO);

        assertNotNull(response);
        assertEquals(User.class, response.getClass());
        assertEquals(ID, response.getId());
        assertEquals(CAIO, response.getName());
        assertEquals(MAIL, response.getEmail());
        assertEquals(DADSAD, response.getPassword());
    }

    @Test
    void whenCreateThenReturnException(){
        Mockito.when(repository.save(any())).thenThrow(new DataIntegratyViolationException("Email já cadastrado no banco de dados"));

        try {
            User response = service.create(userDTO);
        } catch (Exception exception) {
            assertEquals(DataIntegratyViolationException.class, exception.getClass());
            assertEquals("Email já cadastrado no banco de dados", exception.getMessage());
        }

    }

    @Test
    void whenUpdateThenReturnSuccess() {
        Mockito.when(repository.save(any())).thenReturn(user);

        User response = service.update(userDTO);

        assertNotNull(response);
        assertEquals(User.class, response.getClass());
        assertEquals(ID, response.getId());
        assertEquals(CAIO, response.getName());
        assertEquals(MAIL, response.getEmail());
        assertEquals(DADSAD, response.getPassword());
    }

    @Test
    void deleteWithSuccess() {
        Mockito.when(repository.findById(anyInt())).thenReturn(optionalUser);
        doNothing().when(repository).deleteById(anyInt());
        service.delete(ID);

        verify(repository, times(1)).deleteById(anyInt());
    }

    @Test
    void deleteObjectNotFoundException() {
        when(repository.findById(anyInt()))
                .thenThrow(new ObjectNotFoundExceptions("Objeto não encontrado"));
        try {
            service.delete(ID);
        } catch (Exception ex) {
            assertEquals(ObjectNotFoundExceptions.class, ex.getClass());
            assertEquals("Objeto não encontrado", ex.getMessage());
        }
    }


    private void startUser() {
        user = new User(ID, CAIO, DADSAD, MAIL);
        userDTO = new UserDTO(ID, CAIO, MAIL, DADSAD);
        optionalUser = Optional.of(new User(ID, CAIO, DADSAD, MAIL));
    }
}
