package es.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Data
public class Trabajador {
    @Id
    protected String dni=null;
    protected String nombre=null;
    protected String gmail=null;
    protected String telefono=null;
    protected int sueldo=0;  
}