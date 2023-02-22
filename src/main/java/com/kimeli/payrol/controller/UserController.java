package com.kimeli.payrol.controller;

import com.kimeli.payrol.model.User;
import com.kimeli.payrol.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/users")
    public ResponseEntity<?> getUser() {
        Map<String, Object> map = new LinkedHashMap<String, Object>();
        List<User> userList = userService.getUser();
        if (!userList.isEmpty()) {
            map.put("status", 1);
            map.put("data", userList);
            return new ResponseEntity<>(map, HttpStatus.OK);
        } else {
            map.clear();
            map.put("status", 0);
            map.put("message", "Data is not found");
            return new ResponseEntity<>(map, HttpStatus.NOT_FOUND);
        }
    }
    @PostMapping("/save")
    public ResponseEntity<?> saveUser(@RequestBody User user) {
        Map<String, Object> map = new LinkedHashMap<String, Object>();
        userService.save(user);
        map.put("status", 1);
        map.put("message", "Record is Saved Successfully!");
        return new ResponseEntity<>(map, HttpStatus.CREATED);
    }
    @GetMapping("/user/{id}")
    public ResponseEntity<?> getUserById(@PathVariable Integer id) {
        Map<String, Object> map = new LinkedHashMap<String, Object>();
        try {
            User user = userService.findById(id);
            map.put("status", 1);
            map.put("data", user);
            return new ResponseEntity<>(map, HttpStatus.OK);
        } catch (Exception ex) {
            map.clear();
            map.put("status", 0);
            map.put("message", "Data is not found");
            return new ResponseEntity<>(map, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Integer id) {
        Map<String, Object> map = new LinkedHashMap<String, Object>();
        try {
            User user = userService.findById(id);
            userService.delete(user);
            map.put("status", 1);
            map.put("message", "Record is deleted successfully!");
            return new ResponseEntity<>(map, HttpStatus.OK);
        } catch (Exception ex) {
            map.clear();
            map.put("status", 0);
            map.put("message", "Data is not found");
            return new ResponseEntity<>(map, HttpStatus.NOT_FOUND);
        }
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateUserById(@PathVariable Integer id, @RequestBody User userDetail) {
        Map<String, Object> map = new LinkedHashMap<String, Object>();
        try {
            User user = userService.findById(id);
            user.setUserName(userDetail.getUserName());
            user.setMobileNo(userDetail.getMobileNo());
            user.setEmailId(userDetail.getEmailId());
            user.setCity(userDetail.getCity());
            user.setPassword(userDetail.getPassword());
            userService.save(user);
            map.put("status", 1);
            map.put("data", userService.findById(id));
            return new ResponseEntity<>(map, HttpStatus.OK);
        } catch (Exception ex) {
            map.clear();
            map.put("status", 0);
            map.put("message", "Data is not found");
            return new ResponseEntity<>(map, HttpStatus.NOT_FOUND);
        }
    }
}
