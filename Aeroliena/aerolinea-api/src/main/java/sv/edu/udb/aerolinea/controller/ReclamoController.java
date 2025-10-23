package sv.edu.udb.aerolinea.controller;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import sv.edu.udb.aerolinea.entity.Reclamo;
import sv.edu.udb.aerolinea.repository.ReclamoRepository;

import java.util.*;

/**
 * Controlador para manejar reclamos de los pasajeros.
 */
@RestController
@RequestMapping("/api/v1/reclamos")
public class ReclamoController {

    private final ReclamoRepository repo;

    public ReclamoController(ReclamoRepository repo) {
        this.repo = repo;
    }

    //  Listar todos los reclamos
    @GetMapping
    public ResponseEntity<List<Reclamo>> listar() {
        return ResponseEntity.ok(repo.findAll());
    }

    //  Obtener reclamo por ID
    @GetMapping("/{id}")
    public ResponseEntity<?> obtener(@PathVariable Integer id) {
        Optional<Reclamo> reclamo = repo.findById(id);
        return reclamo.<ResponseEntity<?>>map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(Map.of("error", "Reclamo no encontrado")));
    }

    //  Crear nuevo reclamo
    @PostMapping
    public ResponseEntity<Reclamo> crear(@RequestBody Reclamo reclamo) {
        return ResponseEntity.status(HttpStatus.CREATED).body(repo.save(reclamo));
    }

    //  Modificar reclamo (descripcion y estado)
    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@PathVariable Integer id, @RequestBody Reclamo reclamo) {
        Optional<Reclamo> opt = repo.findById(id);
        if (opt.isPresent()) {
            Reclamo existente = opt.get();
            existente.setDescripcion(reclamo.getDescripcion());
            existente.setEstado(reclamo.getEstado());
            repo.save(existente);
            return ResponseEntity.ok(existente);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("error", "Reclamo no encontrado"));
        }
    }

    // Resolver reclamo directamente
    @PutMapping("/{id}/resolver")
    public ResponseEntity<?> resolver(@PathVariable Integer id) {
        Optional<Reclamo> opt = repo.findById(id);
        if (opt.isPresent()) {
            Reclamo existente = opt.get();
            existente.setEstado("resuelto");
            repo.save(existente);
            return ResponseEntity.ok(Map.of("mensaje", "Reclamo marcado como resuelto", "reclamo", existente));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("error", "Reclamo no encontrado"));
        }
    }

    //  Eliminar reclamo
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Integer id) {
        if (!repo.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("error", "Reclamo no encontrado"));
        }
        repo.deleteById(id);
        return ResponseEntity.ok(Map.of("mensaje", "Reclamo eliminado correctamente"));
    }
}
