package es.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
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
    private List<Reserva> reservas = new ArrayList<>();
   


}