package proyecto_func.Domain;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import lombok.Data;
import proyecto_func.domain.Mascota;
import proyecto_func.domain.Usuario;


@Data
@Entity
@Table(name = "adopcion")
public class Adopcion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_adopcion")
    private Long idAdopcion;
    private String nombre;
    private String telefono;
    private String correo;
    private String cedula;
    private int edad;
    private String provincia;
    private String canton;
    private String distrito;
    
    @ManyToOne
    @JoinColumn(name="id_mascota")
     Mascota mascota;
    
 
    public Adopcion() {
    }

    public Adopcion(String nombre,  String telefono, String correo, String cedula, int edad, String provincia, String canton, String distrito) {
        this.nombre = nombre;
        this.telefono = telefono;
        this.correo = correo;
        this.cedula = cedula;
        this.edad = edad;
        this.provincia = provincia;
        this.canton = canton;
        this.distrito = distrito;
    }

}
