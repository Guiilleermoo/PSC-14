package es.model;

import java.util.Optional;

import com.fasterxml.jackson.annotation.JsonManagedReference;

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
    @JsonManagedReference
    public Viaje viaje;

    @ManyToOne
    @JoinColumn(name = "cliente")
    @JsonManagedReference
    public Cliente cliente;
    Integer numPlazas=null;
}
