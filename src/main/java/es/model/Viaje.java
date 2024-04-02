package es.model;
import java.time.LocalDateTime;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Viaje {
    @Id
   
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    protected String origen=null;
    protected String destino=null;
    protected LocalDateTime fecha=LocalDateTime.now();
    protected int duracion=0;
    protected Double precio=0.0;
    protected int oferta=0; //Porcentaje de descuento
    protected String empresa=null;
    protected int asientosTotales=0;


    @OneToMany(mappedBy = "viaje")
    private List<Reserva> reservas = new ArrayList<>();
    public String getOrigen() {
        return origen;
    }

    public void setOrigen(String origen) {
        if(origen!=null && origen!=destino) {
            this.origen = origen;
        } else {
            throw new IllegalArgumentException("El origen no puede ser nulo o el mismo que el destino");
        }
    }

    public String getDestino() {
        return destino;
    }
    
    public void setDestino(String destino) {
        if(origen!=destino && destino!=null) {
            this.destino = destino;
        } else {
            throw new IllegalArgumentException("El destino no puede ser el mismo que el origen o nulo");
        }
    }
    
    public LocalDateTime getFecha() {
        return fecha;
    }
    
    public void setFecha(LocalDateTime fecha) {
        if(fecha.isAfter(LocalDateTime.now()) || fecha.equals(LocalDateTime.now())) {
            this.fecha = fecha;
        } else {
            throw new IllegalArgumentException("La fecha no puede ser anterior a la actual");
        }
    }
    
    public int getDuracion() {
        return duracion;
    }
    
    public void setDuracion(int duracion) {
        if(duracion>0) {
            this.duracion = duracion;
        } else {
            throw new IllegalArgumentException("La duración no puede ser null ni negativa");
        }
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        if(precio>0) {
            this.precio = precio;
        } else {
            throw new IllegalArgumentException("El precio no puede ser null ni negativo");
        }
    }

    public int getOferta() {
        return oferta;
    }

    public void setOferta(int oferta) {
        if(oferta>=0 && oferta<=100) {
            this.oferta = oferta;
        } else {
            throw new IllegalArgumentException("La oferta no puede ser null ni negativa ni mayor que 100");
        }
    }

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        if(empresa!=null) {
            this.empresa = empresa;
        } else {
            throw new IllegalArgumentException("La empresa no puede ser nula");
        }
    }

    public int getAsientosTotales() {
        return asientosTotales;
    }

    public void setAsientosTotales(int asientosTotales) {
        if(asientosTotales>=0) {
            this.asientosTotales = asientosTotales;
        } else {
            throw new IllegalArgumentException("Los asientos totales no pueden ser negativos");
        }
    }


    

    @Override
    public String toString() {
        return "Viaje{" +
                "origen='" + origen + '\'' +
                ", destino='" + destino + '\'' +
                ", fecha='" + fecha.toString() + '\'' +
                ", duracion='" + duracion + '\'' +
                ", precio='" + precio + "€" + '\'' +
                ", oferta='" + oferta + "%" + '\'' +
                ", empresa='" + empresa + '\'' +
                ", asientosTotales='" + asientosTotales + '\'' +
               
                "}";
    }

    public int calcularPrecioFinal() {
        return (int) (precio - (precio * oferta / 100));
    }

    public LocalDateTime calcularFechaFin() {
        return fecha.plusMinutes(duracion);
    }
}