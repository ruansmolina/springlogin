package br.com.ruanmolina.springlogin.controller;

import br.com.ruanmolina.springlogin.DTO.DTOLogin;
import br.com.ruanmolina.springlogin.DTO.DTORegisterUser;
import br.com.ruanmolina.springlogin.DTO.DTOTokenJWT;
import br.com.ruanmolina.springlogin.model.User;
import br.com.ruanmolina.springlogin.service.TokenService;
import br.com.ruanmolina.springlogin.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;
    private final AuthenticationManager authManager;
    private final TokenService tokenService;

    public UserController(UserService userService, AuthenticationManager authManager, TokenService tokenService) {

        this.userService = userService;
        this.authManager = authManager;
        this.tokenService = tokenService;
    }

    @PostMapping
    public ResponseEntity<User> registerUser(@RequestBody @Valid DTORegisterUser dtoUser, UriComponentsBuilder uriBuilder) {
        User user = this.userService.register(dtoUser);
        URI uri = uriBuilder.path("/user/{id}").buildAndExpand(user.getId()).toUri();
        return ResponseEntity.created(uri).body(user);
    }

    @GetMapping
    public ResponseEntity<List<User>> getUserList() {
        return ResponseEntity.ok(this.userService.getAllUsers());
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody DTOLogin login) {
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(login.email(), login.password());
        Authentication auth = authManager.authenticate(authToken);
        String tokenJWT = tokenService.generateToken((User) auth.getPrincipal());
        return ResponseEntity.ok(new DTOTokenJWT(tokenJWT));

    }
}
