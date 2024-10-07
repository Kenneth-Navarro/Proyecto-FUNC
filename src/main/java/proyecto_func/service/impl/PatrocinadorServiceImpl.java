package proyecto_func.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import proyecto_func.dao.PatrocinadorDao;
import proyecto_func.domain.Patrocinador;
import proyecto_func.service.PatrocinadorService;

@Service //Cuando trae @ a esto se le conoce como notacion
public class PatrocinadorServiceImpl implements PatrocinadorService {

    @Autowired
    private PatrocinadorDao patrocinadorDao;

    @Override
    @Transactional(readOnly = true) //No altera la base de datos solo trae los datos
    public List<Patrocinador> getPatrocinadores() {
        var lista = patrocinadorDao.findAll();
        return lista;
    }

    @Override
    @Transactional(readOnly = true)//No altera la base de datos solo trae los datos
    public Patrocinador getPatrocinador(Patrocinador patrocinador) {
        return patrocinadorDao.findById(patrocinador.getIdPatrocinador()).orElse(null);
    }

    @Override
    @Transactional //Si modifica la base de datos
    public void save(Patrocinador patrocinador) {
        patrocinadorDao.save(patrocinador);
    }

    @Override
    @Transactional //Si modifica la base de datos
    public void delete(Patrocinador patrocinador) {
        patrocinadorDao.delete(patrocinador);
    }

    @Override
    public List<Patrocinador> consultaNativo(String nombreFiltro) {
        return patrocinadorDao.consultaNativo(nombreFiltro);
    }
}
