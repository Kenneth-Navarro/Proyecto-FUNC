package proyecto_func.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import proyecto_func.domain.Patrocinador;
import proyecto_func.service.FirebaseStorageService;
import proyecto_func.service.PatrocinadorService;

@Controller
@Slf4j
@RequestMapping("/patrocinador")
public class PatrocinadorController {

    @Autowired //Sirve para crear autoinstancias
    private PatrocinadorService patrocinadorService;

    @GetMapping("/listado")
    public String inicio(Model model) {
        var patrocinadores = patrocinadorService.getPatrocinadores();
        String currentURL = "http://localhost/patrocinador/listado";
        model.addAttribute("patrocinadores", patrocinadores);
        model.addAttribute("totalPatrocinadors", patrocinadores.size());
        model.addAttribute("currentURL", currentURL);
        return "/patrocinador/listado";
    }

    @GetMapping("/nuevo")
    public String patrocinadorNuevo(Patrocinador patrocinador) {
        return "/patrocinador/modifica";
    }

    @Autowired
    private FirebaseStorageService firebaseStorageService;

    @PostMapping("/guardar")
    public String patrocinadorGuardar(Patrocinador patrocinador,
            @RequestParam("imagenFile") MultipartFile imagenFile) {
        if (!imagenFile.isEmpty()) {
            patrocinadorService.save(patrocinador);
            patrocinador.setRutaImagen(
                    firebaseStorageService.cargaImagen(
                            imagenFile,
                            "patrocinador",
                            patrocinador.getIdPatrocinador()));
        }
        patrocinadorService.save(patrocinador);
        return "redirect:/patrocinador/listado";
    }

    @GetMapping("/eliminar/{idPatrocinador}")
    public String patrocinadorEliminar(Patrocinador patrocinador) {
        patrocinadorService.delete(patrocinador);
        return "redirect:/patrocinador/listado";
    }

    @GetMapping("/modificar/{idPatrocinador}")
    public String patrocinadorModificar(Patrocinador patrocinador, Model model) {
        patrocinador = patrocinadorService.getPatrocinador(patrocinador);
        model.addAttribute("patrocinador", patrocinador);
        return "/patrocinador/modifica";
    }

    @GetMapping("/ver/{idPatrocinador}")
    public String patrocinadorVer(Patrocinador patrocinador, Model model) {
        patrocinador = patrocinadorService.getPatrocinador(patrocinador);
        model.addAttribute("patrocinador", patrocinador);
        return "/patrocinador/ver";
    }

    @PostMapping("/consulta")
    public String consultaQuery3(@RequestParam(value = "nombreFiltro") String nombreFiltro, Model model) {
        var patrocinadores = patrocinadorService.consultaNativo(nombreFiltro);
        String currentURL = "http://localhost/patrocinador/listado";
        model.addAttribute("patrocinadores", patrocinadores);
        model.addAttribute("currentURL", currentURL);
        return "patrocinador/listado";

    }

}
