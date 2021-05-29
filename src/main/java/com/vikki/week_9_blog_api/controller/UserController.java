package com.vikki.week_9_blog_api.controller;

import com.vikki.week_9_blog_api.model.User;
import com.vikki.week_9_blog_api.repository.UserRepository;
//import com.vikki.week_9_blog_api.service.UserService;
//import com.vikki.week_9_blog_api.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {


    @Autowired
    private UserRepository userRepository;

    // List all Users
    @GetMapping()
    public List<User> showUsers() {
        return this.userRepository.findAll();
    }

    //add user
    @PostMapping()
    public User addUser(@RequestBody User user) {
        Date registerTime = new Date();
        user.setDateCreated(registerTime);
        return this.userRepository.save(user);
    }

    // get user by email
    @GetMapping("/{userEmail}")
    public User getUserByEmail(@PathVariable(value="userEmail") String userEmail) {
        return userRepository.findUserByEmail(userEmail);
    }

    //upaate user by email
    @PutMapping("/{userEmail}")
    public  User updateUserByEmail(@RequestBody User user, @PathVariable(value="userEmail") String userEmail) {
        User existingUser = this.userRepository.findUserByEmail(userEmail);
        existingUser.setFirstName(user.getFirstName());
        existingUser.setLastName(user.getLastName());
        existingUser.setEmail(user.getEmail());
        existingUser.setPassword(user.getPassword());
        return this.userRepository.save(existingUser);
    }


    @PatchMapping("/{userEmail}")
    public ResponseEntity < ? > updateResource(@RequestParam("userEmail") @PathVariable("userEmail") String userEmail) {
        User existingUser = userRepository.findUserByEmail(userEmail);
        return new ResponseEntity < > (existingUser, HttpStatus.OK);
    }


    //Delete user by email
    @DeleteMapping("/{userEmail}")
    public ResponseEntity<User> deleteUser(@PathVariable(value="userEmail") String userEmail) {
        User existingUser = this.userRepository.findUserByEmail(userEmail);
        this.userRepository.delete(existingUser);
        return ResponseEntity.ok().build();
    }

}
