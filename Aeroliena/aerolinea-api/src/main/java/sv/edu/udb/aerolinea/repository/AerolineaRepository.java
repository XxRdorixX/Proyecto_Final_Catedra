package sv.edu.udb.aerolinea.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import sv.edu.udb.aerolinea.entity.Aerolinea;

public interface AerolineaRepository extends JpaRepository<Aerolinea, Integer> {
}
