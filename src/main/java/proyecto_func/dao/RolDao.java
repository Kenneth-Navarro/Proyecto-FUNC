package proyecto_func.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import proyecto_func.domain.Rol;

public interface RolDao extends JpaRepository<Rol, Long> {

    @Query(nativeQuery = true,
            value = "SELECT * FROM rol WHERE rol.id_Usuario = :idUsuario")
    public Rol consultaRol(@Param("idUsuario") Long idUsuario);
}
