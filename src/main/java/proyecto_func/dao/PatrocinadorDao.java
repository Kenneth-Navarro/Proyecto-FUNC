
package proyecto_func.dao;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import proyecto_func.domain.Mensaje;
import proyecto_func.domain.Patrocinador;


public interface PatrocinadorDao extends JpaRepository<Patrocinador, Long>{
    
    @Query(nativeQuery=true,
            value="SELECT * FROM patrocinador where patrocinador.nombre LIKE :nombreFiltro% ORDER BY patrocinador.nombre ASC")
    public List<Patrocinador> consultaNativo(@Param("nombreFiltro") String nombreFiltro); 
}
