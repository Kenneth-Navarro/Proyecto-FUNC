package proyecto_func.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import proyecto_func.dao.MascotaDao;
import proyecto_func.domain.Mascota;
import proyecto_func.service.MascotaService;

@Service //Cuando trae @ a esto se le conoce como notacion
public class MascotaServiceImpl implements MascotaService {

    @Autowired
    private MascotaDao mascotaDao;

    @Override
    @Transactional(readOnly = true) //No altera la base de datos solo trae los datos
    public List<Mascota> getMascotas(boolean disponinle) {
        var lista = mascotaDao.findAll();
        if (disponinle) {
            lista.removeIf(e -> !e.isAdoptada());
        }
        return lista;
    }

    @Override
    @Transactional(readOnly = true)//No altera la base de datos solo trae los datos
    public Mascota getMascota(Mascota mascota) {
        return mascotaDao.findById(mascota.getIdMascota()).orElse(null);
    }

    @Override
    @Transactional //Si modifica la base de datos
    public void save(Mascota mascota) {
        mascotaDao.save(mascota);
    }

    @Override
    @Transactional //Si modifica la base de datos
    public void delete(Mascota mascota) {
        mascotaDao.delete(mascota);
    }

    @Override
    public List<Mascota> consultaNativo(int anoInf, int anoSup) {
        return mascotaDao.consultaNativo(anoInf, anoSup);
    }
    
     
}
