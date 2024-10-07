
package proyecto_func.dao;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import proyecto_func.domain.Mascota;


public interface MascotaDao extends JpaRepository<Mascota, Long>{
    
    @Query(nativeQuery=true,
            value="SELECT * FROM mascota where mascota.ano_rescate BETWEEN :anoInf AND :anoSup ORDER BY mascota.ano_rescate ASC")
    public List<Mascota> consultaNativo(@Param("anoInf") int anoInf, @Param("anoSup") int anoSup); 
}
