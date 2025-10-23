package sv.edu.udb.aerolinea.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sv.edu.udb.aerolinea.entity.Tripulacion;
import sv.edu.udb.aerolinea.entity.Aerolinea;
import sv.edu.udb.aerolinea.repository.TripulacionRepository;
import sv.edu.udb.aerolinea.repository.AerolineaRepository;

import java.util.*;

/**
 * Controlador REST para gestionar tripulaciones.
 */
@RestController
@RequestMapping("/api/v1/tripulacion")
public class TripulacionController {

    private final TripulacionRepository repo;
    private final AerolineaRepository aerolineaRepo;

    public TripulacionController(TripulacionRepository repo, AerolineaRepository aerolineaRepo) {
        this.repo = repo;
        this.aerolineaRepo = aerolineaRepo;
    }

    // Listar todos los tripulantes
    @GetMapping
    public ResponseEntity<List<Tripulacion>> listar() {
        return ResponseEntity.ok(repo.findAll());
    }

    // Obtener un tripulante por ID
    @GetMapping("/{id}")
    public ResponseEntity<?> obtener(@PathVariable Integer id) {
        return repo.findById(id)
                .<ResponseEntity<?>>map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(Map.of("error", "Tripulante no encontrado")));
    }

    // Crear un nuevo tripulante
    @PostMapping
    public ResponseEntity<?> crear(@RequestBody Map<String, Object> body) {
        try {
            Integer idaerolinea = (Integer) body.get("idaerolinea");
            Optional<Aerolinea> aerolineaOpt = aerolineaRepo.findById(idaerolinea);

            if (aerolineaOpt.isEmpty()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(Map.of("error", "La aerolínea especificada no existe"));
            }

            Tripulacion trip = new Tripulacion();
            trip.setNombre((String) body.get("nombre"));
            trip.setRol((String) body.get("rol"));
            trip.setAerolinea(aerolineaOpt.get());

            return ResponseEntity.status(HttpStatus.CREATED).body(repo.save(trip));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Map.of("error", "Error al crear tripulante", "detalle", e.getMessage()));
        }
    }

    // Actualizar datos
    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@PathVariable Integer id, @RequestBody Tripulacion datos) {
        Optional<Tripulacion> tripOpt = repo.findById(id);

        if (tripOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("error", "Tripulante no encontrado"));
        }

        Tripulacion existente = tripOpt.get();
        existente.setNombre(datos.getNombre());
        existente.setRol(datos.getRol());
        return ResponseEntity.ok(repo.save(existente));
    }

    //  Eliminar
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Integer id) {
        if (!repo.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("error", "Tripulante no encontrado"));
        }
        repo.deleteById(id);
        return ResponseEntity.ok(Map.of("mensaje", "Tripulante eliminado correctamente"));
    }
}
