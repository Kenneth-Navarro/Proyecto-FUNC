
package proyecto_func.dao;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import proyecto_func.domain.Usuario;



public interface UsuarioDao extends JpaRepository<Usuario, Long>{
    
    @Query(nativeQuery=true,
            value="SELECT * FROM usuario where usuario.username LIKE :nombreFiltro% ORDER BY usuario.username ASC")
    public List<Usuario> consultaNativo(@Param("nombreFiltro") String nombreFiltro); 
    
     Usuario findByUsername(String username);
}