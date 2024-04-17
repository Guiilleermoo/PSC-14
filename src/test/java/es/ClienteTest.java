package es;


import static org.junit.Assert.assertEquals;


import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import es.model.Cliente;
import es.model.Reserva;

public class ClienteTest {

    private Cliente cliente;
    private List<Reserva> reservas;

    @Before
    public void setUp() {
        cliente = new Cliente();
        cliente.setDni("78955017L");
        cliente.setNombre("Unai Aira");
        cliente.setGmail("unaiaira@gmail.com");
        cliente.setTelefono("666444555");
        cliente.setResidencia("Calle Deusto");
        cliente.setPassword("contra");

        reservas = new ArrayList<>();
        Reserva reserva1 = new Reserva();
        reserva1.setId(1);
        reserva1.setCliente(cliente);
        reservas.add(reserva1);

        Reserva reserva2 = new Reserva();
        reserva2.setId(2);
        reserva2.setCliente(cliente);
        reservas.add(reserva2);

        
    }

    @Test
    public void testGettersAndSetters() {
        assertEquals("78955017L", cliente.getDni());
        assertEquals("Unai Aira", cliente.getNombre());
        assertEquals("unaiaira@gmail.com", cliente.getGmail());
        assertEquals("666444555", cliente.getTelefono());
        assertEquals("Calle Deusto", cliente.getResidencia());
        assertEquals("contra", cliente.getPassword());
        
    }

    
}