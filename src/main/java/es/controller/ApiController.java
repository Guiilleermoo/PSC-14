package es.controller;

import java.time.LocalDateTime;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApiController {
    
    public ApiController() {
    }

    @RequestMapping("/test")
    public String test() {
        return "Hello World";
    }
    @GetMapping("/reservarViaje/{idViaje}/{idCliente}/{numAsientos}")
	public boolean reservarViaje(@PathVariable Integer idViaje, @PathVariable  Integer idCliente, @PathVariable Integer numAsientos) {
	     
	  
	    return false;
	}


    @GetMapping("/borrarViaje/{idOferta}")
	public boolean borrarViaje(@PathVariable Integer idOferta) {
	     
	  
	        return false;
	}
    @GetMapping("/anadirViaje/{Origen}/{Destino}/{Fecha}/{Duracion}/{Precio}/{Oferta}/{Empresa}/{AsientosDisponibles}/{AsientosOcupados}")
	public boolean anadirViaje(@PathVariable String Origen,@PathVariable String Destino,@PathVariable LocalDateTime Fecha,
    @PathVariable int Duracion,@PathVariable Double Precio,@PathVariable int Oferta,@PathVariable String Empresa,
    @PathVariable int AsientosDisponibles,@PathVariable int AsientosOcupados) {
         
      
        
    return false;
	      
	}
    @GetMapping("/editarViaje/{idViaje}/{Fecha}/{Precio}/{Oferta}/{AsientosDisponibles}/{AsientosOcupados}")
	public boolean editarViaje(@PathVariable Integer idViaje,@PathVariable LocalDateTime Fecha, @PathVariable Double Precio, @PathVariable int Oferta, 
    @PathVariable int AsientosDisponibles, @PathVariable int AsientosOcupados) {
	     
	  
	     return false;
	}
    @GetMapping("/cancelarReserva/{idReserva}/{idCliente}")
	public boolean cancelarReserva(@PathVariable Integer idReserva, @PathVariable Integer idCliente) {
	     
	  
	     return false;
	}
	 
 
}
