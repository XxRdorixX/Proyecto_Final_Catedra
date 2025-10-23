package sv.edu.udb.aerolinea.controller;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import sv.edu.udb.aerolinea.entity.*;
import sv.edu.udb.aerolinea.repository.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

/**
 * Controlador REST para gestión de vuelos.
 */
@RestController
@RequestMapping("/api/v1/vuelos")
public class VueloController {

    private final VueloRepository repo;
    private final AvionRepository avionRepo;
    private final RutaRepository rutaRepo;

    public VueloController(VueloRepository repo, AvionRepository avionRepo, RutaRepository rutaRepo) {
        this.repo = repo;
        this.avionRepo = avionRepo;
        this.rutaRepo = rutaRepo;
    }

    @GetMapping
    public ResponseEntity<List<Vuelo>> listar() {
        return ResponseEntity.ok(repo.findAll());
    }

    @PostMapping
    public ResponseEntity<?> crear(@RequestBody Map<String, Object> body) {
        try {
            Integer idruta = (Integer) body.get("idruta");
            Integer idavion = (Integer) body.get("idavion");

            Optional<Ruta> rutaOpt = rutaRepo.findById(idruta);
            Optional<Avion> avionOpt = avionRepo.findById(idavion);

            if (rutaOpt.isEmpty() || avionOpt.isEmpty()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(Map.of("error", "Ruta o avión no válidos"));
            }

            Vuelo vuelo = new Vuelo();
            vuelo.setRuta(rutaOpt.get());
            vuelo.setAvion(avionOpt.get());
            vuelo.setFechasalida(LocalDate.parse((String) body.get("fechasalida")));
            vuelo.setHorasalida(LocalTime.parse((String) body.get("horasalida")));
            vuelo.setTarifa(new BigDecimal(body.get("tarifa").toString()));

            return ResponseEntity.status(HttpStatus.CREATED).body(repo.save(vuelo));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Map.of("error", "Error al crear vuelo", "detalle", e.getMessage()));
        }
    }
}
