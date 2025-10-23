package sv.edu.udb.aerolinea.entity;


import jakarta.persistence.*;
import lombok.Data;

/**
 * Representa a los usuarios del sistema (administrador, empleado, cliente).
 */
@Data
@Entity
@Table(name = "usuario")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idusuario;

    @Column(nullable = false, length = 100)
    private String nombrecompleto;

    @Column(nullable = false, unique = true, length = 100)
    private String correo;

    @Column(nullable = false, length = 200)
    private String contrasena;

    @Column(nullable = false, length = 50)
    private String rol;
}

