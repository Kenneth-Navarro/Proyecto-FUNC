package proyecto_func.service.impl;

import java.sql.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import proyecto_func.dao.EventoDao;
import proyecto_func.domain.Evento;
import proyecto_func.service.EventoService;

@Service //Cuando trae @ a esto se le conoce como notacion
public class EventoServiceImpl implements EventoService {

    @Autowired
    private EventoDao eventoDao;

    @Override
    @Transactional(readOnly = true) //No altera la base de datos solo trae los datos
    public List<Evento> getEventos() {
        var lista = eventoDao.findAll();
        return lista;
    }

    @Override
    @Transactional(readOnly = true)//No altera la base de datos solo trae los datos
    public Evento getEvento(Evento evento) {
        return eventoDao.findById(evento.getIdEvento()).orElse(null);
    }

    @Override
    @Transactional //Si modifica la base de datos
    public void save(Evento evento) {
        eventoDao.save(evento);
    }

    @Override
    @Transactional //Si modifica la base de datos
    public void delete(Evento evento) {
        eventoDao.delete(evento);
    }

    @Override
    public List<Evento> consultaNativo(Date fechaInf, Date fechaSup) {
        return eventoDao.consultaNativo(fechaInf, fechaSup);
    }
}
