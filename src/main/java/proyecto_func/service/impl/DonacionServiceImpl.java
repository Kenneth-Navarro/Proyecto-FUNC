package proyecto_func.service.impl;

import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import proyecto_func.Domain.Donacion;
import proyecto_func.dao.DonacionDao;
import proyecto_func.service.DonacionService;

@Service
public class DonacionServiceImpl implements DonacionService{

    @Autowired
    private DonacionDao donacionDao;

    @Override
    @Transactional(readOnly = true) //No altera la base de datos solo trae los datos
    public List<Donacion> getDonaciones() {
        var lista = donacionDao.findAll();
        return lista;
    }

    @Override
    @Transactional(readOnly = true) // solo lee la base de datos//
    public Donacion getDonacion(Donacion donacion) {
        return donacionDao.findById(donacion.getIdDonacion()).orElse(null);
    }

    @Override
    @Transactional
    public void save(Donacion donacion) {
        donacion.setFecha(LocalDate.now());
        donacionDao.save(donacion);
    }

    @Override
    @Transactional
    public void delete(Donacion donacion) {
        donacionDao.delete(donacion);
    }

    @Override
    public List<Donacion> consultaNativo(double montoInf, double montoSup) {
        return donacionDao.consultaNativo(montoInf, montoSup);
    }
}
