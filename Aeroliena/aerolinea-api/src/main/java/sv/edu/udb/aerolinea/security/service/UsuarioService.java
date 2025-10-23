package sv.edu.udb.aerolinea.security.service;

import sv.edu.udb.aerolinea.entity.Usuario;
import java.util.Optional;
import java.util.List;

public interface UsuarioService {
    List<Usuario> findAll();
    Optional<Usuario> findByCorreo(String correo);
    Usuario save(Usuario usuario);
}

