package proyecto_func.service.impl;

import jakarta.servlet.http.HttpSession;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import proyecto_func.dao.RolDao;
import proyecto_func.dao.UsuarioDao;
import proyecto_func.domain.Rol;
import proyecto_func.domain.Usuario;
import proyecto_func.service.UsuarioService;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioDao usuarioDao;
    @Autowired
    private RolDao rolDao;

    @Override
    @Transactional(readOnly = true)
    public List<Usuario> getUsuarios() {
        var lista = usuarioDao.findAll();
        return lista;
    }

    @Override
    @Transactional(readOnly = true)//No altera la base de datos solo trae los datos
    public Usuario getUsuario(Usuario usuario) {
        return usuarioDao.findById(usuario.getIdUsuario()).orElse(null);
    }

    @Override
    @Transactional //Si modifica la base de datos
    public void save(Usuario usuario) {
        if (usuario.getFechaCreacion()==null) {
           usuario.setFechaCreacion(LocalDate.now()); 
        }
        usuarioDao.save(usuario);
    }

    @Override
    @Transactional //Si modifica la base de datos
    public void delete(Usuario usuario) {
        usuarioDao.delete(usuario);
    }

    @Override
    public List<Usuario> consultaNativo(String nombreFiltro) {
        return usuarioDao.consultaNativo(nombreFiltro);
    }

    @Override
    public Usuario byUsername(String username) {
        return usuarioDao.findByUsername(username);
    }
    
    

}
