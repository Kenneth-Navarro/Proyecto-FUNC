
package proyecto_func.dao;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import proyecto_func.Domain.Donacion;
import proyecto_func.domain.Mascota;


public interface DonacionDao extends JpaRepository<Donacion, Long> {
    
     @Query(nativeQuery=true,
            value="SELECT * FROM donacion where donacion.monto BETWEEN :montoInf AND :montoSup ORDER BY donacion.monto ASC")
    public List<Donacion> consultaNativo(@Param("montoInf") double montoInf, @Param("montoSup") double montoSup); 
}
