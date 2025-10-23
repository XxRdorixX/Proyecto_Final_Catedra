package sv.edu.udb.aerolinea.security.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

/**
 * Clase de utilidad para generar y validar tokens JWT.
 */
@Component
public class JwtTokenUtil {

    // 🔐 Se genera una clave segura automáticamente si la del YAML es demasiado corta
    private final SecretKey secretKey;

    // Duración del token (en milisegundos)
    private final long expirationMs;

    public JwtTokenUtil(
            @Value("${jwt.secret}") String secret,
            @Value("${jwt.expiration}") long expirationMs
    ) {
        // Si el secreto del YAML es corto, se usa una clave segura por defecto
        if (secret == null || secret.length() < 64) {
            System.out.println("⚠️ Advertencia: Clave JWT demasiado corta, usando una generada automáticamente.");
            this.secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS512);
        } else {
            this.secretKey = Keys.hmacShaKeyFor(secret.getBytes());
        }
        this.expirationMs = expirationMs;
    }

    /**
     * Genera un token JWT con el correo del usuario.
     */
    public String generateToken(String correo) {
        return Jwts.builder()
                .setSubject(correo)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expirationMs))
                .signWith(secretKey, SignatureAlgorithm.HS512)
                .compact();
    }

    /**
     * Extrae el correo del token JWT.
     */
    public String getCorreoFromToken(String token) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(token)
                .getBody();
        return claims.getSubject();
    }

    /**
     * Valida si el token es correcto y no ha expirado.
     */
    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(secretKey).build().parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            System.err.println("Token inválido: " + e.getMessage());
            return false;
        }
    }
}
