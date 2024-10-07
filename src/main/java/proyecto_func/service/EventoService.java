
package proyecto_func.service;


import java.sql.Date;
import java.util.List;
import proyecto_func.domain.Evento;

public interface EventoService {

    // Se obtiene un listado de eventos en un List
    public List<Evento> getEventos();

    // Se obtiene un Evento, a partir del id de un evento
    public Evento getEvento(Evento evento);

    // Se inserta un nuevo evento si el id del evento esta vacío
    // Se actualiza un evento si el id del evento NO esta vacío
    public void save(Evento evento);

    // Se elimina el evento que tiene el id pasado por parámetro
    public void delete(Evento evento);
    
    public List<Evento> consultaNativo(Date fechaInf, Date fechaSup);

}
