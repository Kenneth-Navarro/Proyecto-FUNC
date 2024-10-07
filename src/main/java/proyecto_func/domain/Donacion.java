package proyecto_func.Domain;

import jakarta.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import lombok.Data;

@Data
@Entity
@Table(name = "donacion")
public class Donacion implements Serializable{
    
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_donacion")
    private Long idDonacion;
    private String nombreDonante;
    private String primerApellido;
    private String segundoApellido;
    private LocalDate fecha;
    private String descripcion;
    private double monto;

    
    public Donacion() {
    }

    public Donacion(String nombreDonante, String primerApellido, String segundoApellido, LocalDate fecha, String descripcion, double monto){
        this.nombreDonante = nombreDonante;
        this.primerApellido = primerApellido;
        this.segundoApellido = segundoApellido;
        this.fecha = fecha;
        this.descripcion = descripcion;
        this.monto = monto;
    }
    
    
    

    
}
