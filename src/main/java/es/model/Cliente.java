package es.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
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
    
    @OneToMany(mappedBy = "cliente")
    @JsonIgnore
    private List<Reserva> reservas = new ArrayList<>();
    
}