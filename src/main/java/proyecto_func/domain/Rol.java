 
package proyecto_func.domain;

import jakarta.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.time.LocalDate;

import lombok.Data;

@Data
@Entity
@Table(name = "rol")
public class Rol implements Serializable {

   private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_rol")
    private Long idRol;
    private String nombre;
    @Column(name = "id_Usuario")
    private Long idUsuario;



}
