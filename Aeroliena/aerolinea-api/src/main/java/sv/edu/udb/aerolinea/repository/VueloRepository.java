package sv.edu.udb.aerolinea.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sv.edu.udb.aerolinea.entity.Vuelo;
import java.time.LocalDate;
import java.util.List;

public interface VueloRepository extends JpaRepository<Vuelo, Integer> {
    List<Vuelo> findByRuta_CiudadorigenAndRuta_CiudaddestinoAndFechasalida(
            String origen, String destino, LocalDate fecha);
}
