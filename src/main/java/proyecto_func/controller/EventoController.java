package proyecto_func.controller;

import java.sql.Date;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import proyecto_func.domain.Evento;
import proyecto_func.service.FirebaseStorageService;
import proyecto_func.service.EventoService;

@Controller
@Slf4j
@RequestMapping("/evento")
public class EventoController {

    @Autowired //Sirve para crear autoinstancias
    private EventoService eventoService;

    @GetMapping("/listado")
    public String inicio(Model model) {
        var eventos = eventoService.getEventos();
        String currentURL = "http://localhost/evento/listado";
        model.addAttribute("eventos", eventos);
        model.addAttribute("totalEventos", eventos.size());
        model.addAttribute("currentURL", currentURL);
        return "/evento/listado";
    }
     @GetMapping("/vistaEvento")
    public String vista_usuario(Model model) {
        var eventos = eventoService.getEventos();
        model.addAttribute("eventos", eventos);
        return "/evento/vistaEvento";
    }

    @GetMapping("/nuevo")
    public String eventoNuevo(Evento evento) {
        return "/evento/modifica";
    }

    @Autowired
    private FirebaseStorageService firebaseStorageService;

    @PostMapping("/guardar")
    public String eventoGuardar(Evento evento,
            @RequestParam("imagenFile") MultipartFile imagenFile) {
        if (!imagenFile.isEmpty()) {
            eventoService.save(evento);
            evento.setRutaImagen(
                    firebaseStorageService.cargaImagen(
                            imagenFile,
                            "evento",
                            evento.getIdEvento()));
        }
        eventoService.save(evento);
        return "redirect:/evento/listado";
    }

    @GetMapping("/eliminar/{idEvento}")
    public String eventoEliminar(Evento evento) {
        eventoService.delete(evento);
        return "redirect:/evento/listado";
    }

    @GetMapping("/modificar/{idEvento}")
    public String eventoModificar(Evento evento, Model model) {
        evento = eventoService.getEvento(evento);
        model.addAttribute("evento", evento);
        return "/evento/modifica";
    }

    @GetMapping("/ver/{idEvento}")
    public String eventoVer(Evento evento, Model model) {
        evento = eventoService.getEvento(evento);
        model.addAttribute("evento", evento);
        return "/evento/ver";
    }
    
    @PostMapping("/consulta")
    public String consultaQuery3(@RequestParam(value = "fechaInf") Date fechaInf,
            @RequestParam(value = "fechaSup") Date fechaSup, Model model) {
        var eventos = eventoService.consultaNativo(fechaInf, fechaSup);
        String currentURL = "http://localhost/evento/listado";
        model.addAttribute("eventos", eventos);
        model.addAttribute("fechaInf", fechaInf);
        model.addAttribute("fechaSup", fechaSup);
        model.addAttribute("currentURL", currentURL);
        return "/evento/listado";
    }
    
    
    
}
