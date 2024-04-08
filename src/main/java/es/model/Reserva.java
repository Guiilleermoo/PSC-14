package es.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity

@Data
public class Reserva {
    @Id
    Integer id;

    @ManyToOne
    @JoinColumn(name = "viaje")
    private Viaje viaje;

    @ManyToOne
    @JoinColumn(name = "cliente")
    private Cliente cliente;
    Integer numPlazas=null;
}
