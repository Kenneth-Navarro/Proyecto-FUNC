
package proyecto_func.service;


import java.util.List;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import proyecto_func.domain.Usuario;

public interface UsuarioService {


    public List<Usuario> getUsuarios();

    public Usuario getUsuario(Usuario usuario);


    public void save(Usuario usuario);


    public void delete(Usuario usuario);
    
    public List<Usuario> consultaNativo(String nombreFiltro);
    
    public Usuario byUsername(String username);
    
    
}
