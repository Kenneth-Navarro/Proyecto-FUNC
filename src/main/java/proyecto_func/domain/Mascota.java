
package proyecto_func.domain;

import jakarta.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.util.List;
import lombok.Data;



@Data
@Entity
@Table(name = "mascota")
public class Mascota implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_mascota")
    private Long idMascota;
    private String nombre;
    private String descripcion;
    private String anoRescate;
    private String rutaImagen;
    private boolean adoptada;
   

    public Mascota() {
    }

    public Mascota(String nombre, String descripcion, String anoRescate, String rutaImagen, boolean adoptada) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.anoRescate = anoRescate;
        this.rutaImagen = rutaImagen;
        this.adoptada = adoptada;
    }

    

    
}
