
package proyecto_func.service;


import java.util.List;
import proyecto_func.domain.Mascota;

public interface MascotaService {

    // Se obtiene un listado de mascotas en un List
    public List<Mascota> getMascotas(boolean disponible);

    // Se obtiene un Mascota, a partir del id de un mascota
    public Mascota getMascota(Mascota mascota);

    // Se inserta un nuevo mascota si el id del mascota esta vacío
    // Se actualiza un mascota si el id del mascota NO esta vacío
    public void save(Mascota mascota);

    // Se elimina el mascota que tiene el id pasado por parámetro
    public void delete(Mascota mascota);
    
    //Lista de mascotas utilizando consultas con SQL Nativo
    public List<Mascota> consultaNativo(int anoInf, int anoSup);
    
}
