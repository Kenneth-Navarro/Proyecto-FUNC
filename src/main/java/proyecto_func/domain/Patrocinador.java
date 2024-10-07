
package proyecto_func.domain;

import jakarta.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.util.List;
import lombok.Data;

@Data
@Entity
@Table(name = "patrocinador")
public class Patrocinador implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_patrocinador")
    private Long idPatrocinador;
    private String nombre;
    private String primerApellido;
    private String segundoApellido;
    private String telefono;
    private String correo;
    private String nombreEncargado;
    private String identificacionEncargado;
    private String rutaImagen;
   

    public Patrocinador() {
    }

    public Patrocinador(String nombre, String primerApellido, String segundoApellido, String telefono, String correo, String nombreEncargado, String identificacionEncargado) {
        this.nombre = nombre;
        this.primerApellido = primerApellido;
        this.segundoApellido = segundoApellido;
        this.telefono = telefono;
        this.correo = correo;
        this.nombreEncargado = nombreEncargado;
        this.identificacionEncargado = identificacionEncargado;
    }

    

    

    

    
}
