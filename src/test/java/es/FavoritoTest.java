package es;

import org.junit.jupiter.api.Test;

import es.model.Cliente;
import es.model.Favorito;
import es.model.Viaje;

import static org.junit.jupiter.api.Assertions.*;

public class FavoritoTest {

    @Test
    public void testFavoritoConstructorAndGetters() {
        // Crear ejemplos de las clases relacionadas
        Integer id = 1;
        Viaje viaje = new Viaje();
        Cliente cliente = new Cliente();

        // Crear un objeto de la clase favorito
        Favorito favorito = new Favorito();
        favorito.setId(id);
        favorito.setViaje(viaje);
        favorito.setCliente(cliente);

        // Verificar que el constructor y los getters funcionan correctamente
        assertEquals(id, favorito.getId());
        assertEquals(viaje, favorito.getViaje());
        assertEquals(cliente, favorito.getCliente());
    }
}