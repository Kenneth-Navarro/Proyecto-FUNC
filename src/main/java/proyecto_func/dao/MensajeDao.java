
package proyecto_func.dao;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import proyecto_func.domain.Mascota;
import proyecto_func.domain.Mensaje;


public interface MensajeDao extends JpaRepository<Mensaje, Long>{
    @Query(nativeQuery=true,
            value="SELECT * FROM mensaje where mensaje.nombre LIKE :nombreFiltro% ORDER BY mensaje.nombre ASC")
    public List<Mensaje> consultaNativo(@Param("nombreFiltro") String nombreFiltro); 
}
