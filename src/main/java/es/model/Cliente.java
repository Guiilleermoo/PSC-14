package es.model;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.ManyToAny;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.JoinColumn;
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
    
    @OneToMany(mappedBy = "cliente")
    @JsonIgnore
    private List<Reserva> reservas = new ArrayList<>();
    
    @ManyToMany 
    @JoinTable(name = "Favorito",
            joinColumns = @JoinColumn(name = "cliente_id"),
            inverseJoinColumns = @JoinColumn(name = "viaje_id"))
    private List<Viaje> viajesFavoritos;
}