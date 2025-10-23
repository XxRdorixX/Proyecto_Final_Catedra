package sv.edu.udb.aerolinea.entity;

import jakarta.persistence.*;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonBackReference;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Entidad que representa un vuelo.
 */
@Entity
@Data
@Table(name = "vuelo")
public class Vuelo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idvuelo;

    @ManyToOne
    @JoinColumn(name = "idruta", nullable = false)
    private Ruta ruta;

    @ManyToOne
    @JoinColumn(name = "idavion", nullable = false)
    private Avion avion;

    @Column(nullable = false)
    private LocalDate fechasalida;

    @Column(nullable = false)
    private LocalTime horasalida;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal tarifa;
}
