package proyecto_func.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import proyecto_func.dao.RolDao;
import proyecto_func.domain.Rol;
import proyecto_func.service.RolService;

@Service
public class RolServiceImpl implements RolService{

    @Autowired
    private RolDao rolDao;

    @Override
    @Transactional(readOnly = true)//No altera la base de datos solo trae los datos
    public Rol getRol(Long IdUsuario) {
        return rolDao.consultaRol(IdUsuario);
    }
    @Override
    public void save(Rol rol) {
        rolDao.save(rol);
    }

    @Override
    public void delete(Rol rol) {
        rolDao.delete(rol);
    }
    
    
}
