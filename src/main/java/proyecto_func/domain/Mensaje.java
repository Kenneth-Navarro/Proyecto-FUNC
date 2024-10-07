
package proyecto_func.domain;

import jakarta.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.util.List;
import lombok.Data;

@Data
@Entity
@Table(name = "mensaje")
public class Mensaje implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_mensaje")
    private Long idMensaje;
    private String nombre;
    private String correo;
    private String telefono;
    private String texto;



    public Mensaje() {
    }

    public Mensaje(String nombre, String correo, String telefono, String texto) {
        this.nombre = nombre;
        this.correo = correo;
        this.telefono = telefono;
        this.texto = texto;
    }

   
}
