package proyecto_func.service.impl;

import jakarta.mail.MessagingException;
import java.util.List;
import java.util.Locale;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import proyecto_func.Domain.Adopcion;
import proyecto_func.dao.AdopcionDao;
import proyecto_func.service.AdopcionService;
import proyecto_func.service.CorreoService;

@Service
public class AdopcionServiceImpl implements AdopcionService {
    @Autowired
    private CorreoService correoService;
    @Autowired
    private AdopcionDao adopcionDao;

    @Override
    public List<Adopcion> getAdopciones() {
        var lista = adopcionDao.findAll();

        return lista;
    }

    @Override
    @Transactional(readOnly = true)
    public Adopcion getAdopcion(Adopcion adopcion) {
        return adopcionDao.findById(adopcion.getIdAdopcion()).orElse(null);
    }

    @Override
    @Transactional
    public void save(Adopcion adopcion)throws MessagingException{
        enviaCorreoActivar(adopcion);
        adopcionDao.save(adopcion);
    }
    

    @Override
    @Transactional
    public void delete(Adopcion adopcion) {
        adopcionDao.delete(adopcion);
    }

    @Override
    public List<Adopcion> consultaNativo(int cedulaFiltro) {
        return adopcionDao.consultaNativo(cedulaFiltro);
    }
    
    private void enviaCorreoActivar(Adopcion adopcion) throws MessagingException {
        
        String mensaje = "";
        mensaje = String.format("<h1>Saludos %s</h1>"
                + "<hr><br><br>"
                + "<h3>Hemos recibido su solicitud de adopción. Muy pronto nos contactaremos.</h3>"
                + "<hr><br><br>"
                + "<p>Atte: UNC</p>", adopcion.getNombre());
        String asunto="Solicitud de Adopción";
        
        correoService.enviarCorreoHtml(adopcion.getCorreo(), asunto, mensaje);
    }
}
