package sv.edu.udb.aerolinea.controller;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import sv.edu.udb.aerolinea.entity.Aerolinea;
import sv.edu.udb.aerolinea.repository.AerolineaRepository;
import java.util.*;

/**
 * Controlador para la gestión de Aerolíneas.
 */
@RestController
@RequestMapping("/api/v1/aerolineas")
public class AerolineaController {

    private final AerolineaRepository repo;

    public AerolineaController(AerolineaRepository repo) {
        this.repo = repo;
    }

    @GetMapping
    public ResponseEntity<List<Aerolinea>> listar() {
        return ResponseEntity.ok(repo.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> obtener(@PathVariable Integer id) {
        Optional<Aerolinea> aerolinea = repo.findById(id);
        if (aerolinea.isPresent()) {
            return ResponseEntity.ok(aerolinea.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("error", "Aerolínea no encontrada"));
        }
    }

    @PostMapping
    public ResponseEntity<Aerolinea> crear(@RequestBody Aerolinea aerolinea) {
        return ResponseEntity.status(HttpStatus.CREATED).body(repo.save(aerolinea));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@PathVariable Integer id, @RequestBody Aerolinea aerolinea) {
        Optional<Aerolinea> opt = repo.findById(id);
        if (opt.isPresent()) {
            Aerolinea existente = opt.get();
            existente.setNombre(aerolinea.getNombre());
            existente.setPais(aerolinea.getPais());
            repo.save(existente);
            return ResponseEntity.ok(existente);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("error", "Aerolínea no encontrada"));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Integer id) {
        if (!repo.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("error", "No existe esa aerolínea"));
        }
        repo.deleteById(id);
        return ResponseEntity.ok(Map.of("mensaje", "Aerolínea eliminada correctamente"));
    }
}
