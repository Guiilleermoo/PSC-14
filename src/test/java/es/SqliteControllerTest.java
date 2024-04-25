package es;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import es.controller.SqliteController;
import es.dao.ClienteRepository;
import es.dao.FavoritoRepository;
import es.dao.ReservaRepository;
import es.dao.TrabajadorRepository;
import es.dao.ViajeRepository;
import es.model.Cliente;
import es.model.Favorito;
import es.model.Reserva;
import es.model.Trabajador;
import es.model.Viaje;

public class SqliteControllerTest {

    private ViajeRepository viajeRepository;
    private SqliteController sqliteController = mock(SqliteController.class);
    private ClienteRepository clienteRepository;
    private FavoritoRepository favoritoRepository;
    private ReservaRepository reservaRepository;
    private TrabajadorRepository trabajadorRepository;

    @Before
    public void setUp() {
        viajeRepository = mock(ViajeRepository.class);
        clienteRepository = mock(ClienteRepository.class);
        favoritoRepository = mock(FavoritoRepository.class);
        reservaRepository = mock(ReservaRepository.class);
        trabajadorRepository = mock(TrabajadorRepository.class);
        sqliteController = new SqliteController(favoritoRepository, clienteRepository, reservaRepository, trabajadorRepository, viajeRepository); // Reemplaza "TuClase" con el nombre de tu clase que contiene el método getViajesTodos()
    }

    //TEST DE TRABAJADOR
    @Test
    public void testCrearTrabajador() {
        // Datos de prueba
        String jsonData = "{\"dni\": \"123456789\", \"nombre\": \"Unai\", \"gmail\": \"unaiaira@gmail.com\", \"telefono\": \"666444555\", \"sueldo\": 2000}";

        // Mock de la instancia de Trabajador
        Trabajador trabajador = new Trabajador();
        trabajador.setDni("123456789");
        trabajador.setNombre("Unai");
        trabajador.setGmail("unaiaira@gmail.com");
        trabajador.setTelefono("666444555");
        trabajador.setSueldo(2000);

        // Simular el guardado del trabajador
        when(trabajadorRepository.save(trabajador)).thenReturn(trabajador);

        // Llamar al método de creación de trabajador
        ResponseEntity<String> response = sqliteController.crearTrabajador(jsonData);

        // Verificar que se devuelve un ResponseEntity con HttpStatus.OK
        assertEquals(HttpStatus.OK, response.getStatusCode());

        // Verificar que se devuelve el mensaje esperado
        assertEquals("trabajador ha sido guardado correctamente", response.getBody());
    }    

    @Test
    public void testEliminarTrabajadorExistente() {

        // Datos de prueba
        String jsonData = "{\"dni\": \"12345678A\"}";

        // Mock de la instancia de Trabajador
        Trabajador trabajador = new Trabajador();
        trabajador.setDni("12345678A");

        // Simular la búsqueda y eliminación del trabajador
        when(trabajadorRepository.findByDni("12345678A")).thenReturn(trabajador);

        // Llamar al método de eliminación de trabajador
        ResponseEntity<String> response = sqliteController.eliminarTrabajador(jsonData);

        // Verificar que se devuelve un ResponseEntity con HttpStatus.OK
        assertEquals(HttpStatus.OK, response.getStatusCode());

        // Verificar que se devuelve el mensaje esperado
        assertEquals("Trabajador ha sido eliminado correctamente", response.getBody());
    }

    @Test
    public void testEliminarTrabajadorNoExistente() {
        // Mock del repositorio de trabajadores
        TrabajadorRepository trabajadorRepository = mock(TrabajadorRepository.class);

        // Datos de prueba
        String jsonData = "{\"dni\": \"12345678A\"}";

        // Simular que el trabajador no existe
        when(trabajadorRepository.findByDni("12345678A")).thenReturn(null);

        // Llamar al método de eliminación de trabajador
        ResponseEntity<String> response = sqliteController.eliminarTrabajador(jsonData);

        // Verificar que se devuelve un ResponseEntity con HttpStatus.NOT_FOUND
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());

        // Verificar que se devuelve el mensaje esperado
        assertEquals("No se ha encontrado el trabajador", response.getBody());
    }

    @Test
    public void testEliminarTrabajadorConError() {
        // Datos de prueba
        String jsonData = "{\"dni\": \"12345678A\"}";

        // Simular un error en la eliminación del trabajador
        when(trabajadorRepository.findByDni("12345678A")).thenThrow(new RuntimeException("Error en la base de datos"));

        // Llamar al método de eliminación de trabajador
        ResponseEntity<String> response = sqliteController.eliminarTrabajador(jsonData);

        // Verificar que se devuelve un ResponseEntity con HttpStatus.INTERNAL_SERVER_ERROR
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());

        // Verificar que se devuelve el mensaje esperado
        assertEquals("Error al eliminar el trabajador -> Error en la base de datos", response.getBody());
    }

    //TESTS DE CLIENTE

    @Test
    public void testGetClienteExistente() {
        // Datos de prueba
        String gmail = "unaiaira@gmail.com";
        String password = "password";
        Cliente cliente = new Cliente();
        cliente.setGmail(gmail);
        cliente.setPassword(password);

        // Simular la búsqueda del cliente en el repositorio
        when(clienteRepository.findByGmail(gmail)).thenReturn(cliente);

        // Llamar al método de tu controlador
        ResponseEntity<Cliente> response = sqliteController.getCliente(gmail, password);

        // Verificar que se devuelve un ResponseEntity con HttpStatus.OK
        assertEquals(HttpStatus.OK, response.getStatusCode());

        // Verificar que se devuelve el cliente esperado
        assertEquals(cliente, response.getBody());
    }

    @Test
    public void testGetClienteNoExistente() {
        // Datos de prueba
        String gmail = "unaiaira@gmail.com";
        String password = "password";

        // Simular que el cliente no existe en el repositorio
        when(clienteRepository.findByGmail(gmail)).thenReturn(null);

        // Llamar al método de tu controlador
        ResponseEntity<Cliente> response = sqliteController.getCliente(gmail, password);

        // Verificar que se devuelve un ResponseEntity con HttpStatus.NOT_FOUND
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void testGetClienteError() {
        // Datos de prueba
        String gmail = "unaiaira@gmail.com";
        String password = "password";

        // Simular un error en el repositorio
        when(clienteRepository.findByGmail(gmail)).thenThrow(new RuntimeException("Error en el repositorio"));

        // Llamar al método de tu controlador
        ResponseEntity<Cliente> response = sqliteController.getCliente(gmail, password);

        // Verificar que se devuelve un ResponseEntity con HttpStatus.INTERNAL_SERVER_ERROR
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }

    @Test
    public void testEliminarClienteExistente() {
        // Datos de prueba
        String jsonData = "{\"dni\": \"123456789\"}";
        Cliente cliente = new Cliente();
        cliente.setDni("123456789");

        // Simular la búsqueda del cliente en el repositorio
        when(clienteRepository.findByDni("123456789")).thenReturn(cliente);

        // Llamar al método de tu controlador
        ResponseEntity<String> response = sqliteController.eliminarCliente(jsonData);

        // Verificar que se devuelve un ResponseEntity con HttpStatus.OK
        assertEquals(HttpStatus.OK, response.getStatusCode());

        // Verificar que se devuelve el mensaje esperado
        assertEquals("Cliente ha sido eliminado correctamente", response.getBody());
    }

    @Test
    public void testEliminarClienteNoExistente() {
        // Datos de prueba
        String jsonData = "{\"dni\": \"123456789\"}";

        // Simular que el cliente no existe en el repositorio
        when(clienteRepository.findByDni("123456789")).thenReturn(null);

        // Llamar al método de tu controlador
        ResponseEntity<String> response = sqliteController.eliminarCliente(jsonData);

        // Verificar que se devuelve un ResponseEntity con HttpStatus.NOT_FOUND
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());

        // Verificar que se devuelve el mensaje esperado
        assertEquals("No se ha encontrado al cliente", response.getBody());
    }

    @Test
    public void testEliminarClienteError() {
        // Datos de prueba
        String jsonData = "{\"dni\": \"123456789\"}";

        // Simular un error en el repositorio
        when(clienteRepository.findByDni("123456789")).thenThrow(new RuntimeException("Error en el repositorio"));

        // Llamar al método de tu controlador
        ResponseEntity<String> response = sqliteController.eliminarCliente(jsonData);

        // Verificar que se devuelve un ResponseEntity con HttpStatus.INTERNAL_SERVER_ERROR
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());

        // Verificar que se devuelve el mensaje esperado
        assertEquals("Error al eliminar el cliente -> Error en el repositorio", response.getBody());
    }

    @Test
    public void testCrearClienteCorrecto() {
        // Datos de prueba
        String jsonData = "{\"dni\": \"123456789\", \"nombre\": \"Unai\", \"gmail\": \"unaiaira@gmail.com\", \"telefono\": \"666444555\", \"residencia\": \"Deusto\", \"contrasena\": \"password\"}";

        // Simular el guardado del cliente
        when(clienteRepository.save(new Cliente())).thenReturn(new Cliente());

        // Llamar al método del controlador
        ResponseEntity<String> response = sqliteController.crearCliente(jsonData);

        // Verificar que se devuelve un ResponseEntity con HttpStatus.OK
        assertEquals(HttpStatus.OK, response.getStatusCode());

        // Verificar que se devuelve el mensaje esperado
        assertEquals("cliente ha sido guardado correctamente", response.getBody());
    }

    @Test
    public void testCrearClienteError() {
        // Datos de prueba
        String jsonData = "{\"dni\": \"123456789\", \"nombre\": \"Unai\", \"gmail\": \"unaiaira@gmail.com\", \"telefono\": \"666444555\", \"residencia\": \"Deusto\", \"contrasena\": \"password\"}";

        // Simular un error en el repositorio
        when(clienteRepository.save(new Cliente())).thenThrow(new RuntimeException("Error en el repositorio"));

        // Llamar al método del controlador
        sqliteController.crearCliente(jsonData);
    }




    //TESTS DE VIAJES

    @Test
    public void testCrearViaje() {
        // Datos de prueba
        String jsonData = "{\"id\": 1, \"origen\": \"Bilbao\", \"destino\": \"Madrid\", \"fecha\": \"2024-04-30\", \"duracion\": 120, \"precio\": 50.0, \"oferta\": 0, \"empresa\": \"Deusto\", \"asientosTotales\": 50, \"asientosDisponibles\": 50}";

        // Llamar al método de creación de viaje
        ResponseEntity<String> response = sqliteController.crearViaje(jsonData);

        // Verificar que se devuelve un ResponseEntity con HttpStatus.OK
        assertEquals(HttpStatus.OK, response.getStatusCode());

        // Verificar que se devuelve el mensaje esperado
        assertEquals("Viaje creado correctamente", response.getBody());
    }

    @Test
    public void testCrearViajeConError() {
        // Simular un error al guardar el viaje
        when(viajeRepository.save(any(Viaje.class))).thenThrow(new RuntimeException("Error en la base de datos"));

        // Datos de prueba
        String jsonData = "{\"id\": 1, \"origen\": \"Bilbao\", \"destino\": \"Madrid\", \"fecha\": \"2024-04-30\", \"duracion\": 120, \"precio\": 50.0, \"oferta\": 0, \"empresa\": \"Deusto\", \"asientosTotales\": 50, \"asientosDisponibles\": 50}";

        // Llamar al método de creación de viaje
        ResponseEntity<String> response = sqliteController.crearViaje(jsonData);

        // Verificar que se devuelve un ResponseEntity con HttpStatus.INTERNAL_SERVER_ERROR
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());

        // Verificar que se devuelve el mensaje esperado
        assertEquals("Error al crear el viaje -> Error en la base de datos", response.getBody());
    }


    @Test
    public void testGetViajesTodos() {
        // Arrange
        List<Viaje> viajes = new ArrayList<>();
        viajes.add(new Viaje());
        when(viajeRepository.findAll()).thenReturn(viajes);

        // Act
        ResponseEntity<List<Viaje>> responseEntity = sqliteController.getViajesTodos();

        // Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(viajes, responseEntity.getBody());
    }

    @Test
    public void testGetViajesTodos_Exception() {
        // Arrange
        when(viajeRepository.findAll()).thenThrow(new RuntimeException("Error de prueba"));

        // Act
        ResponseEntity<List<Viaje>> responseEntity = sqliteController.getViajesTodos();

        // Assert
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
    }

    @Test
    public void testEliminarViajeExistente() {
        // Datos de prueba
        String jsonData = "{\"id\": 1}";
        Viaje viaje = new Viaje();
        viaje.setId(1);

        // Simular la búsqueda del viaje en el repositorio
        when(viajeRepository.findById(1)).thenReturn(viaje);

        // Llamar al método del controlador
        ResponseEntity<String> response = sqliteController.eliminarViaje(jsonData);

        // Verificar que se devuelve un ResponseEntity con HttpStatus.OK
        assertEquals(HttpStatus.OK, response.getStatusCode());

        // Verificar que se devuelve el mensaje esperado
        assertEquals("El viaje ha sido eliminado correctamente", response.getBody());
    }

    @Test
    public void testEliminarViajeNoExistente() {
        // Datos de prueba
        String jsonData = "{\"id\": 1}";

        // Simular que el viaje no existe en el repositorio
        when(viajeRepository.findById(1)).thenReturn(null);

        // Llamar al método del controlador
        ResponseEntity<String> response = sqliteController.eliminarViaje(jsonData);

        // Verificar que se devuelve un ResponseEntity con HttpStatus.NOT_FOUND
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());

        // Verificar que se devuelve el mensaje esperado
        assertEquals("No se ha encontrado el viaje", response.getBody());
    }

    @Test
    public void testEliminarViajeError() {
        // Datos de prueba
        String jsonData = "{\"id\": 1}";

        // Simular un error en el repositorio
        when(viajeRepository.findById(1)).thenThrow(new RuntimeException("Error en el repositorio"));

        // Llamar al método del controlador
        ResponseEntity<String> response = sqliteController.eliminarViaje(jsonData);

        // Verificar que se devuelve un ResponseEntity con HttpStatus.INTERNAL_SERVER_ERROR
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());

        // Verificar que se devuelve el mensaje esperado
        assertEquals("Error al eliminar el viaje -> Error en el repositorio", response.getBody());
    }

    @Test
    public void testGetViajeExistente() {
        // Datos de prueba
        Integer id = 1;
        Viaje viaje = new Viaje();
        viaje.setId(id);

        // Simular la búsqueda del viaje en el repositorio
        when(viajeRepository.findById(id)).thenReturn(java.util.Optional.of(viaje));

        // Llamar al método del controlador
        ResponseEntity<Viaje> response = sqliteController.getViaje(id);

        // Verificar que se devuelve un ResponseEntity con HttpStatus.OK
        assertEquals(HttpStatus.OK, response.getStatusCode());

        // Verificar que se devuelve el viaje esperado
        assertEquals(viaje, response.getBody());
    }

    @Test
    public void testGetViajeNoExistente() {
        // Datos de prueba
        Integer id = 1;

        // Simular que el viaje no existe en el repositorio
        when(viajeRepository.findById(id)).thenReturn(java.util.Optional.empty());

        // Llamar al método del controlador
        ResponseEntity<Viaje> response = sqliteController.getViaje(id);

        // Verificar que se devuelve un ResponseEntity con HttpStatus.NOT_FOUND
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());

        // Verificar que no se devuelve ningún viaje
        assertEquals(null, response.getBody());
    }

    @Test
    public void testGetViajeError() {
        // Datos de prueba
        Integer id = 1;

        // Simular un error en el repositorio
        when(viajeRepository.findById(id)).thenThrow(new RuntimeException("Error en el repositorio"));

        // Llamar al método del controlador
        ResponseEntity<Viaje> response = sqliteController.getViaje(id);

        // Verificar que se devuelve un ResponseEntity con HttpStatus.INTERNAL_SERVER_ERROR
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());

        // Verificar que no se devuelve ningún viaje
        assertEquals(null, response.getBody());
    }

    @Test
    public void testEditarViajeExistente() {
        // Datos de prueba
        Integer id = 1;
        String jsonData = "{\"origen\": \"Bilbao\", \"destino\": \"Madrid\", \"fecha\": \"2024-04-30\", \"duracion\": \"120\", \"precio\": \"50.0\", \"oferta\": \"0\", \"empresa\": \"Deusto\", \"asientosTotales\": \"50\", \"asientosDisponibles\": \"50\"}";
        Viaje viaje = new Viaje();
        viaje.setId(id);

        // Simular la búsqueda y edición del viaje en el repositorio
        when(viajeRepository.findById(id)).thenReturn(java.util.Optional.of(viaje));

        // Llamar al método del controlador
        ResponseEntity<Viaje> response = sqliteController.editarViaje(id, jsonData);

        // Verificar que se devuelve un ResponseEntity con HttpStatus.OK
        assertEquals(HttpStatus.OK, response.getStatusCode());

        // Verificar que se devuelve el viaje editado
        assertEquals(viaje, response.getBody());
    }

    @Test
    public void testEditarViajeNoExistente() {
        // Datos de prueba
        Integer id = 1;
        String jsonData = "{\"origen\": \"Bilbao\", \"destino\": \"Madrid\", \"fecha\": \"2024-04-30\", \"duracion\": \"120\", \"precio\": \"50.0\", \"oferta\": \"0\", \"empresa\": \"Deusto\", \"asientosTotales\": \"50\", \"asientosDisponibles\": \"50\"}";

        // Simular que el viaje no existe en el repositorio
        when(viajeRepository.findById(id)).thenReturn(java.util.Optional.empty());

        // Llamar al método del controlador
        ResponseEntity<Viaje> response = sqliteController.editarViaje(id, jsonData);

        // Verificar que se devuelve un ResponseEntity con HttpStatus.INTERNAL_SERVER_ERROR
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());

        // Verificar que no se devuelve ningún viaje
        assertEquals(null, response.getBody());
    }

    @Test
    public void testEditarViajeError() {
        // Datos de prueba
        Integer id = 1;
        String jsonData = "{\"origen\": \"Bilbao\", \"destino\": \"Madrid\", \"fecha\": \"2024-04-30\", \"duracion\": \"120\", \"precio\": \"50.0\", \"oferta\": \"0\", \"empresa\": \"Deusto\", \"asientosTotales\": \"50\", \"asientosDisponibles\": \"50\"}";

        // Simular un error en el repositorio
        when(viajeRepository.findById(id)).thenThrow(new RuntimeException("Error en el repositorio"));

        // Llamar al método del controlador
        ResponseEntity<Viaje> response = sqliteController.editarViaje(id, jsonData);

        // Verificar que se devuelve un ResponseEntity con HttpStatus.INTERNAL_SERVER_ERROR
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());

        // Verificar que no se devuelve ningún viaje
        assertEquals(null, response.getBody());
    }


    //TESTS DE RESERVAS

    @Test
    public void testCrearReserva() {
        // Datos de prueba
        Integer idViaje = 1;
        String jsonData = "{\"dniCliente\": \"123456789\", \"numPlazas\": \"2\"}";
        Viaje viaje = new Viaje();
        Cliente cliente = new Cliente();
        when(viajeRepository.findById(idViaje)).thenReturn(java.util.Optional.of(viaje));
        when(clienteRepository.findByDni("123456789")).thenReturn(cliente);

        // Llamar al método del controlador
        ResponseEntity<String> response = sqliteController.crearReserva(idViaje, jsonData);

        // Verificar que se devuelve un ResponseEntity con HttpStatus.OK
        assertEquals(HttpStatus.OK, response.getStatusCode());

        // Verificar que se devuelve el mensaje esperado
        assertEquals("Tu reserva se ha registrado correctamente", response.getBody());
    }

    @Test
    public void testCrearReservaNoCliente() {
        // Datos de prueba
        Integer idViaje = 1;
        String jsonData = "{\"dniCliente\": \"123456789\", \"numPlazas\": \"2\"}";
        Viaje viaje = new Viaje();
        when(viajeRepository.findById(idViaje)).thenReturn(java.util.Optional.of(viaje));
        when(clienteRepository.findByDni("123456789")).thenReturn(null);

        // Llamar al método del controlador
        ResponseEntity<String> response = sqliteController.crearReserva(idViaje, jsonData);

        // Verificar que se devuelve un ResponseEntity con HttpStatus.NOT_FOUND
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());

        // Verificar que se devuelve el mensaje esperado
        assertEquals("No eres cliente debes de registrarte", response.getBody());
    }

    @Test
    public void testCrearReservaError() {
        // Datos de prueba
        Integer idViaje = 1;
        String jsonData = "{\"dniCliente\": \"123456789\", \"numPlazas\": \"2\"}";
        when(viajeRepository.findById(idViaje)).thenThrow(new RuntimeException("Error en el repositorio"));

        // Llamar al método del controlador
        ResponseEntity<String> response = sqliteController.crearReserva(idViaje, jsonData);

        // Verificar que se devuelve un ResponseEntity con HttpStatus.INTERNAL_SERVER_ERROR
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }

    @Test
    public void testEliminarReservaExistente() {
        // Datos de prueba
        String jsonData = "{\"id\": 1}";
        Reserva reserva = new Reserva();
        when(reservaRepository.findById(1)).thenReturn(reserva);

        // Llamar al método del controlador
        ResponseEntity<String> response = sqliteController.eliminarReserva(jsonData);

        // Verificar que se devuelve un ResponseEntity con HttpStatus.OK
        assertEquals(HttpStatus.OK, response.getStatusCode());

        // Verificar que se devuelve el mensaje esperado
        assertEquals("Reserva ha sido eliminado correctamente", response.getBody());
    }

    @Test
    public void testEliminarReservaNoExistente() {
        // Datos de prueba
        String jsonData = "{\"id\": 1}";
        when(reservaRepository.findById(1)).thenReturn(null);

        // Llamar al método del controlador
        ResponseEntity<String> response = sqliteController.eliminarReserva(jsonData);

        // Verificar que se devuelve un ResponseEntity con HttpStatus.NOT_FOUND
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());

        // Verificar que se devuelve el mensaje esperado
        assertEquals("No se ha encontrado la reserva", response.getBody());
    }

    @Test
    public void testEliminarReservaError() {
        // Simular un error en el método
        when(reservaRepository.findById(1)).thenThrow(new RuntimeException("Error en el controlador"));

        // Llamar al método del controlador
        ResponseEntity<String> response = sqliteController.eliminarReserva("{\"id\": 1}");

        // Verificar que se devuelve un ResponseEntity con HttpStatus.INTERNAL_SERVER_ERROR
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }

    @Test
    public void testGetViajesClienteExistente() {
        // Datos de prueba
        String dni = "123456789";
        Cliente cliente = new Cliente();
        cliente.setDni(dni);
        List<Reserva> reservas = new ArrayList<>();
        reservas.add(new Reserva());
        when(clienteRepository.findByDni(dni)).thenReturn(cliente);
        when(reservaRepository.findByCliente(cliente)).thenReturn(reservas);

        // Llamar al método del controlador
        ResponseEntity<List<Reserva>> response = sqliteController.getViajesCliente(dni);

        // Verificar que se devuelve un ResponseEntity con HttpStatus.OK
        assertEquals(HttpStatus.OK, response.getStatusCode());

        // Verificar que se devuelve la lista de reservas esperada
        assertEquals(reservas, response.getBody());
    }

    @Test
    public void testGetViajesClienteNoExistente() {
        // Datos de prueba
        String dni = "123456789";
        when(clienteRepository.findByDni(dni)).thenThrow(new RuntimeException("Error en el controlador"));

        // Llamar al método del controlador
        ResponseEntity<List<Reserva>> response = sqliteController.getViajesCliente(dni);

        // Verificar que se devuelve un ResponseEntity con HttpStatus.INTERNAL_SERVER_ERROR
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }

    //TESTS DE FAVORITOS

    @Test
    public void testGetFavoritoDniExistente() {
        // Datos de prueba
        String dni = "123456789";
        List<Favorito> favoritos = new ArrayList<>();
        favoritos.add(new Favorito());
        when(favoritoRepository.findByClienteDni(dni)).thenReturn(favoritos);

        // Llamar al método del controlador
        ResponseEntity<List<Favorito>> response = sqliteController.getFavoritoDni(dni);

        // Verificar que se devuelve un ResponseEntity con HttpStatus.OK
        assertEquals(HttpStatus.OK, response.getStatusCode());

        // Verificar que se devuelve la lista de favoritos esperada
        assertEquals(favoritos, response.getBody());
    }

    @Test
    public void testGetFavoritoDniNoExistente() {
        // Datos de prueba
        String dni = "123456789";
        when(favoritoRepository.findByClienteDni(dni)).thenReturn(null);

        // Llamar al método del controlador
        ResponseEntity<List<Favorito>> response = sqliteController.getFavoritoDni(dni);

        // Verificar que se devuelve un ResponseEntity con HttpStatus.NOT_FOUND
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void testGetFavoritoDniError() {
        // Simular un error en el método
        when(sqliteController.getFavoritoDni("123456789")).thenThrow(new RuntimeException("Error en el controlador"));

        // Llamar al método del controlador
        ResponseEntity<List<Favorito>> response = sqliteController.getFavoritoDni("123456789");

        // Verificar que se devuelve un ResponseEntity con HttpStatus.INTERNAL_SERVER_ERROR
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }

    @Test
    public void testAnadirFavoritoExitoso() {
        // Datos de prueba
        String dni = "123456789";
        Integer idViaje = 1;
        Cliente cliente = new Cliente();
        cliente.setDni(dni);
        Viaje viaje = new Viaje();
        viaje.setId(idViaje);
        Favorito favorito = new Favorito();
        favorito.setCliente(cliente);
        favorito.setViaje(viaje);
        when(clienteRepository.findByDni(dni)).thenReturn(cliente);
        when(viajeRepository.findById(idViaje)).thenReturn(java.util.Optional.of(viaje));
        when(favoritoRepository.save(favorito)).thenReturn(favorito);

        // Llamar al método del controlador
        ResponseEntity<String> response = sqliteController.anadirFavorito(dni, idViaje);

        // Verificar que se devuelve un ResponseEntity con HttpStatus.OK
        assertEquals(HttpStatus.OK, response.getStatusCode());

        // Verificar el mensaje de respuesta
        assertEquals("Favorito añadido correctamente", response.getBody());
    }

    @Test
    public void testAnadirFavoritoErrorClienteNoEncontrado() {
        // Datos de prueba
        String dni = "123456789";
        Integer idViaje = 1;
        when(clienteRepository.findByDni(dni)).thenReturn(null);

        // Llamar al método del controlador
        ResponseEntity<String> response = sqliteController.anadirFavorito(dni, idViaje);

        // Verificar que se devuelve un ResponseEntity con HttpStatus.INTERNAL_SERVER_ERROR
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void testAnadirFavoritoErrorViajeNoEncontrado() {
        // Datos de prueba
        String dni = "123456789";
        Integer idViaje = 1;
        Cliente cliente = new Cliente();
        cliente.setDni(dni);
        when(clienteRepository.findByDni(dni)).thenReturn(cliente);
        when(viajeRepository.findById(idViaje)).thenThrow(new RuntimeException("Error en el controlador"));

        // Llamar al método del controlador
        ResponseEntity<String> response = sqliteController.anadirFavorito(dni, idViaje);

        // Verificar que se devuelve un ResponseEntity con HttpStatus.INTERNAL_SERVER_ERROR
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }

    @Test
    public void testEliminarFavoritoExitoso() {
        // Datos de prueba
        int idFavorito = 1;
        Favorito favorito = new Favorito();
        favorito.setId(idFavorito);
        when(favoritoRepository.findById(idFavorito)).thenReturn(favorito);

        // Llamar al método del controlador
        ResponseEntity<String> response = sqliteController.eliminarFavorito("{\"id\": " + idFavorito + "}");

        // Verificar que se devuelve un ResponseEntity con HttpStatus.OK
        assertEquals(HttpStatus.OK, response.getStatusCode());

        // Verificar el mensaje de respuesta
        assertEquals("Reserva ha sido eliminado correctamente", response.getBody());
    }

    @Test
    public void testEliminarFavoritoNoEncontrado() {
        // Datos de prueba
        int idFavorito = 1;
        when(favoritoRepository.findById(idFavorito)).thenReturn(null);

        // Llamar al método del controlador
        ResponseEntity<String> response = sqliteController.eliminarFavorito("{\"id\": " + idFavorito + "}");

        // Verificar que se devuelve un ResponseEntity con HttpStatus.NOT_FOUND
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void testEliminarFavoritoError() {
        // Datos de prueba
        int idFavorito = 1;
        when(favoritoRepository.findById(idFavorito)).thenThrow(new RuntimeException("Error en la base de datos"));

        // Llamar al método del controlador
        ResponseEntity<String> response = sqliteController.eliminarFavorito("{\"id\": " + idFavorito + "}");

        // Verificar que se devuelve un ResponseEntity con HttpStatus.INTERNAL_SERVER_ERROR
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }

    @Test
    public void testGetDNIporGmailExitoso() {
        // Datos de prueba
        String gmail = "example@gmail.com";
        String dni = "123456789";
        Cliente cliente = new Cliente();
        cliente.setDni(dni);
        when(clienteRepository.findByGmail(gmail)).thenReturn(cliente);

        // Llamar al método del controlador
        ResponseEntity<String> response = sqliteController.getDNIporGmail(gmail);

        // Verificar que se devuelve un ResponseEntity con HttpStatus.OK
        assertEquals(HttpStatus.OK, response.getStatusCode());

        // Verificar el cuerpo de la respuesta
        assertEquals(dni, response.getBody());
    }

    @Test
    public void testGetDNIporGmailNoEncontrado() {
        // Datos de prueba
        Cliente cliente = new Cliente();
        String gmail = "unaiaira@gmail.com";
        String dni = null;
        cliente.setDni(dni);
        cliente.setGmail(gmail);
        when(clienteRepository.findByGmail(gmail)).thenReturn(cliente);

        // Llamar al método del controlador
        ResponseEntity<String> response = sqliteController.getDNIporGmail(gmail);

        // Verificar que se devuelve un ResponseEntity con HttpStatus.NOT_FOUND
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void testGetDNIporGmailError() {
        // Datos de prueba
        String gmail = "unaiaira@gmail.com";
        when(clienteRepository.findByGmail(gmail)).thenThrow(new RuntimeException("Error en la base de datos"));

        // Llamar al método del controlador
        ResponseEntity<String> response = sqliteController.getDNIporGmail(gmail);

        // Verificar que se devuelve un ResponseEntity con HttpStatus.INTERNAL_SERVER_ERROR
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }
}