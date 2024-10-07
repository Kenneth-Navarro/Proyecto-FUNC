package proyecto_func.domain;

import jakarta.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import lombok.Data;

@Data
@Entity
@Table(name = "usuario")
public class Usuario implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_Usuario")
    private Long idUsuario;
    private String username;
    private String password;
    private String nombrePropietario;
    private String primerApellido;
    private String segundoApellido;
    private LocalDate fechaCreacion;
    private String telefonoContacto;

    public Usuario() {
    }

    public Usuario(String username, String password, String primerApellido, String segundoApellido, String telefonoContacto) {
        this.username = username;
        this.password = password;
        this.primerApellido = primerApellido;
        this.segundoApellido = segundoApellido;
        this.telefonoContacto = telefonoContacto;
  
    }
    @OneToOne
    @JoinColumn(name = "id_Usuario")
    Rol rol;

}
