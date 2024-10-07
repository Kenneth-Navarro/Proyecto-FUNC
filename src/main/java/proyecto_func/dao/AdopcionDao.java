package proyecto_func.dao;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import proyecto_func.Domain.Adopcion;
import proyecto_func.domain.Mascota;


public interface AdopcionDao extends JpaRepository<Adopcion, Long> {
    
    @Query(nativeQuery=true,
            value="SELECT * FROM adopcion where adopcion.cedula LIKE :cedulaFiltro% ORDER BY adopcion.cedula ASC")
    public List<Adopcion> consultaNativo(@Param("cedulaFiltro") int cedulaFiltro); 
    
}