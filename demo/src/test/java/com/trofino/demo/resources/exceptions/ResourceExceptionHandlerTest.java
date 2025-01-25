package com.trofino.demo.resources.exceptions;

import com.trofino.demo.services.exceptions.DataIntegratyViolationException;
import com.trofino.demo.services.exceptions.ObjectNotFoundExceptions;
import org.apache.coyote.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ResourceExceptionHandlerTest {

    @InjectMocks
    private ResourceExceptionHandler resourceExceptionHandler;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void whenObjectNotFoundExceptionThenReturnAResponseEntity() {

        ResponseEntity<StandardError> response = resourceExceptionHandler
                .objectNotFound(new ObjectNotFoundExceptions("Objeto não encontrado")
                        , new MockHttpServletRequest());

        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.NOT_FOUND,response.getStatusCode());
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(StandardError.class, response.getBody().getClass());
        assertEquals("Objeto não encontrado", response.getBody().getError());
        assertEquals(404, response.getBody().getStatus());
    }

    @Test
    void whenDataIntegratyViolationExceptionThenReturnResponse() {

        ResponseEntity<StandardError> response = resourceExceptionHandler
                .dataIntegratyViolationException(new DataIntegratyViolationException("Dados invalidos.")
                        , new MockHttpServletRequest());

        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.BAD_REQUEST,response.getStatusCode());
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(StandardError.class, response.getBody().getClass());
        assertEquals("Dados invalidos.", response.getBody().getError());
        assertEquals(400, response.getBody().getStatus());
    }
}