package Backend.user.service;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import Backend.user.dto.UserRequestDto;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Map;

@SpringBootTest
class UserServiceTest {
    @Autowired
    UserService userService;

    @Test
    void signup() {
        UserRequestDto user = new UserRequestDto();
        user.setUsername("test1");
        user.setPassword("test1");
        ResponseEntity<Map<String, Object>> response = userService.signup(user);
        assertEquals(response.getStatusCode(), HttpStatus.OK);
    }

    @Test
    void login() {
        UserRequestDto user = new UserRequestDto();
        user.setUsername("test1");
        user.setPassword("test1");
        ResponseEntity<Map<String, Object>> response = userService.login(user);
        assertEquals(response.getStatusCode(), HttpStatus.OK);
    }
}