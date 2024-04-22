package es;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.anyInt;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import es.model.Cliente;
import es.model.Reserva;

public class ClienteTest {

    private Cliente cliente;

    @Mock
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

        // Estas 3 lineas simulan el comportamiento de la lista de reservas
        when(reservas.size()).thenReturn(2);
        when(reservas.get(0)).thenReturn(new Reserva());
        when(reservas.get(1)).thenReturn(new Reserva());
    }

    @Test
    public void testGettersAndSetters() {
        assertEquals("78955017L", cliente.getDni());
        assertEquals("Unai Aira", cliente.getNombre());
        assertEquals("unaiaira@gmail.com", cliente.getGmail());
        assertEquals("666444555", cliente.getTelefono());
        assertEquals("Calle Deusto", cliente.getResidencia());
        assertEquals("contra", cliente.getPassword());  
        
        assertEquals(2, reservas.size());
        verify(reservas, times(2)).get(anyInt());
    }
}