package es.controller;

import java.time.LocalDateTime;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.model.Viaje;
import service.ViajeService;

@RestController
public class ApiController {
    
    private ViajeService viajeService;


    @RequestMapping("/test")
    public String test() {
        return "Hello World";
    }
    @GetMapping("/reservarViaje/{idViaje}/{idCliente}/{numAsientos}")
	public boolean reservarViaje(@PathVariable Integer idViaje, @PathVariable  Integer idCliente, @PathVariable Integer numAsientos) {

	    return  viajeService.reservarViaje(idViaje, idCliente, numAsientos);
	}


    @GetMapping("/borrarViaje/{idOferta}")
	public boolean borrarViaje(@PathVariable Integer idOferta) {
	   
         return   viajeService.borrarViaje(idOferta);
	}

    @GetMapping("/anadirViaje/{Origen}/{Destino}/{Fecha}/{Duracion}/{Precio}/{Oferta}/{Empresa}/{AsientosDisponibles}/{AsientosOcupados}")
	public boolean anadirViaje(@PathVariable String Origen,@PathVariable String Destino,@PathVariable LocalDateTime Fecha,
    @PathVariable int Duracion,@PathVariable Double Precio,@PathVariable int Oferta,@PathVariable String Empresa,
    @PathVariable int AsientosDisponibles,@PathVariable int AsientosOcupados) {
         
      Viaje viaje = new Viaje();        
        //sin hacer
    return viajeService.crearViaje(viaje);
	      
	}
 
    
    @PostMapping("/editarViaje")
    public boolean editarViaje(@RequestBody Viaje viaje) {
        //sin hacer
        // Aquí puedes realizar la lógica para editar el viaje con los datos proporcionados
        // Por ejemplo, podrías llamar a un servicio para realizar la actualización en la base de datos

        return false; // Devolver un booleano indicando si la operación de edición fue exitosa o no
    }
    @GetMapping("/cancelarReserva/{idReserva}/{dniCliente}")
	public boolean cancelarReserva(@PathVariable Integer idReserva, @PathVariable String dniCliente) {
	    
	  
	     return  viajeService.cancelarReserva(idReserva,dniCliente);
	}
	 
 
}
