package es.model;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;



@Entity
@Data
public class Viaje {
   
    @Id
    private int id;
    protected String origen=null;
    protected String destino=null;
    protected String fecha=null;
    protected int duracion=0;
    protected Double precio=0.0;
    protected int oferta=0; //Porcentaje de descuento
    protected String empresa=null;
    protected int asientosTotales=0;
    protected int asientosDisponibles=0;

    
   
  
    public String getOrigen() {
        return origen;
    }

    public int getAsientosDisponibles() {
        return asientosDisponibles;
    }

    public int calcularPrecioFinal() {
        return (int) (precio - (precio * oferta / 100));
    }

    public Viaje orElse(Object object) {
        
        throw new UnsupportedOperationException("Unimplemented method 'orElse'");
    }
}