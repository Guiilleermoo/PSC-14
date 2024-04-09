package es.service;

import org.springframework.stereotype.Service;

import es.dao.ReservaRepository;
import es.model.Reserva;

@Service
public class ReservaService {
    ReservaRepository reservaRepository;

    public Reserva findByDni(String dni) {
        Reserva r = new Reserva();
        
        return r;
    }
}
