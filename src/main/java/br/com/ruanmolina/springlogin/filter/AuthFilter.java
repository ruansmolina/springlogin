package br.com.ruanmolina.springlogin.filter;

import br.com.ruanmolina.springlogin.model.User;
import br.com.ruanmolina.springlogin.repository.UserRepository;
import br.com.ruanmolina.springlogin.service.TokenService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Service
public class AuthFilter extends OncePerRequestFilter {

    private final TokenService tokenService;
    private final UserRepository userRep;

    public AuthFilter(TokenService tokenService, UserRepository userRep){
        this.tokenService = tokenService;
        this.userRep = userRep;
    }
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String token = recoverToken(request);
        if(token != null){
            String subject = tokenService.recoverSubject(token);
            UserDetails user = userRep.findByEmail(subject).orElseThrow(()-> new RuntimeException("Usuario não encontrado"));
            UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(user,null,user.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(auth);
        }
        filterChain.doFilter(request,response);

    }
    public String recoverToken(HttpServletRequest request){
        if(request.getHeader("Authorization") != null) {
            var header = request.getHeader("Authorization").replace("Bearer ", "").trim();

            return header;
        }
        return null;
    }
}
