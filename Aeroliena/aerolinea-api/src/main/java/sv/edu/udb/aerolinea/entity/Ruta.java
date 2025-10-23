package sv.edu.udb.aerolinea.entity;


import jakarta.persistence.*;
import lombok.Data;

/**
 * Representa una ruta aérea entre dos ciudades.
 */
@Data
@Entity
@Table(name = "ruta")
public class Ruta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idruta;

    @Column(nullable = false, length = 100)
    private String ciudadorigen;

    @Column(nullable = false, length = 100)
    private String ciudaddestino;

    @Column(nullable = false)
    private Integer duracion; // minutos
}
