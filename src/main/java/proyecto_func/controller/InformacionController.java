package proyecto_func.controller;

import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@Slf4j
@RequestMapping("/informacion")
public class InformacionController {

    @GetMapping("/quienesSomos")
    public String inicio() {
        return "/informacion/quienesSomos";
    }


}
