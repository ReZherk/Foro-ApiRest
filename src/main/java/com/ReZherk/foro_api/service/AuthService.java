package com.ReZherk.foro_api.service;

import com.ReZherk.foro_api.domain.usuario.Usuario;
import com.ReZherk.foro_api.dto.AuthRequest;
import com.ReZherk.foro_api.dto.AuthResponse;
import com.ReZherk.foro_api.repository.UsuarioRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class AuthService {
    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder encoder;
    private final JwtTokenService jwt;

    public AuthService(UsuarioRepository usuarioRepository, PasswordEncoder encoder, JwtTokenService jwt) {
        this.usuarioRepository = usuarioRepository;
        this.encoder = encoder;
        this.jwt = jwt;
    }

    public AuthResponse authenticate(AuthRequest req) {
        Usuario user = usuarioRepository.findByEmail(req.email())
                .orElseThrow(() -> new IllegalArgumentException("Credenciales inválidas"));
        if (!encoder.matches(req.password(), user.getPassword())) {
            throw new IllegalArgumentException("Credenciales inválidas");
        }
        String token = jwt.generateToken(String.valueOf(user.getId()), Map.of(
                "email", user.getEmail(),
                "nombre", user.getNombre()
        ));
        return new AuthResponse("Bearer", token);
    }
}