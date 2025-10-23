package sv.edu.udb.aerolinea.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sv.edu.udb.aerolinea.entity.Pasajero;

public interface PasajeroRepository extends JpaRepository<Pasajero, Integer> {
}
