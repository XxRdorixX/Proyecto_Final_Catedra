package sv.edu.udb.aerolinea.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sv.edu.udb.aerolinea.entity.Avion;

public interface AvionRepository extends JpaRepository<Avion, Integer> {
}

