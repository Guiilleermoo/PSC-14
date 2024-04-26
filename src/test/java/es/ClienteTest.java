package es;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.anyInt;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import es.model.Cliente;
import es.model.Reserva;
import es.model.Trabajador;
import es.model.Viaje;

public class ClienteTest {

    private Cliente cliente;

    @Mock
    private List<Reserva> reservas;

    @Before
    public void setUp() {
    MockitoAnnotations.openMocks(this);

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
        verify(reservas).size();
    }

    @Test
    public void testOrElseThrowsUnsupportedOperationException() {
        // Creamos una instancia de la clase Viaje
        Cliente cliente = new Cliente();

        // Verificamos que llamar al método orElse lance una UnsupportedOperationException
        assertThrows(UnsupportedOperationException.class, () -> {
            cliente.orElse(new Object());
        });
    }

    @Test
    public void testEquals() {
        // Crear dos trabajadores con los mismos atributos
        Cliente cliente1 = new Cliente();
        cliente1.setDni("78955017L");
        cliente1.setNombre("Unai Aira");
        cliente1.setGmail("unaiaira@gmail.com");
        cliente1.setTelefono("666444555");
        cliente1.setResidencia("Calle Deusto");
        cliente1.setPassword("contra");

        Cliente cliente2 = new Cliente();
        cliente2.setDni("78955017L");
        cliente2.setNombre("Unai Aira");
        cliente2.setGmail("unaiaira@gmail.com");
        cliente2.setTelefono("666444555");
        cliente2.setResidencia("Calle Deusto");
        cliente2.setPassword("contra");

        // Verificar que los dos trabajadores sean iguales
        assertTrue(cliente1.equals(cliente2));
        assertTrue(cliente2.equals(cliente1));

        // Cambiar un atributo de uno de los trabajadores
        cliente2.setResidencia("Calle Bilbao");

        // Verificar que los trabajadores ya no sean iguales
        assertFalse(cliente1.equals(cliente2));
        assertFalse(cliente2.equals(cliente1));
    }
}