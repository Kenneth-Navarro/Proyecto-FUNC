
package proyecto_func.service;


import java.util.List;
import proyecto_func.domain.Patrocinador;

public interface PatrocinadorService {

    // Se obtiene un listado de patrocinadors en un List
    public List<Patrocinador> getPatrocinadores();

    // Se obtiene un Patrocinador, a partir del id de un patrocinador
    public Patrocinador getPatrocinador(Patrocinador patrocinador);

    // Se inserta un nuevo patrocinador si el id del patrocinador esta vacío
    // Se actualiza un patrocinador si el id del patrocinador NO esta vacío
    public void save(Patrocinador patrocinador);

    // Se elimina el patrocinador que tiene el id pasado por parámetro
    public void delete(Patrocinador patrocinador);
    
    public List<Patrocinador> consultaNativo(String nombreFiltro);

}
