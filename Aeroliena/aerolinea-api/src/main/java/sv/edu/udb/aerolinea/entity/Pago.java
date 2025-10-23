package sv.edu.udb.aerolinea.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Entidad que representa los pagos de las reservaciones.
 */
@Entity
@Getter
@Setter
@Table(name = "pago")
public class Pago {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idpago;

    @ManyToOne
    @JoinColumn(name = "idreservacion", nullable = false)
    private Reservacion reservacion;

    @Column(nullable = false, length = 50)
    private String metodopago;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal monto;

    @Column(nullable = false)
    private LocalDateTime fechapago = LocalDateTime.now();
}
