package proyecto_func.controller;

import jakarta.mail.MessagingException;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import proyecto_func.Domain.Adopcion;
import proyecto_func.domain.Mascota;
import proyecto_func.service.AdopcionService;
import proyecto_func.service.MascotaService;

@Controller
@Slf4j
@RequestMapping("/adopcion")
public class AdopcionController {

    @Autowired //Sirve para crear autoinstancias
    private AdopcionService adopcionService;
    @Autowired //Sirve para crear autoinstancias
    private MascotaService mascotaService;

    @GetMapping("/listado")
    public String inicio(Model model) {
        var adopciones = adopcionService.getAdopciones();
        var mascotas = mascotaService.getMascotas(false);
        String currentURL = "http://localhost/adopcion/listado";
        model.addAttribute("adopciones", adopciones);
        model.addAttribute("mascotas", mascotas);
        model.addAttribute("currentURL", currentURL);
        return "/adopcion/listado";
    }

    @GetMapping("/nuevo")
    public String adopcionNuevo(Adopcion adopcion) {
        return "/adopcion/modifica";
    }

    @PostMapping("/guardar")
    public String adopcionGuardar(Adopcion adopcion) throws MessagingException{
        System.out.println(adopcion);
        adopcionService.save(adopcion);
        return "redirect:/adopcion/listado";
    }

    @GetMapping("/eliminar/{idAdopcion}")
    public String adopcionEliminar(Adopcion adopcion) {
        adopcionService.delete(adopcion);
        return "redirect:/adopcion/listado";
    }

    @GetMapping("/modificar/{idAdopcion}")
    public String adopcionModificar(Adopcion adopcion, Model model) {
        adopcion = adopcionService.getAdopcion(adopcion);
        var mascota = mascotaService.getMascota(adopcion.getMascota());
        var mascotas = mascotaService.getMascotas(false);
        model.addAttribute("adopcion", adopcion);
        model.addAttribute("mascotas", mascotas);
        model.addAttribute("mascota", mascota);
        return "/adopcion/modifica";
    }

    @GetMapping("/ver/{idAdopcion}")
    public String adopcionVer(Adopcion adopcion, Model model) {
        adopcion = adopcionService.getAdopcion(adopcion);
        model.addAttribute("adopcion", adopcion);
        return "/adopcion/ver";
    }

    @PostMapping("/consulta")
    public String consultaQuery3(@RequestParam(value = "cedulaFiltro") int cedulaFiltro, Model model) {
        var adopciones = adopcionService.consultaNativo(cedulaFiltro);
        String currentURL = "http://localhost/adopcion/listado";
        model.addAttribute("adopciones", adopciones);
        model.addAttribute("cedulaFiltro", cedulaFiltro);
        model.addAttribute("currentURL", currentURL);
        return "/adopcion/listado";
    }

    @GetMapping("/vistaAdopcion")
    public String vistaUsuario(Model model) {
        var mascotas = mascotaService.getMascotas(false);
        model.addAttribute("mascotas", mascotas);
        return "/adopcion/vistaAdopcion";
    }
  
    
    @GetMapping("/vistaAdopcion_1/{idMascota}")
    public String vistaUsuario_1(Mascota mascota, Model model) {
        mascota = mascotaService.getMascota(mascota);
        model.addAttribute("mascota", mascota);
      return "/adopcion/vistaAdopcion_1";
    }
     @PostMapping("/guardar2")
    public String adopcionGuardar2(Adopcion adopcion) throws MessagingException{
        adopcionService.save(adopcion);
        return "redirect:/adopcion/vistaAdopcion";
    }
}
