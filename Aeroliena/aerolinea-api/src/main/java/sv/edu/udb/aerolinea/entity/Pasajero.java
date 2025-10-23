package sv.edu.udb.aerolinea.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;

/**
 * Representa un pasajero con posible relación a un usuario.
 */
@Data
@Entity
@Table(name = "pasajero")
public class Pasajero {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idpasajero;

    @Column(nullable = false, length = 100)
    private String nombrecompleto;

    @Column(nullable = false)
    private LocalDate fechanacimiento;

    @Column(nullable = false, unique = true, length = 20)
    private String pasaporte;

    private String preferenciaasiento;

    @OneToOne
    @JoinColumn(name = "idusuario", unique = true)
    private Usuario usuario;
}

