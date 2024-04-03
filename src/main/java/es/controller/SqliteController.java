package es.controller;

import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;

import es.dao.ClienteRepository;
import es.dao.ReservaRepository;
import es.dao.TrabajadorRepository;
import es.dao.ViajeRepository;
import es.model.Trabajador;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;


@RestController
@RequestMapping("/sql")
public class SqliteController {
    
    ClienteRepository clienteRepository;
    ReservaRepository reservaRepository;
    TrabajadorRepository trabajadorRepository;
    ViajeRepository viajeRepository;

    public SqliteController(ClienteRepository clienteRepository,
    ReservaRepository reservaRepository,
    TrabajadorRepository trabajadorRepository,
    ViajeRepository viajeRepository) {

        this.clienteRepository = clienteRepository;
        this.reservaRepository = reservaRepository;
        this.trabajadorRepository = trabajadorRepository;
        this.viajeRepository = viajeRepository;
        
    }
    @CrossOrigin("http://127.0.0.1:5500")
    @GetMapping("/buscarTrabajador/{dni}")
    public ResponseEntity<String> getTrabajador(@PathVariable String dni) {
        Trabajador trabajador = trabajadorRepository.findByDni(dni);
      
        if (trabajador == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
