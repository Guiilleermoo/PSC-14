window.onload = function () {
    listarViajes();
}

let listarViajes = async () => {
    const peticion = await fetch('http://localhost:8080/sql/buscarReserva/' + dniCliente, {

        method: 'GET',
        headers: {
            'Accept': 'application/json'
        }
    });

    const viajes = await peticion.json();

    let contenidoTabla = "";
    for (let viaje of viajes) {
        let contenidoFila = `<tr>
            <td>${viaje.idReserva}</td>
            <td>${viaje.idReserva}</td>
            <td>${viaje.origen}</td>
            <td>${viaje.destino}</td>
            <td>${viaje.fecha}</td>
            <td>${viaje.precio}</td>
            <td>${viaje.oferta}</td>
            <td>${viaje.numPlazas}</td>
            <td>
            <i onClick="eliminarReserva(${viaje.idReserva})"class="material-icons button cancel">cancel</i>
            </td>
        </tr>
        ` 
        contenidoTabla += contenidoFila;
    }
    document.querySelector("#tabla tbody").outerHTML = contenidoTabla;
}
let idEditar;

let borrarViaje = async (id) => {
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
