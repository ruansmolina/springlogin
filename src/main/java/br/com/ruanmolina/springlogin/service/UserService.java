package br.com.ruanmolina.springlogin.service;

import br.com.ruanmolina.springlogin.DTO.DTORegisterUser;
import br.com.ruanmolina.springlogin.model.User;
import br.com.ruanmolina.springlogin.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    final private UserRepository userRep;
    final private PasswordEncoder passwordEncoder;
    public UserService(UserRepository userRep, PasswordEncoder passwordEncoder){
        this.userRep = userRep;
        this.passwordEncoder= passwordEncoder;
    }
    public User register(DTORegisterUser userDTO){
        User user = new User(userDTO);
        user.setPassword(passwordEncoder.encode((user.getPassword())));
        user = userRep.save(user);

        return user;
    }
    public List<User> getAllUsers(){
        return userRep.findAll();
    }
    public UserDetails getByEmail(String email){
        return userRep.findByEmail(email).orElseThrow(()-> new RuntimeException("Email não encontrado"));
    }

}
