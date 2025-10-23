package sv.edu.udb.aerolinea.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sv.edu.udb.aerolinea.entity.Tripulacion;

public interface TripulacionRepository extends JpaRepository<Tripulacion, Integer> {
}
