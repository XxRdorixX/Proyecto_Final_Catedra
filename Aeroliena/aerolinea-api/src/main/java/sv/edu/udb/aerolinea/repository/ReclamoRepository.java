package sv.edu.udb.aerolinea.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sv.edu.udb.aerolinea.entity.Reclamo;

public interface ReclamoRepository extends JpaRepository<Reclamo, Integer> {
}
