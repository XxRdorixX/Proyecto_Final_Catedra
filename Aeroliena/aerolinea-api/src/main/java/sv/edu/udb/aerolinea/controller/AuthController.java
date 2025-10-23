package sv.edu.udb.aerolinea.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import sv.edu.udb.aerolinea.entity.Usuario;
import sv.edu.udb.aerolinea.repository.UsuarioRepository;
import sv.edu.udb.aerolinea.security.jwt.JwtTokenUtil;

import java.util.Map;

/**
 * Controlador para autenticación y registro JWT.
 */
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthenticationManager authManager;

    private final JwtTokenUtil jwtUtil;
    private final UsuarioRepository usuarioRepo;
    private final PasswordEncoder passwordEncoder;

    public AuthController(AuthenticationManager authManager, JwtTokenUtil jwtUtil,
                          UsuarioRepository usuarioRepo, PasswordEncoder passwordEncoder) {
        this.authManager = authManager;
        this.jwtUtil = jwtUtil;
        this.usuarioRepo = usuarioRepo;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     *  Iniciar sesión y obtener token JWT
     */
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> body) {
        Authentication authentication = authManager.authenticate(
                new UsernamePasswordAuthenticationToken(body.get("correo"), body.get("contrasena"))
        );

        UserDetails user = (UserDetails) authentication.getPrincipal();
        String token = jwtUtil.generateToken(user.getUsername());
        return ResponseEntity.ok(Map.of("token", token));
    }

    /**
     *  Registrar un nuevo usuario (automáticamente cifrado)
     */
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody Usuario usuario) {
        if (usuarioRepo.findByCorreo(usuario.getCorreo()).isPresent()) {
            return ResponseEntity.badRequest().body(Map.of("error", "El correo ya está registrado"));
        }

        usuario.setContrasena(passwordEncoder.encode(usuario.getContrasena()));
        usuarioRepo.save(usuario);

        return ResponseEntity.ok(Map.of("mensaje", "Usuario registrado correctamente"));
    }
}

