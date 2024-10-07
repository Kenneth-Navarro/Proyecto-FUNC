package proyecto_func.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import proyecto_func.domain.Rol;
import proyecto_func.domain.Usuario;
import proyecto_func.service.FirebaseStorageService;
import proyecto_func.service.RolService;
import proyecto_func.service.UsuarioService;

@Controller
@Slf4j
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired //Sirve para crear autoinstancias
    private UsuarioService usuarioService;
    @Autowired
    private RolService rolService;

    @GetMapping("/listado")
    public String inicio(Model model) {
        var usuarios = usuarioService.getUsuarios();
        String currentURL = "http://localhost/usuario/listado";
        model.addAttribute("usuarios", usuarios);
        model.addAttribute("totalUsuarios", usuarios.size());
        model.addAttribute("currentURL", currentURL);
        return "/usuario/listado";
    }

    @PostMapping("/nuevo")
    public String usuarioNuevo(Usuario usuario, String nombreRol, Model model) {
        Usuario usuarioVerificar = usuarioService.byUsername(usuario.getUsername());

        if (usuarioVerificar != null) {
            
            model.addAttribute("usuario",usuario);
            return "/usuario/existente";
        } else {
            
            var codigo = new BCryptPasswordEncoder();
            usuario.setPassword(codigo.encode(usuario.getPassword()));
            usuarioService.save(usuario);
            Rol rol = new Rol();
            rol.setNombre(nombreRol);
            rol.setIdUsuario(usuario.getIdUsuario());

            rolService.save(rol);

            return "redirect:/usuario/listado";
        }
    }

    @Autowired
    private FirebaseStorageService firebaseStorageService;

    @PostMapping("/guardar")
    public String usuarioGuardar(Usuario usuario, String nombreRol) {

        usuarioService.save(usuario);
        Rol rol = rolService.getRol(usuario.getIdUsuario());
        rol.setNombre(nombreRol);

        rolService.save(rol);

        return "redirect:/usuario/listado";
    }

    @GetMapping("/eliminar/{idUsuario}")
    public String usuarioEliminar(Usuario usuario) {
        Rol rol = rolService.getRol(usuario.getIdUsuario());
        rolService.delete(rol);
        usuarioService.delete(usuario);
        return "redirect:/usuario/listado";
    }

    @GetMapping("/modificar/{idUsuario}")
    public String usuarioModificar(Usuario usuario, String nombreRol, Model model) {
        usuario = usuarioService.getUsuario(usuario);

        Rol rol = rolService.getRol(usuario.getIdUsuario());
        model.addAttribute("usuario", usuario);
        model.addAttribute("nombreRol", rol.getNombre());
        return "/usuario/modifica";
    }

    @GetMapping("/ver/{idUsuario}")
    public String usuarioVer(Usuario usuario, Model model) {
        usuario = usuarioService.getUsuario(usuario);
        model.addAttribute("usuario", usuario);
        return "/usuario/ver";
    }

    @PostMapping("/consulta")
    public String consultaQuery3(@RequestParam(value = "nombreFiltro") String nombreFiltro,
            Model model) {
        var usuarios = usuarioService.consultaNativo(nombreFiltro);
        String currentURL = "http://localhost/usuario/listado";
        model.addAttribute("usuarios", usuarios);
        model.addAttribute("currentURL", currentURL);
        return "/usuario/listado";
    }

    @GetMapping("/restablecerPass/{idUsuario}")
    public String restablecer(Usuario usuario, Model model) {
        usuario = usuarioService.getUsuario(usuario);
        model.addAttribute("usuario", usuario);
        return "/usuario/restablecerPass";
    }

    @PostMapping("/cambiar")
    public String cambiar(Usuario usuario) {

        String pass = usuario.getPassword();
        usuario = usuarioService.getUsuario(usuario);
        usuario.setPassword(pass);
        usuarioService.save(usuario);
        var codigo = new BCryptPasswordEncoder();
        usuario.setPassword(codigo.encode(usuario.getPassword()));
        usuarioService.save(usuario);

        return "redirect:/usuario/listado";
    }
}
