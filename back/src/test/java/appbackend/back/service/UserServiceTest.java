package appbackend.back.service;

import appbackend.back.model.UserModel;
import appbackend.back.repository.UserRepository;
import appbackend.back.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    private UserModel testUser;

    @BeforeEach
    void setUp() {
        testUser = new UserModel(
                "Huy Ngo", 2003,
                "ngodanghuy1623@gmail.com", "034347338",
                "Male", "UET",1, "VietNam");
    }

    @Test
    void getAllUser() {
        // Setup mock behavior
        List<UserModel> userList = new ArrayList<>();
        userList.add(testUser);
        when(userRepository.findAll()).thenReturn(userList);

        // Call service method
        List<UserModel> result = userService.getAllUser();

        // Assert result
        assertEquals(1, result.size());
        assertEquals(testUser, result.get(0));
    }

    @Test
    void addUser_Success() {
        // Setup mock behavior
        when(userRepository.save(testUser)).thenReturn(testUser);

        // Call service method
        ResponseEntity<String> result = userService.addUser(testUser);

        // Assert result
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals("Success", result.getBody());
    }

    @Test
    void addUser_Failed() {
        // Setup mock behavior
        when(userRepository.save(testUser)).thenThrow(new RuntimeException("Simulated exception"));

        // Call service method
        ResponseEntity<String> result = userService.addUser(testUser);

        // Assert result
        assertEquals(HttpStatus.BAD_REQUEST, result.getStatusCode());
        assertEquals("Failed", result.getBody());
    }

    @Test
    void updateUser_Success() {
        // Setup mock behavior
        when(userRepository.findById(1)).thenReturn(Optional.of(testUser));
        when(userRepository.saveAndFlush(testUser)).thenReturn(testUser);

        // Call service method
        ResponseEntity<String> result = userService.updateUser(1, testUser);

        // Assert result
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals("Success", result.getBody());
    }

    @Test
    void updateUser_UserNotFound() {
        // Setup mock behavior
        when(userRepository.findById(1)).thenReturn(Optional.empty());

        // Call service method
        ResponseEntity<String> result = userService.updateUser(1, testUser);

        // Assert result
        assertEquals(HttpStatus.NOT_FOUND, result.getStatusCode());
        assertEquals("User not found", result.getBody());
    }

    @Test
    void updateUser_Failed() {
        // Setup mock behavior
        when(userRepository.findById(1)).thenReturn(Optional.of(testUser));
        doThrow(new RuntimeException()).when(userRepository).saveAndFlush(testUser);

        // Call service method
        ResponseEntity<String> result = userService.updateUser(1, testUser);

        // Assert result
        assertEquals(HttpStatus.BAD_REQUEST, result.getStatusCode());
        assertEquals("Failed", result.getBody());
    }

//    @Test
//    void deleteUser_Success() {
//        // Setup mock behavior
//        when(userRepository.existsById(1)).thenReturn(true);
//
//        // Call service method
//        ResponseEntity<String> result = userService.deleteUser(1);
//
//        // Assert result
//        assertEquals(HttpStatus.OK, result.getStatusCode());
//        assertEquals("Success", result.getBody());
//        verify(userRepository).deleteById(1);
//    }
//
//    @Test
//    void deleteUser_UserNotFound() {
//        // Setup mock behavior
//        when(userRepository.existsById(1)).thenReturn(false);
//
//        // Call service method
//        ResponseEntity<String> result = userService.deleteUser(1);
//
//        // Assert result
//        assertEquals(HttpStatus.NOT_FOUND, result.getStatusCode());
//        assertEquals("User not found", result.getBody());
//    }
//
//    @Test
//    void deleteUser_Failed() {
//        // Setup mock behavior
//        when(userRepository.existsById(1)).thenReturn(true);
//        doThrow(new RuntimeException("Simulated exception")).when(userRepository).deleteById(1);
//
//        // Call service method
//        ResponseEntity<String> result = userService.deleteUser(1);
//
//        // Assert result
//        assertEquals(HttpStatus.BAD_REQUEST, result.getStatusCode());
//        assertEquals("Failed", result.getBody());
//    }
}
