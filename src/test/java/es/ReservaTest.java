package es;

import org.junit.jupiter.api.Test;

import es.model.Cliente;
import es.model.Reserva;
import es.model.Viaje;

import static org.junit.jupiter.api.Assertions.*;

public class ReservaTest {

    @Test
    public void testReservaConstructorAndGetters() {
        // crear ejemplos de las clases relacionadas
        Integer id = 1;
        Viaje viaje = new Viaje();
        Cliente cliente = new Cliente();
        Integer numPlazas = 5;

        // crear un objeto de la clase reserva
        Reserva reserva = new Reserva();
        reserva.setId(id);
        reserva.setViaje(viaje);
        reserva.setCliente(cliente);
        reserva.setNumPlazas(numPlazas);

        // verificar que el constructor y los getters funcionan correctamente
        assertEquals(id, reserva.getId());
        assertEquals(viaje, reserva.getViaje());
        assertEquals(cliente, reserva.getCliente());
        assertEquals(numPlazas, reserva.getNumPlazas());
    }
}
