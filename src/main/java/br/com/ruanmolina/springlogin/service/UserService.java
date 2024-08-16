package br.com.ruanmolina.springlogin.service;

import br.com.ruanmolina.springlogin.DTO.DTORegisterUser;
import br.com.ruanmolina.springlogin.model.User;
import br.com.ruanmolina.springlogin.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    final private UserRepository userRep;
    public UserService(UserRepository userRep){
        this.userRep = userRep;
    }
    public User register(DTORegisterUser userDTO){
        User user = new User(userDTO);
        user = userRep.save(user);
        return user;
    }
    public List<User> getAllUsers(){
        return userRep.findAll();
    }
    public User getByEmail(String email){
        return userRep.findByEmail(email).orElseThrow(()-> new RuntimeException("Email não encontrado"));
    }

}
