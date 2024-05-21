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

    
   
  /**

     * @return Este metodo devuelve el origen del viaje
     */
    public String getOrigen() {
        return origen;
    }
/**
   
    
     * @return Este metodo devuelve los asientos disponibles del viaje.
     */
    public int getAsientosDisponibles() {
        return asientosDisponibles;
    }
/**
     * Este metodo calcula el prefio final con la formula (precio - (precio * oferta / 100).
     * 

     * @return Este metodo devuelve el precio final de un viaje.
     */
    public int calcularPrecioFinal() {
        return (int) (precio - (precio * oferta / 100));
    }

    public Viaje orElse(Object object) {
        
        throw new UnsupportedOperationException("Unimplemented method 'orElse'");
    }
}