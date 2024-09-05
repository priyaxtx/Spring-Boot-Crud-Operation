package com.example.demo.controller;

import com.example.demo.exception.UserNotFoundExeception;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@RestController
@CrossOrigin("http://localhost:3000")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/user")
    User newUser(@RequestBody User user) {
        return userRepository.save(user);
    };

    @GetMapping("/users")
    List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @GetMapping("/user/{id}")
    User getUserById(@PathVariable long id) {
        return  userRepository.findById(id).orElseThrow(()-> new UserNotFoundExeception(id));
    }

    @PutMapping("/user/{id}")
    User updateUser(@RequestBody User newUser, @PathVariable Long id){
        return userRepository.findById(id)
                .map(user -> {
                    user.setUsername(newUser.getUsername());
                    user.setName(newUser.getName());
                    user.setEmail(newUser.getEmail());
                    return userRepository.save(user);
                }).orElseThrow(()-> new UserNotFoundExeception(id));

}

    @DeleteMapping("/user/{id}")
    String deleteUser(@PathVariable long id) {
        if(!userRepository.existsById(id))
        {throw new UserNotFoundExeception(id);}
            userRepository.deleteById(id);
return "User deleted"+id+"has been deleted";
}
}
