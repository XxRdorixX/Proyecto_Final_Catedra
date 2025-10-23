package sv.edu.udb.aerolinea.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sv.edu.udb.aerolinea.entity.Pago;
import sv.edu.udb.aerolinea.repository.PagoRepository;
import sv.edu.udb.aerolinea.repository.ReservacionRepository;

import java.util.List;
import java.util.Map;

/**
 * Controlador REST para gestionar los pagos de las reservaciones.
 */
@RestController
@RequestMapping("/api/v1/pagos")
public class PagoController {

    private final PagoRepository pagoRepository;
    private final ReservacionRepository reservacionRepository;

    public PagoController(PagoRepository pagoRepository, ReservacionRepository reservacionRepository) {
        this.pagoRepository = pagoRepository;
        this.reservacionRepository = reservacionRepository;
    }

    //  Listar todos los pagos
    @GetMapping
    public ResponseEntity<List<Pago>> listarPagos() {
        return ResponseEntity.ok(pagoRepository.findAll());
    }

    //  Buscar un pago por ID
    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerPagoPorId(@PathVariable Integer id) {
        return pagoRepository.findById(id)
                .map(pago -> ResponseEntity.ok((Object) pago))
                .orElseGet(() -> ResponseEntity.status(404).body(Map.of("error", "Pago no encontrado")));
    }

    // Crear nuevo pago
    @PostMapping
    public ResponseEntity<?> crearPago(@RequestBody Pago pago) {
        if (pago.getReservacion() == null || pago.getReservacion().getIdreservacion() == null) {
            return ResponseEntity.badRequest().body(Map.of("error", "Debe asociar una reservación válida"));
        }

        var reservacion = reservacionRepository.findById(pago.getReservacion().getIdreservacion()).orElse(null);
        if (reservacion == null) {
            return ResponseEntity.status(404).body(Map.of("error", "Reservación no encontrada"));
        }

        pago.setReservacion(reservacion);
        Pago nuevoPago = pagoRepository.save(pago);
        return ResponseEntity.ok(nuevoPago);
    }

    // Actualizar un pago existente
    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarPago(@PathVariable Integer id, @RequestBody Pago detalles) {
        return pagoRepository.findById(id)
                .map(pago -> {
                    pago.setMetodopago(detalles.getMetodopago());
                    pago.setMonto(detalles.getMonto());
                    Pago actualizado = pagoRepository.save(pago);
                    return ResponseEntity.ok((Object) actualizado);
                })
                .orElseGet(() -> ResponseEntity.status(404).body(Map.of("error", "Pago no encontrado")));
    }

    //  Eliminar un pago
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarPago(@PathVariable Integer id) {
        return pagoRepository.findById(id)
                .map(pago -> {
                    pagoRepository.delete(pago);
                    return ResponseEntity.ok(Map.of("mensaje", "Pago eliminado correctamente"));
                })
                .orElseGet(() -> ResponseEntity.status(404).body(Map.of("error", "Pago no encontrado")));
    }
}
