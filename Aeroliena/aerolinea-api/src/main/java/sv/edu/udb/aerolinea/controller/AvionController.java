package sv.edu.udb.aerolinea.controller;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import sv.edu.udb.aerolinea.entity.*;
import sv.edu.udb.aerolinea.repository.*;

import java.util.*;

/**
 * Controlador para la gestión de aviones.
 */
@RestController
@RequestMapping("/api/v1/aviones")
public class AvionController {

    private final AvionRepository avionRepo;
    private final AerolineaRepository aerolineaRepo;

    public AvionController(AvionRepository avionRepo, AerolineaRepository aerolineaRepo) {
        this.avionRepo = avionRepo;
        this.aerolineaRepo = aerolineaRepo;
    }

    @GetMapping
    public ResponseEntity<List<Avion>> listar() {
        return ResponseEntity.ok(avionRepo.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> obtener(@PathVariable Integer id) {
        Optional<Avion> avion = avionRepo.findById(id);
        if (avion.isPresent()) {
            return ResponseEntity.ok(avion.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("error", "Avión no encontrado"));
        }
    }

    @PostMapping
    public ResponseEntity<?> crear(@RequestBody Map<String, Object> body) {
        Integer idaerolinea = (Integer) body.get("idaerolinea");
        Optional<Aerolinea> aerolinea = aerolineaRepo.findById(idaerolinea);
        if (aerolinea.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Map.of("error", "La aerolínea especificada no existe"));
        }

        Avion avion = new Avion();
        avion.setModelo((String) body.get("modelo"));
        avion.setCapacidad((Integer) body.get("capacidad"));
        avion.setAerolinea(aerolinea.get());

        return ResponseEntity.status(HttpStatus.CREATED).body(avionRepo.save(avion));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@PathVariable Integer id, @RequestBody Map<String, Object> body) {
        Optional<Avion> opt = avionRepo.findById(id);
        if (opt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("error", "Avión no encontrado"));
        }

        Avion avion = opt.get();
        avion.setModelo((String) body.get("modelo"));
        avion.setCapacidad((Integer) body.get("capacidad"));
        return ResponseEntity.ok(avionRepo.save(avion));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Integer id) {
        if (!avionRepo.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("error", "El avión no existe"));
        }
        avionRepo.deleteById(id);
        return ResponseEntity.ok(Map.of("mensaje", "Avión eliminado correctamente"));
    }
}
