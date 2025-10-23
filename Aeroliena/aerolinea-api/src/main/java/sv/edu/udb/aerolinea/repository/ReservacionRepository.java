package sv.edu.udb.aerolinea.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sv.edu.udb.aerolinea.entity.Reservacion;

public interface ReservacionRepository extends JpaRepository<Reservacion, Integer> {
}
