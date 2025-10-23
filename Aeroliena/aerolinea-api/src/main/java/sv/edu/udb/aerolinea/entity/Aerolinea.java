package sv.edu.udb.aerolinea.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

/**
 * Entidad que representa una aerolínea.
 */
@Entity
@Data
@Table(name = "aerolinea")
public class Aerolinea {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idaerolinea;

    @Column(nullable = false, length = 100)
    private String nombre;

    @Column(nullable = false, length = 50)
    private String pais;

    @OneToMany(mappedBy = "aerolinea", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference // 👈 Evita recursión
    private List<Avion> aviones;
}
