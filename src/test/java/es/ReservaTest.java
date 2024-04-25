package es;

import org.junit.jupiter.api.Test;

import es.model.Cliente;
import es.model.Reserva;
import es.model.Viaje;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

public class ReservaTest {

    @Test
    public void testReservaConstructorAndGetters() {
        // crear ejemplos de las clases relacionadas
        Integer id = 1;
        Viaje viajeMock = mock(Viaje.class);
        Cliente clienteMock = mock(Cliente.class);
        Integer numPlazas = 5;

        // crear un objeto de la clase reserva
        Reserva reserva = new Reserva();
        reserva.setId(id);
        reserva.setViaje(viajeMock);
        reserva.setCliente(clienteMock);
        reserva.setNumPlazas(numPlazas);

        // verificar que el constructor y los getters funcionan correctamente
        assertEquals(id, reserva.getId());
        assertEquals(viajeMock, reserva.getViaje());
        assertEquals(clienteMock, reserva.getCliente());
        assertEquals(numPlazas, reserva.getNumPlazas());
    }
    @Test
    public void testToString() {
        // Crear una instancia de Reserva con datos de ejemplo
        Reserva reserva = new Reserva();
        reserva.setId(1);
        reserva.setViaje(new Viaje());
        reserva.setCliente(new Cliente());
        reserva.setNumPlazas(2);

        // Definir la representación esperada de la reserva como una cadena
        String expectedToString = "Reserva{id=1, viaje=Viaje{...}, cliente=Cliente{...}, numPlazas=2}";

        // Verificar que el método toString() devuelve la representación esperada
        assertEquals(expectedToString, reserva.toString());
    }
}
