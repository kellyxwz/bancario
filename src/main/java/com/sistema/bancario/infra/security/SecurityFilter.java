package com.sistema.bancario.infra.security;

import com.sistema.bancario.entities.User;
import com.sistema.bancario.repository.UserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class SecurityFilter extends OncePerRequestFilter {


    private final TokenService service;
    private final UserRepository repository;

    public SecurityFilter(TokenService service, UserRepository repository) {
        this.service = service;
        this.repository = repository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        var token = this.recoverToken(request);
        if (token != null){
            var login = service.validateToken(token);

            // 1. Busca a entidade pura do banco de dados (User)
            com.sistema.bancario.entities.User userEntity = repository.findByEmail(login);

            // 🔒 2. Blindagem contra nulo
            if (userEntity != null) {
                // 3. Transforma a entidade no CustomUserDetails para recuperar os métodos do Spring Security
                UserDetails userDetails = new UserDetailsImpl(userEntity);

                // 4. Agora sim! Usamos userDetails (que tem o getAuthorities()) em vez de userEntity
                var authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }
        filterChain.doFilter(request, response);
    }

    private String recoverToken(HttpServletRequest request) {
        var authHeader = request.getHeader("Authorization");
        if (authHeader == null || !authHeader.startsWith("Bearer ")) return null;

        return authHeader.substring(7);
    }
}
