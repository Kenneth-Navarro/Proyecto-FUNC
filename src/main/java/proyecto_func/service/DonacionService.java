package proyecto_func.service;

import java.util.List;
import proyecto_func.Domain.Donacion;

public interface DonacionService {

    public List<Donacion> getDonaciones();

    public Donacion getDonacion(Donacion donacion);

    public void save(Donacion donacion);

    public void delete(Donacion donacion);
    
     //Lista de donaciones utilizando consultas con SQL Nativo
    public List<Donacion> consultaNativo(double montoInf, double montoSup);
}
