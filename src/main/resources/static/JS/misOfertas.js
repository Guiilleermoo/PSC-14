window.onload = function () {
    listarViajes();
}
function obtenerValorCookie(nombre) {
    const cookies = document.cookie.split(';');

    for (let cookie of cookies) {
        const partes = cookie.split('=');
        const nombreCookie = partes[0].trim();
        const valorCookie = partes[1];

        if (nombreCookie === nombre) {
            return valorCookie;
        }
    }

    return null;
}
let listarViajes = async () => {
    const gmail = obtenerValorCookie("email");
    const busquedaCliente = await fetch('http://localhost:8080/sql/buscarCliente/' + gmail, {
        method: 'GET',
        headers: {
            'Accept': 'application/json'
        },
    });
    const cliente = await busquedaCliente.json();
    const peticion = await fetch('http://localhost:8080/sql/viajesCliente/' + cliente.dni, {

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

