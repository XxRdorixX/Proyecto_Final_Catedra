package sv.edu.udb.aerolinea.service.impl;

import org.springframework.stereotype.Service;
import sv.edu.udb.aerolinea.entity.Usuario;
import sv.edu.udb.aerolinea.repository.UsuarioRepository;
import sv.edu.udb.aerolinea.security.service.UsuarioService;
import java.util.List;
import java.util.Optional;

/**
 * Lógica de negocio para los usuarios.
 */
@Service
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioServiceImpl(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public List<Usuario> findAll() {
        return usuarioRepository.findAll();
    }

    @Override
    public Optional<Usuario> findByCorreo(String correo) {
        return usuarioRepository.findByCorreo(correo);
    }

    @Override
    public Usuario save(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }
}
