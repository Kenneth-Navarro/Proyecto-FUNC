package proyecto_func.service.impl;

import jakarta.mail.MessagingException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import proyecto_func.dao.MensajeDao;
import proyecto_func.domain.Mensaje;
import proyecto_func.service.CorreoService;
import proyecto_func.service.MensajeService;

@Service //Cuando trae @ a esto se le conoce como notacion
public class MensajeServiceImpl implements MensajeService {

    @Autowired
    private CorreoService correoService;
    
    @Autowired
    private MensajeDao mensajeDao;

    @Override
    @Transactional(readOnly = true) //No altera la base de datos solo trae los datos
    public List<Mensaje> getMensajes() {
        var lista = mensajeDao.findAll();
        return lista;
    }

    @Override
    @Transactional(readOnly = true)//No altera la base de datos solo trae los datos
    public Mensaje getMensaje(Mensaje mensaje) {
        return mensajeDao.findById(mensaje.getIdMensaje()).orElse(null);
    }

    @Override
    @Transactional //Si modifica la base de datos
    public void save(Mensaje mensaje) throws MessagingException {
        enviarMensaje(mensaje);
        mensajeDao.save(mensaje);
    }

    @Override
    @Transactional //Si modifica la base de datos
    public void delete(Mensaje mensaje) {
        mensajeDao.delete(mensaje);
    }

    @Override
    public List<Mensaje> consultaNativo(String nombreFiltro) {
        return mensajeDao.consultaNativo(nombreFiltro);
    }
    
     private void enviarMensaje(Mensaje mensajeEnviar) throws MessagingException {
        
        String mensaje = "";
        mensaje = String.format("<h1>Saludos %s</h1>"
                + "<hr><br><br>"
                + "<h3>Hemos recibido su solicitud de contacto. Muy pronto nos contactaremos.</h3>"
                + "<hr><br><br>"
                + "<p>Atte: UNC</p>", mensajeEnviar.getNombre());
        String asunto="Solicitud de Contactanos";
        
        correoService.enviarCorreoHtml(mensajeEnviar.getCorreo(), asunto, mensaje);
    }
}
