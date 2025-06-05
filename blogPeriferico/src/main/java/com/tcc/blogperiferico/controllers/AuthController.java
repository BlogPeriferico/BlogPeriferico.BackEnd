package com.tcc.blogperiferico.controllers;

import com.tcc.blogperiferico.entities.Usuario;
import com.tcc.blogperiferico.repositories.UsuarioRepository;
import com.tcc.blogperiferico.security.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private final String ADMIN_EMAIL = "blog.periferico@gmail.com";
    private final String ADMIN_ROLE = "ADMIN";
    private final String USER_ROLE = "USUARIO";

    @PostMapping("/login")
    public Map<String, String> login(@RequestBody Map<String, String> body) {
        String email = body.get("email");
        String senha = body.get("senha");

        Optional<Usuario> user = usuarioRepository.findByEmail(email);
        if (user.isPresent() && passwordEncoder.matches(senha, user.get().getSenha())) {
            String token = jwtTokenProvider.generateToken(email);
            String role = email.equals(ADMIN_EMAIL) ? ADMIN_ROLE : USER_ROLE;

            Map<String, String> response = new HashMap<>();
            response.put("token", token);
            response.put("papel", role);
            return response;
        } else {
            throw new RuntimeException("Credenciais inválidas");
        }
    }
}
