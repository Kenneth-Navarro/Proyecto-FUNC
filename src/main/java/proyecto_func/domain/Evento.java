package proyecto_func.domain;

import jakarta.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.util.List;
import lombok.Data;

@Data
@Entity
@Table(name = "evento")
public class Evento implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_evento")
    private Long idEvento;
    private String nombre;
    private String descripcion;
    private Date fecha;
    private String ubicacion;
    private String duracion;
    private String rutaImagen;

    public Evento() {
    }

    public Evento(String nombre, String descripcion, Date fecha,String ubicacion, String duracion) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.fecha = fecha;
        this.ubicacion = ubicacion;
        this.duracion = duracion;
    }

}
