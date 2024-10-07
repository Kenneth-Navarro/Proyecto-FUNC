package proyecto_func.controller;

import jakarta.mail.MessagingException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import proyecto_func.domain.Mensaje;
import proyecto_func.domain.Mensaje;
import proyecto_func.service.MensajeService;

@Controller
@Slf4j
@RequestMapping("/mensaje")
public class MensajeController {

    @Autowired //Sirve para crear autoinstancias
    private MensajeService mensajeService;

    @GetMapping("/listado")
    public String inicio(Model model) {
        var mensajes = mensajeService.getMensajes();
        String currentURL = "http://localhost/mensaje/listado";
        model.addAttribute("mensajes", mensajes);
        model.addAttribute("totalMensajes", mensajes.size());
        model.addAttribute("currentURL", currentURL);
        return "/mensaje/listado";
    }
    
    @PostMapping("/guardar")
    public String mensajeGuardar(Mensaje mensaje) throws MessagingException{
        mensajeService.save(mensaje);
        return "redirect:/mensaje/contactanos";
    }

    @GetMapping("/eliminar/{idMensaje}")
    public String mensajeEliminar(Mensaje mensaje) {
        mensajeService.delete(mensaje);
        return "redirect:/mensaje/listado";
    }

    @GetMapping("/ver/{idMensaje}")
    public String mensajeVer(Mensaje mensaje, Model model) {
        mensaje = mensajeService.getMensaje(mensaje);
        model.addAttribute("mensaje", mensaje);
        return "/mensaje/ver";
    }
    
    @PostMapping("/consulta")
    public String consultaQuery3(@RequestParam(value="nombreFiltro") String nombreFiltro, Model model){
        var mensajes = mensajeService.consultaNativo(nombreFiltro);
        String currentURL = "http://localhost/mensaje/listado";
        model.addAttribute("mensajes", mensajes);
        model.addAttribute("nombreFiltro", nombreFiltro);
        model.addAttribute("currentURL", currentURL);
        return "/mensaje/listado";
        
    }
    
    @GetMapping("/contactanos")
    public String contactanos(){
        return "/mensaje/contactanos";
    }
    
    
}
