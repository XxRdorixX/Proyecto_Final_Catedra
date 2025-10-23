package sv.edu.udb.aerolinea.controller;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import sv.edu.udb.aerolinea.entity.Pasajero;
import sv.edu.udb.aerolinea.repository.PasajeroRepository;
import java.util.*;

/**
 * Controlador para la gestión de pasajeros.
 */
@RestController
@RequestMapping("/api/v1/pasajeros")
public class PasajeroController {

    private final PasajeroRepository repo;

    public PasajeroController(PasajeroRepository repo) {
        this.repo = repo;
    }

    //  Listar todos los pasajeros
    @GetMapping
    public ResponseEntity<List<Pasajero>> listar() {
        return ResponseEntity.ok(repo.findAll());
    }

    //  Obtener pasajero por ID
    @GetMapping("/{id}")
    public ResponseEntity<?> obtener(@PathVariable Integer id) {
        Optional<Pasajero> pasajero = repo.findById(id);
        if (pasajero.isPresent()) {
            return ResponseEntity.ok(pasajero.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("error", "Pasajero no encontrado"));
        }
    }

    //  Crear nuevo pasajero
    @PostMapping
    public ResponseEntity<Pasajero> crear(@RequestBody Pasajero pasajero) {
        return ResponseEntity.status(HttpStatus.CREATED).body(repo.save(pasajero));
    }

    //  Actualizar pasajero
    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@PathVariable Integer id, @RequestBody Pasajero pasajero) {
        Optional<Pasajero> opt = repo.findById(id);
        if (opt.isPresent()) {
            Pasajero existente = opt.get();
            existente.setNombrecompleto(pasajero.getNombrecompleto());
            existente.setFechanacimiento(pasajero.getFechanacimiento());
            existente.setPasaporte(pasajero.getPasaporte());
            existente.setPreferenciaasiento(pasajero.getPreferenciaasiento());
            repo.save(existente);
            return ResponseEntity.ok(existente);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("error", "Pasajero no encontrado"));
        }
    }

    //  Eliminar pasajero
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Integer id) {
        if (!repo.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("error", "No existe ese pasajero"));
        }
        repo.deleteById(id);
        return ResponseEntity.ok(Map.of("mensaje", "Pasajero eliminado correctamente"));
    }
}
