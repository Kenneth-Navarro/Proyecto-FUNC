
package proyecto_func.service.impl;

import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Collections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import proyecto_func.dao.UsuarioDao;
import proyecto_func.domain.Rol;
import proyecto_func.domain.Usuario;
import proyecto_func.service.UsuarioDetailsService;

@Service("userDetailsService")
public class UsuarioDetailsServiceImpl  implements UsuarioDetailsService, UserDetailsService {
    
    @Autowired
    private UsuarioDao usuarioDao;
  @Autowired
    private HttpSession session;

@Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        Buscar el usuario que pasamos por parametro
        Usuario usuario = usuarioDao.findByUsername(username);
        
        // si valida si se encontro usuario o no
        if (usuario == null) {
            throw new UsernameNotFoundException(username);
            
        }

        Rol rol = usuario.getRol();

        //se retorna un user de tipo userdetails
        return new User(usuario.getUsername(), usuario.getPassword(), Collections.singleton(new SimpleGrantedAuthority(rol.getNombre())));
    }
}
