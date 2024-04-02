package service;

import java.util.Optional;

import es.dao.ClienteRepository;
import es.dao.ViajeRepository;
import es.dao.ReservaRepository;
import es.model.Viaje;
import es.model.Cliente;
import es.model.Reserva;
public class ViajeService {
    private ViajeRepository viajeRepository;
    private ClienteRepository clienteRepository;
    private ReservaRepository reservaRepository;
    public Viaje crearViaje(Viaje viaje) {
        return viajeRepository.save(viaje);
    }

    public void borrarViaje(Integer id) {
        viajeRepository.deleteById(id);
    }
    public boolean reservarViaje(Integer idViaje,Integer idCliente,Integer pasajeros) {
        Viaje viaje = viajeRepository.findById(idViaje).get();
    
        if(viaje.getAsientosTotales()>=pasajeros) {
            Cliente cliente = clienteRepository.findById(idCliente).get();
            Reserva reserva = new Reserva();
            reserva.setCliente(cliente);
            reserva.setViaje(viaje);
            reserva.setNumPlazas(pasajeros);
            cliente.getReservas().add(reserva);
            viaje.setAsientosTotales(viaje.getAsientosTotales()-pasajeros);
            reservaRepository.save(reserva);
       
        return true;
        }
        return false;
    }
    public void cancelarReserva(Integer idReserva) {
        Optional<Reserva> reserva = reservaRepository.findById(idReserva);
        
        if(reserva.isPresent()) {
            Reserva reserva2 = reserva.get();
            reserva2.getCliente().getReservas().remove(reserva2);
            reserva2.getViaje().setAsientosTotales(reserva2.getViaje().getAsientosTotales()+reserva2.getNumPlazas());
            reservaRepository.deleteById(idReserva);
        }

    }
}
