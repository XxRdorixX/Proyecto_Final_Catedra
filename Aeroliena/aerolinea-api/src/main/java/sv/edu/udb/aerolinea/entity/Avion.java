package sv.edu.udb.aerolinea.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

/**
 * Entidad que representa un avión.
 */
@Entity
@Data
@Table(name = "avion")
public class Avion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idavion;

    @Column(nullable = false, length = 50)
    private String modelo;

    @Column(nullable = false)
    private Integer capacidad;

    @ManyToOne
    @JoinColumn(name = "idaerolinea", nullable = false)
    @JsonBackReference // 👈 Evita recursión infinita
    private Aerolinea aerolinea;
}

