package sv.edu.udb.aerolinea.controller;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import sv.edu.udb.aerolinea.entity.*;
import sv.edu.udb.aerolinea.repository.*;

import java.time.LocalDateTime;
import java.util.*;

/**
 * Controlador REST para gestionar reservaciones.
 */
@RestController
@RequestMapping("/api/v1/reservaciones")
public class ReservacionController {

    private final ReservacionRepository repo;
    private final VueloRepository vueloRepo;
    private final PasajeroRepository pasajeroRepo;

    public ReservacionController(ReservacionRepository repo, VueloRepository vueloRepo, PasajeroRepository pasajeroRepo) {
        this.repo = repo;
        this.vueloRepo = vueloRepo;
        this.pasajeroRepo = pasajeroRepo;
    }

    //  Listar todas las reservaciones
    @GetMapping
    public ResponseEntity<List<Reservacion>> listar() {
        return ResponseEntity.ok(repo.findAll());
    }

    //  Obtener una reservación por ID
    @GetMapping("/{id}")
    public ResponseEntity<?> obtener(@PathVariable Integer id) {
        return repo.findById(id)
                .<ResponseEntity<?>>map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(Map.of("error", "Reservación no encontrada")));
    }

    //  Crear una nueva reservación
    @PostMapping
    public ResponseEntity<?> crear(@RequestBody Map<String, Object> body) {
        try {
            Integer idvuelo = (Integer) body.get("idvuelo");
            Integer idpasajero = (Integer) body.get("idpasajero");

            Optional<Vuelo> vueloOpt = vueloRepo.findById(idvuelo);
            Optional<Pasajero> pasajeroOpt = pasajeroRepo.findById(idpasajero);

            if (vueloOpt.isEmpty() || pasajeroOpt.isEmpty()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(Map.of("error", "Vuelo o pasajero no válidos"));
            }

            Reservacion reservacion = new Reservacion();
            reservacion.setVuelo(vueloOpt.get());
            reservacion.setPasajero(pasajeroOpt.get());
            reservacion.setEstado((String) body.getOrDefault("estado", "pendiente"));
            reservacion.setFechareserva(LocalDateTime.now());

            return ResponseEntity.status(HttpStatus.CREATED).body(repo.save(reservacion));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Map.of("error", "Error al crear reservación", "detalle", e.getMessage()));
        }
    }

    // Actualizar estado de la reservación
    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@PathVariable Integer id, @RequestBody Map<String, String> body) {
        Optional<Reservacion> reservacionOpt = repo.findById(id);

        if (reservacionOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("error", "Reservación no encontrada"));
        }

        Reservacion reservacion = reservacionOpt.get();
        reservacion.setEstado(body.getOrDefault("estado", reservacion.getEstado()));

        return ResponseEntity.ok(repo.save(reservacion));
    }

    // Eliminar reservación
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Integer id) {
        if (!repo.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("error", "Reservación no encontrada"));
        }
        repo.deleteById(id);
        return ResponseEntity.ok(Map.of("mensaje", "Reservación eliminada correctamente"));
    }
}
