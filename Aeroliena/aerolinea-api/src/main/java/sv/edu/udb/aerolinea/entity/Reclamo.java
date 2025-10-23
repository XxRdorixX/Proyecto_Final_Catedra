package sv.edu.udb.aerolinea.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

/**
 * Entidad que representa un reclamo realizado por un pasajero.
 */
@Entity
@Table(name = "reclamo")
public class Reclamo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idreclamo;

    @ManyToOne
    @JoinColumn(name = "idpasajero", nullable = false)
    private Pasajero pasajero;

    @Column(length = 500, nullable = false)
    private String descripcion;

    private LocalDateTime fecha = LocalDateTime.now();

    @Column(length = 20)
    private String estado = "pendiente";

    // 🔹 Getters y Setters
    public Integer getIdreclamo() {
        return idreclamo;
    }

    public void setIdreclamo(Integer idreclamo) {
        this.idreclamo = idreclamo;
    }

    public Pasajero getPasajero() {
        return pasajero;
    }

    public void setPasajero(Pasajero pasajero) {
        this.pasajero = pasajero;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
