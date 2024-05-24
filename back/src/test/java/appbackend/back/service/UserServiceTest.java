package appbackend.back.service;//package appbackend.back.service;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.*;
//
//import appbackend.back.model.UserModel;
//import appbackend.back.repository.UserRepository;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//
//public class UserServiceTest {
//
//    @Mock
//    private UserRepository userRepository;
//
//    @InjectMocks
//    private UserService userService;
//
//    private UserModel user1;
//
//    @BeforeEach
//    public void setUp() {
//        user1 = new UserModel(
//                "H1", "Ng", "Dg", 2003,
//                "ngodanghuy1623@gmail.com", "034347338",
//                "Male", "UET",1);
//        userRepository = mock(UserRepository.class);
//        userService = new UserService(userRepository);
//    }
//
//    @Test
//    public void testGetAllUsers_ShouldReturnAllUsers() {
//        List<UserModel> users = new ArrayList<>();
//        users.add(user1);
//
//        when(userRepository.findAll()).thenReturn(users);
//
//        List<UserModel> retrievedUsers = userService.getAllUser();
//
//        assertEquals(users, retrievedUsers, "Retrieved users should match expected list");
//    }
//
//    @Test
//    public void testGetAllUsers_ShouldReturnEmptyList_WhenNoUsersExist() {
//        when(userRepository.findAll()).thenReturn(new ArrayList<>());
//
//        List<UserModel> retrievedUsers = userService.getAllUser();
//
//        assertTrue(retrievedUsers.isEmpty(), "Retrieved list should be empty");
//    }
//
//    @Test
//    public void testAddUser_ShouldSuccess_WhenValidUser() {
//        when(userRepository.save(user1)).thenReturn(user1);
//
//        ResponseEntity<String> response = userService.addUser(user1);
//
//        assertEquals(HttpStatus.OK, response.getStatusCode(), "Status code should be OK");
//        assertEquals("Success", response.getBody(), "Response message should be Success");
//    }
//
//    @Test
//    public void testAddUser_ShouldFail_WhenExceptionOccurs() {
//        when(userRepository.save(user1)).thenThrow(new RuntimeException("Simulated exception"));
//
//        ResponseEntity<String> response = userService.addUser(user1);
//
//        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode(), "Status code should be BAD_REQUEST");
//        assertEquals("Failed", response.getBody(), "Response message should be Failed");
//    }
//
//    @Test
//    public void testUpdateUser_ShouldSuccess_WhenUserExists() {
//        when(userRepository.findById(user1.getId())).thenReturn(Optional.of(user1));
//        user1.setEmail("updated.email@example.com");
//
//        ResponseEntity<String> response = userService.updateUser(user1.getId(), user1);
//
//        assertEquals(HttpStatus.OK, response.getStatusCode(), "Status code should be OK");
//        assertEquals("Success", response.getBody(), "Response message should be Success");
//        verify(userRepository).saveAndFlush(user1); // Verify method call
//    }
//
//    @Test
//    public void testUpdateUser_ShouldFail_WhenUserNotFound() {
//        when(userRepository.findById(user1.getId())).thenReturn(Optional.empty());
//
//        ResponseEntity<String> response = userService.updateUser(user1.getId(), user1);
//
//        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode(), "Status code should be BAD_REQUEST");
//        assertEquals("Failed", response.getBody(), "Response message should be Failed");
//    }
//
//    @Test
//    public void testUpdateUser_ShouldFail_WhenExceptionOccurs() {
//        when(userRepository.findById(user1.getId())).thenReturn(Optional.of(user1));
//        user1.setEmail("updated.email@example.com");
//        when(userRepository.saveAndFlush(user1)).thenThrow(new RuntimeException("Simulated exception"));
//
//        ResponseEntity<String> response = userService.updateUser(user1.getId(), user1);
//
//        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode(), "Status code should be BAD_REQUEST");
//        assertEquals("Failed", response.getBody(), "Response message should be Failed");
//    }
//
//    @Test
//    public void testDeleteUser_ShouldSuccess_WhenUserExists() {
//        when(userRepository.existsById(user1.getId())).thenReturn(true);
//
//        ResponseEntity<String> response = userService.deleteUser(user1.getId());
//
//        assertEquals(HttpStatus.OK, response.getStatusCode(), "Status code should be BAD_REQUEST");
//        assertEquals("Failed", response.getBody(), "Response message should be Failed");
//    }
//}
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

//    @Test
//    void updateUser_Success() {
  
//        // Setup mock behavior
//        when(userRepository.findById(1)).thenReturn(Optional.of(testUser));
//        when(userRepository.saveAndFlush(testUser)).thenReturn(testUser);
//
//        // Call service method
//        ResponseEntity<String> result = userService.updateUser(1, testUser);
//
//        // Assert result
//        assertEquals(HttpStatus.OK, result.getStatusCode());
//        assertEquals("Success", result.getBody());
//    }
//
//    @Test
//    void updateUser_UserNotFound() {
//        // Setup mock behavior
//        when(userRepository.findById(1)).thenReturn(Optional.empty());
//
//        // Call service method
//        ResponseEntity<String> result = userService.updateUser(1, testUser);
//
//        // Assert result
//        assertEquals(HttpStatus.NOT_FOUND, result.getStatusCode());
//        assertEquals("User not found", result.getBody());
//    }
//
//    @Test
//    void updateUser_Failed() {
//        // Setup mock behavior
//        when(userRepository.findById(1)).thenReturn(Optional.of(testUser));
//        doThrow(new RuntimeException()).when(userRepository).saveAndFlush(testUser);
//
//        // Call service method
//        ResponseEntity<String> result = userService.updateUser(1, testUser);
//
//        // Assert result
//        assertEquals(HttpStatus.BAD_REQUEST, result.getStatusCode());
//        assertEquals("Failed", result.getBody());
//    }
//
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
