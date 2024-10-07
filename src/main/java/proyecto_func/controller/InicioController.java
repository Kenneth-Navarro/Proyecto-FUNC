package proyecto_func.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import proyecto_func.service.PatrocinadorService;

@Controller
@Slf4j
@RequestMapping("/")
public class InicioController {
    
    @Autowired 
    private PatrocinadorService patrocinadorService;

    @GetMapping("/")
    public String index(Model model) {
        var patrocinadores = patrocinadorService.getPatrocinadores();
        model.addAttribute("patrocinadores", patrocinadores);
        return "index";
    }

}
