package appbackend.back.controller;

import appbackend.back.model.UserModel;
import appbackend.back.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(path = "/vdt")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @CrossOrigin(origins = "http://localhost:5500")
    @GetMapping("/")
    public String hello() {
        return "Hello";
    }

//    @CrossOrigin(origins = "http://localhost:5500")
//    @GetMapping("/all")
//    public List<UserModel> getAllUser() {
//        return userService.getAllUser();
//    }
//
//    @CrossOrigin(origins = "http://localhost:5500")
//    @PostMapping("/create")
//    public ResponseEntity<String> createUser(@RequestBody UserModel userModel) {
//        return userService.addUser(userModel);
//    }
//
//    @CrossOrigin(origins = "http://localhost:5500")
//    @PutMapping("/update/{id}")
//    public ResponseEntity<String> createUser(@PathVariable(name = "id") int id, @RequestBody UserModel userModel) {
//        return userService.updateUser(id,userModel);
//    }
//
//    @CrossOrigin(origins = "http://localhost:5500")
//    @DeleteMapping("/delete/{id}")
//    public ResponseEntity<String> deleteUser(@PathVariable(name = "id") int id ) {
//        return userService.deleteUser(id);
//    }
}
