package es.controller;

import java.time.LocalDateTime;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.model.*;
import es.service.*;

@RestController
public class ApiController {
    
    private ViajeService viajeService;
    private ReservaService reservaService;

    @RequestMapping("/test")
    public String test() {
        return "Hello World";
    }

    @GetMapping("/reservarViaje/{idViaje}/{idCliente}/{numAsientos}")
	public boolean reservarViaje(@PathVariable Integer idViaje, @PathVariable  String idCliente, @PathVariable Integer numAsientos) {
        try {
            reservaService.crearReserva(idViaje, idCliente, numAsientos);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } 
	}

    @GetMapping("/borrarViaje/{idOferta}")
	public boolean borrarViaje(@PathVariable Integer idOferta) {
        try {
            viajeService.borrarViaje(idOferta);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
	}

    @GetMapping("/anadirViaje/{Origen}/{Destino}/{Fecha}/{Duracion}/{Precio}/{Oferta}/{Empresa}/{AsientosTotales}/{AsientosDisponibles}")
	public boolean anadirViaje(@PathVariable String Origen, @PathVariable String Destino, @PathVariable String Fecha, @PathVariable int Duracion, @PathVariable Double Precio, @PathVariable int Oferta, @PathVariable String Empresa, @PathVariable int AsientosTotales, @PathVariable int AsientosDisponibles) {
      try {
            Viaje viaje = new Viaje();

            viaje.setOrigen(Origen);
            viaje.setDestino(Destino);
            viaje.setFecha(Fecha);
            viaje.setDuracion(Duracion);
            viaje.setPrecio(Precio);
            viaje.setOferta(Oferta);
            viaje.setEmpresa(Empresa);
            viaje.setAsientosTotales(AsientosTotales);
            viaje.setAsientosDisponibles(AsientosDisponibles);

            viajeService.crearViaje(viaje);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } 
	}
 
    @PostMapping("/editarViaje/")
    public boolean editarViaje(@RequestBody Viaje viaje) {
        //sin hacer
        // Aquí puedes realizar la lógica para editar el viaje con los datos proporcionados
        // Por ejemplo, podrías llamar a un servicio para realizar la actualización en la base de datos

        return false; // Devolver un booleano indicando si la operación de edición fue exitosa o no
    }

    @GetMapping("/cancelarReserva/{idReserva}/{dniCliente}")
	public boolean cancelarReserva(@PathVariable Integer idReserva, @PathVariable String dniCliente) {
	    try {
            reservaService.cancelarReserva(idReserva, dniCliente);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
	}
}
