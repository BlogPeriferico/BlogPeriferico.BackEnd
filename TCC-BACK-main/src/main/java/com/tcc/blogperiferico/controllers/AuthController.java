package com.tcc.blogperiferico.controllers;

import com.tcc.blogperiferico.dto.AuthRequest;
import com.tcc.blogperiferico.dto.AuthResponse;
import com.tcc.blogperiferico.entities.Usuario;
import com.tcc.blogperiferico.repository.UsuarioRepository;
import com.tcc.blogperiferico.security.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "http://localhost:5500", allowCredentials = "true")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private JWTUtil jwtUtil;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest request) {
        System.out.println("AuthRequest recebido: email=" + request.getEmail() + ", senha=" + request.getSenha());
        try {
            authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getSenha())
            );
            UserDetails userDetails = userDetailsService.loadUserByUsername(request.getEmail());
            System.out.println("UserDetails carregado: " + userDetails.getUsername());
            Usuario usuario = usuarioRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado com email: " + request.getEmail()));
            System.out.println("Usuario encontrado: " + usuario.getEmail() + ", role: " + usuario.getRoles());
            String role = usuario.getRoles().name(); // Removido o prefixo "ROLE_"
            String token = jwtUtil.generateToken(userDetails, role);
            System.out.println("Token gerado: " + token);
            return ResponseEntity.ok(new AuthResponse(token));
        } catch (BadCredentialsException e) {
            System.out.println("Erro de autenticação: Credenciais inválidas - " + e.getMessage());
            return ResponseEntity.status(401).body("Credenciais inválidas: senha incorreta.");
        } catch (UsernameNotFoundException e) {
            System.out.println("Erro de autenticação: " + e.getMessage());
            return ResponseEntity.status(401).body("Credenciais inválidas: usuário não encontrado.");
        } catch (Exception e) {
            System.out.println("Erro inesperado: " + e.getClass().getSimpleName() + " - " + e.getMessage());
            return ResponseEntity.status(401).body("Erro ao autenticar: " + e.getMessage());
        }
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/usuario-logado")
    public ResponseEntity<?> usuarioLogado(@AuthenticationPrincipal Usuario usuario) {
        return ResponseEntity.ok("Você está autenticado como: " + usuario.getEmail() + " | Role: " + usuario.getRoles());
    }

    @PreAuthorize("hasRole('ADMINISTRADOR')")
    @GetMapping("/admin-only")
    public ResponseEntity<?> adminOnly() {
        return ResponseEntity.ok("Você tem acesso ADMINISTRADOR.");
    }
}