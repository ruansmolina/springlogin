package br.com.ruanmolina.springlogin.controller;

import br.com.ruanmolina.springlogin.DTO.DTORegisterUser;
import br.com.ruanmolina.springlogin.model.User;
import br.com.ruanmolina.springlogin.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;
    public UserController(UserService userService){
        this.userService = userService;
    }
    @PostMapping
    public ResponseEntity<User> registerUser(@RequestBody @Valid DTORegisterUser dtoUser, UriComponentsBuilder uriBuilder){
        User user = this.userService.register(dtoUser);
        URI uri = uriBuilder.path("/user/{id}").buildAndExpand(user.getId()).toUri();
        return ResponseEntity.created(uri).body(user);
    }
    @GetMapping
    public ResponseEntity<List<User>> getUserList(){
        return ResponseEntity.ok(this.userService.getAllUsers());
    }

    @PostMapping("/login")
    public void login(){

    }
}
