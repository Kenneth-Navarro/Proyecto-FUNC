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
import proyecto_func.Domain.Donacion;
import proyecto_func.service.DonacionService;

@Controller
@Slf4j
@RequestMapping("/donacion")
public class DonacionController {

    @Autowired //Sirve para crear autoinstancias
    private DonacionService donacionService;

    @GetMapping("/listado")
    public String inicio(Model model) {
        var donaciones = donacionService.getDonaciones();
        String currentURL = "http://localhost/donacion/listado";
        model.addAttribute("donaciones", donaciones);
        model.addAttribute("currentURL", currentURL);
        return "/donacion/listado";
    }

    @GetMapping("/ver/{idDonacion}")
    public String donacionVer(Donacion donacion, Model model) {
        donacion = donacionService.getDonacion(donacion);
        model.addAttribute("donacion", donacion);
        return "/donacion/ver";
    }

    @PostMapping("/consulta")
    public String consultaQuery3(@RequestParam(value = "montoInf") double montoInf,
            @RequestParam(value = "montoSup") double montoSup, Model model) {
        var donaciones = donacionService.consultaNativo(montoInf, montoSup);
        String currentURL = "http://localhost/donacion/listado";
        model.addAttribute("donaciones", donaciones);
        model.addAttribute("montoInf", montoInf);
        model.addAttribute("montoSup", montoSup);
        model.addAttribute("montoSup", montoSup);
        model.addAttribute("currentURL", currentURL);
        return "/donacion/listado";
    }

    @GetMapping("/vistaUsuario")
    public String vistaUsuario(Model model) {
        return "/donacion/vistaUsuario";
    }
    @PostMapping("/guardarDonacion")
     public String donacionGuardar(Donacion donacion) {
        donacionService.save(donacion);
        return "redirect:/donacion/vistaUsuario";
    }

}
