
package proyecto_func.service;


import jakarta.mail.MessagingException;
import java.util.List;
import proyecto_func.domain.Mascota;
import proyecto_func.domain.Mensaje;

public interface MensajeService {

    // Se obtiene un listado de mensajes en un List
    public List<Mensaje> getMensajes();

    // Se obtiene un Mensaje, a partir del id de un mensaje
    public Mensaje getMensaje(Mensaje mensaje);

    // Se inserta un nuevo mensaje si el id del mensaje esta vacío
    // Se actualiza un mensaje si el id del mensaje NO esta vacío
    public void save(Mensaje mensaje) throws MessagingException;

    // Se elimina el mensaje que tiene el id pasado por parámetro
    public void delete(Mensaje mensaje);
    
    //Lista de mascotas utilizando consultas con SQL Nativo
    public List<Mensaje> consultaNativo(String nombreFiltro);

}
