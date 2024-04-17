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
