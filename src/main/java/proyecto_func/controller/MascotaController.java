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
import proyecto_func.domain.Mascota;
import proyecto_func.service.FirebaseStorageService;
import proyecto_func.service.MascotaService;



@Controller
@Slf4j
@RequestMapping("/mascota")
public class MascotaController {

    @Autowired //Sirve para crear autoinstancias
    private MascotaService mascotaService;
 

    @GetMapping("/listado")
    public String inicio(Model model) {
        var mascotas = mascotaService.getMascotas(false);
        String currentURL = "http://localhost/mascota/listado";
        model.addAttribute("mascotas", mascotas);
        model.addAttribute("totalMascotas", mascotas.size());
        model.addAttribute("currentURL", currentURL);
        return "/mascota/listado";
    }

    @GetMapping("/nuevo")
    public String mascotaNuevo(Mascota mascota) {
        return "/mascota/modifica";
    }

    @Autowired
    private FirebaseStorageService firebaseStorageService;

    @PostMapping("/guardar")
    public String mascotaGuardar(Mascota mascota,
            @RequestParam("imagenFile") MultipartFile imagenFile) {
        if (!imagenFile.isEmpty()) {
            mascotaService.save(mascota);
            mascota.setRutaImagen(
                    firebaseStorageService.cargaImagen(
                            imagenFile,
                            "mascota",
                            mascota.getIdMascota()));
        }
        
        mascotaService.save(mascota);
        return "redirect:/mascota/listado";
    }

    @GetMapping("/eliminar/{idMascota}")
    public String mascotaEliminar(Mascota mascota) {
        mascotaService.delete(mascota);
        return "redirect:/mascota/listado";
    }

    @GetMapping("/modificar/{idMascota}")
    public String mascotaModificar(Mascota mascota, Model model) {
        mascota = mascotaService.getMascota(mascota);
        model.addAttribute("mascota", mascota);
        return "/mascota/modifica";
    }
    
    
     @GetMapping("/ver/{idMascota}")
    public String mascotaVer(Mascota mascota, Model model) {
        mascota = mascotaService.getMascota(mascota);
        model.addAttribute("mascota", mascota);
        return "/mascota/ver";
    }
    
    @PostMapping("/consulta")
    public String consultaQuery3(@RequestParam(value = "anoInf") int anoInf,
            @RequestParam(value = "anoSup") int anoSup, Model model) {
        var mascotas = mascotaService.consultaNativo(anoInf, anoSup);
        String currentURL = "http://localhost/mascota/listado";
        model.addAttribute("mascotas", mascotas);
        model.addAttribute("anoInf", anoInf);
        model.addAttribute("anoSup", anoSup);
        model.addAttribute("currentURL", currentURL);
        return "/mascota/listado";
    }
   
    
    
    

}
