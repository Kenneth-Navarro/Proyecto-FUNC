
package proyecto_func.dao;

import java.sql.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import proyecto_func.domain.Evento;



public interface EventoDao extends JpaRepository<Evento, Long>{
    
    
    @Query(nativeQuery=true,
            value="SELECT * FROM evento where evento.fecha BETWEEN :fechaInf AND :fechaSup ORDER BY evento.fecha ASC")
    public List<Evento> consultaNativo(@Param("fechaInf") Date fechaInf, @Param("fechaSup") Date fechaSup); 
}