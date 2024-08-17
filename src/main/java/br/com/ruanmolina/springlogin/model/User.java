package br.com.ruanmolina.springlogin.model;

import br.com.ruanmolina.springlogin.DTO.DTORegisterUser;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Users")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Column(unique = true)
    private String email;
    private String password;

    public User(DTORegisterUser user){
        this.name = user.name();
        this.email= user.email();
        this.password= user.password();
    }



}
