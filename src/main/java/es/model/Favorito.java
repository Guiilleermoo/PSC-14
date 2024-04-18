package es.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Id;
import lombok.Data;


@Data
@Entity
public class Favorito {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @ManyToOne
    @JoinColumn(name = "viaje")
    @JsonManagedReference
    public Viaje viaje;

    @ManyToOne
    @JoinColumn(name = "cliente")
    @JsonManagedReference
    public Cliente cliente;


    
}