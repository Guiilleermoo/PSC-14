package es.model;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Cliente {
    @Id
    protected String dni=null;
    protected String nombre=null;
    protected String gmail=null;
    protected String telefono=null;
    protected String residencia=null;
    protected String password=null;
    
    public Cliente orElse(Object object) {
        
        throw new UnsupportedOperationException("Unimplemented method 'orElse'");
    }
}