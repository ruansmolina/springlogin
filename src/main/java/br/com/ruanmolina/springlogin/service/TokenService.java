package br.com.ruanmolina.springlogin.service;

import br.com.ruanmolina.springlogin.model.User;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {
    private String secret = "SprLog2024";
    private String issuer ="API SpringLogin";

    public String generateToken(User user){
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return  JWT.create()
                    .withIssuer(issuer)
                    .withSubject(user.getEmail())
                    .withExpiresAt(ExpirationDate())
                    .sign(algorithm);

        }catch(JWTCreationException exception){
            throw new RuntimeException("Erro ao gerar tokenJWT");
        }
    }
    public String recoverSubject(String token){
        Algorithm algorithm = Algorithm.HMAC256(secret);
        try {
            return JWT.require(algorithm).withIssuer(issuer)
                    .build()
                    .verify(token)
                    .getSubject();

        }catch (JWTVerificationException e) {
            throw new RuntimeException("Token invalido ou expirado");
        }

    }

    private Instant ExpirationDate() {
        return LocalDateTime.now()
                .plusHours(3)
                .toInstant(ZoneOffset.of("-03:00"));
    }

}
