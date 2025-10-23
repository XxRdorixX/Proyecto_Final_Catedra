package sv.edu.udb.aerolinea.controller;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import sv.edu.udb.aerolinea.entity.Ruta;
import sv.edu.udb.aerolinea.repository.RutaRepository;

import java.util.*;

/**
 * Controlador para la gestión de rutas.
 */
@RestController
@RequestMapping("/api/v1/rutas")
public class RutaController {

    private final RutaRepository repo;

    public RutaController(RutaRepository repo) {
        this.repo = repo;
    }

    @GetMapping
    public ResponseEntity<List<Ruta>> listar() {
        return ResponseEntity.ok(repo.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> obtener(@PathVariable Integer id) {
        Optional<Ruta> ruta = repo.findById(id);
        if (ruta.isPresent()) {
            return ResponseEntity.ok(ruta.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("error", "Ruta no encontrada"));
        }
    }

    @PostMapping
    public ResponseEntity<Ruta> crear(@RequestBody Ruta ruta) {
        return ResponseEntity.status(HttpStatus.CREATED).body(repo.save(ruta));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@PathVariable Integer id, @RequestBody Ruta ruta) {
        Optional<Ruta> opt = repo.findById(id);
        if (opt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("error", "Ruta no encontrada"));
        }

        Ruta existente = opt.get();
        // Usamos los nombres exactos según la BD (en minúsculas)
        existente.setCiudadorigen(ruta.getCiudadorigen());
        existente.setCiudaddestino(ruta.getCiudaddestino());
        existente.setDuracion(ruta.getDuracion());
        return ResponseEntity.ok(repo.save(existente));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Integer id) {
        if (!repo.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("error", "No existe esa ruta"));
        }
        repo.deleteById(id);
        return ResponseEntity.ok(Map.of("mensaje", "Ruta eliminada correctamente"));
    }
}
