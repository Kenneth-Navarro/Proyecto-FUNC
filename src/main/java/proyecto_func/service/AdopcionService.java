package proyecto_func.service;

import jakarta.mail.MessagingException;
import java.util.List;
import proyecto_func.Domain.Adopcion;

public interface AdopcionService {

    public List<Adopcion> getAdopciones();

    public Adopcion getAdopcion(Adopcion adopcion);

    public void save(Adopcion adopcion) throws MessagingException;;

    public void delete(Adopcion adopcion);
    
    public List<Adopcion> consultaNativo(int cedulaFiltro);
}
