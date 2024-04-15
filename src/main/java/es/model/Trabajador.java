package es.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Trabajador {
    @Id
    protected String dni=null;
    protected String nombre=null;
    protected String gmail=null;
    protected String telefono=null;
    protected int sueldo=0; 
    protected String password=null; 
}