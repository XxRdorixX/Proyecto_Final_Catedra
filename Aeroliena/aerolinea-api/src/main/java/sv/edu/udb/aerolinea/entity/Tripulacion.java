package sv.edu.udb.aerolinea.entity;

import jakarta.persistence.*;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonBackReference;

/**
 * Entidad que representa a un miembro de la tripulación.
 */
@Entity
@Data
@Table(name = "tripulacion")
public class Tripulacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idtripulante;

    @Column(nullable = false, length = 100)
    private String nombre;

    @Column(nullable = false, length = 50)
    private String rol; // piloto, copiloto, azafata, etc

    @ManyToOne
    @JoinColumn(name = "idaerolinea", nullable = false)
    @JsonBackReference
    private Aerolinea aerolinea;
}
