package sv.edu.udb.aerolinea.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import sv.edu.udb.aerolinea.entity.Usuario;
import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    Optional<Usuario> findByCorreo(String correo);
    boolean existsByCorreo(String correo);
}
