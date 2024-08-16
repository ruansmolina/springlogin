package br.com.ruanmolina.springlogin.controller;

import br.com.ruanmolina.springlogin.DTO.DTORegisterUser;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    @PostMapping
    public void registerUser(@RequestBody DTORegisterUser user){

    }
    @GetMapping
    public void getUserList(){

    }

    @PostMapping("/login")
    public void login(){

    }
}
