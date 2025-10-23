package sv.edu.udb.aerolinea.entity;


import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

/**
 * Representa la reserva de un pasajero a un vuelo específico.
 */
@Data
@Entity
@Table(name = "reservacion")
public class Reservacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idreservacion;

    @ManyToOne
    @JoinColumn(name = "idvuelo", nullable = false)
    private Vuelo vuelo;

    @ManyToOne
    @JoinColumn(name = "idpasajero", nullable = false)
    private Pasajero pasajero;

    @Column(nullable = false)
    private String estado;

    @Column(nullable = false)
    private LocalDateTime fechareserva = LocalDateTime.now();
}
