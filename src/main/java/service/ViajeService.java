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
    public boolean crearViaje(Viaje viaje) {
        try {
            viajeRepository.save(viaje);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean borrarViaje(Integer id) {
        Optional<Viaje> viaje = viajeRepository.findById(id);
        if(viaje.isPresent()) {
            viajeRepository.deleteById(id);
            return true;
        }
        return false;
    }
    public boolean reservarViaje(Integer idViaje,Integer idCliente,Integer pasajeros) {
        Viaje viaje = viajeRepository.findById(idViaje).get();
    
        if(viaje.getAsientosDisponibles()>=pasajeros) {
            Cliente cliente = clienteRepository.findById(idCliente).get();
            Reserva reserva = new Reserva();
            reserva.setCliente(cliente);
            reserva.setViaje(viaje);
            reserva.setNumPlazas(pasajeros);
            cliente.getReservas().add(reserva);
            viaje.setAsientosDisponibles(viaje.getAsientosDisponibles()-pasajeros);
            reservaRepository.save(reserva);
       
        return true;
        }
        return false;
    }
    public boolean cancelarReserva(Integer idReserva, String dniCliente) {
        Optional<Reserva> reserva = reservaRepository.findById(idReserva);
    
        if(reserva.isPresent() && reserva.get().getCliente().getDni().equals(dniCliente)) {
            Reserva reserva2 = reserva.get();
            reserva2.getCliente().getReservas().remove(reserva2);
            reserva2.getViaje().setAsientosTotales(reserva2.getViaje().getAsientosTotales()+reserva2.getNumPlazas());
            reservaRepository.deleteById(idReserva);
            return true;
        }
        return false;
    }
}
