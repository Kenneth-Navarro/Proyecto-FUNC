
package proyecto_func.service;

import proyecto_func.domain.Rol;


public interface RolService {
      public Rol getRol(Long idUsuario);
      public void save (Rol rol);
      public void delete (Rol rol);
}
