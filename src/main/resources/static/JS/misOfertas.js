window.onload = function () {
    listarViajes();
}

let listarViajes = async () => {
    const peticion = await fetch('http://localhost:8080/sql/viajesCliente/' + "1", {

        method: 'GET',
        headers: {
            'Accept': 'application/json'
        }
    });

    const reservas = await peticion.json();

    let contenidoTabla = "";
    reservas.forEach(reserva => {
            let contenidoFila = `<tr>
            <td>${reserva.id}</td>
            <td>${reserva.viaje.id}</td>
            <td>${reserva.viaje.origen}</td>
            <td>${reserva.viaje.destino}</td>
            <td>${reserva.viaje.fecha}</td>
            <td>${reserva.viaje.precio}</td>
            <td>${reserva.viaje.oferta}</td>
            <td>${reserva.numPlazas}</td>
            <td>
            <i onClick="eliminarReserva(${reserva.id})"class="material-icons button cancel">cancel</i>
            </td>
        </tr>
        ` 
        contenidoTabla += contenidoFila;
    });

    
    document.querySelector("#tabla tbody").outerHTML = contenidoTabla;
}

let listarViajesOrdenados = async () => {
    const peticion = await fetch('http://localhost:8080/sql/viajes', {

        method: 'GET',
        headers: {
            'Accept': 'application/json'
        }
    });

    const viajes = await peticion.json();
    viajes.sort((a, b) => b.duracion - a.duracion);

    let contenidoTabla = "";
    for (let viaje of viajes) {
        let contenidoFila = `<tr>
            <td>${viaje.id}</td>
            <td>${viaje.empresa}</td>
            <td>${viaje.origen}</td>
            <td>${viaje.destino}</td>
            <td>${viaje.fecha}</td>
            <td>${viaje.duracion}</td>
            <td>${viaje.precio}</td>
            <td>${viaje.asientosTotales}</td>
            <td>${viaje.asientosDisponibles}</td>
            <td>${viaje.oferta}</td>
            <td>
            <i onClick="editarViaje(${viaje.id})"class="material-icons button edit">edit</i>
            <i onClick="borrarViaje(${viaje.id})"class="material-icons button delete">delete</i>
            </td>
        </tr>
        ` 
        contenidoTabla += contenidoFila;
    }
    document.querySelector("#tabla tbody").outerHTML = contenidoTabla;
}

let idEditar;

let eliminarReserva = async (id) => {
    const peticion = await fetch('http://localhost:8080/sql/eliminarReserva', {

        method: 'DELETE',
        body: JSON.stringify({
            id: id
        }),
        headers: {
            'Content-Type': 'application/json'
        }
    });
    listarViajes();
}
