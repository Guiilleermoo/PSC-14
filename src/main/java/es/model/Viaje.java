package es.model;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


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

    @OneToMany(mappedBy = "viaje")
    @JsonIgnore
    private List<Reserva> reservas = new ArrayList<>();

    public String getOrigen() {
        return origen;
    }

    public int getAsientosDisponibles() {
        return asientosDisponibles;
    }

    public int calcularPrecioFinal() {
        return (int) (precio - (precio * oferta / 100));
    }
}