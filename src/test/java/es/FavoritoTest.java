package es;

import org.junit.jupiter.api.Test;

import es.model.Cliente;
import es.model.Favorito;
import es.model.Viaje;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

public class FavoritoTest {

    @Test
    public void testFavoritoConstructorAndGetters() {
        // Crear ejemplos de las clases relacionadas
        Integer id = 1;
        Viaje viajeMock = mock(Viaje.class);
        Cliente clienteMock = mock(Cliente.class);

        // Crear un objeto de la clase favorito
        Favorito favorito = new Favorito();
        favorito.setId(id);
        favorito.setViaje(viajeMock);
        favorito.setCliente(clienteMock);
        System.out.println(favorito);

        // Verificar que el constructor y los getters funcionan correctamente
        assertEquals(id, favorito.getId());
        assertEquals(viajeMock, favorito.getViaje());
        assertEquals(clienteMock, favorito.getCliente());
    }
}