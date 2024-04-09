window.onload = function () {
    listarViajes();
}

let listarViajes = async () => {
    const peticion = await fetch('http://localhost:8080/sql/viajes', {

        method: 'GET',
        headers: {
            'Accept': 'application/json'
        }
    });

    const viajes = await peticion.json();

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
            <i onClick="reservarViaje(${viaje.id})"class="material-icons button check_circle">check_circle</i>
            </td>
        </tr>
        ` 
        contenidoTabla += contenidoFila;
    }
    document.querySelector("#tabla tbody").outerHTML = contenidoTabla;
}
let idEditar;

let reservarViaje = async (id) => {

    mostrarFormulario();

    idEditar = id;

    const peticion = await fetch('http://localhost:8080/sql/buscarViaje/' + id, {

        method: 'GET',
     
        headers: {
            'Accept': 'application/json',
            
        },
    });
    
    const reserva = await peticion.json();

    document.getElementById("idViaje").value = reserva.id;
    

    let btnModificar = document.getElementById("registrar");

    btnModificar.addEventListener("click", evento => {

        aplicarActualizacion(idEditar);
    });
}
let aplicarActualizacion = async (id) => {

    let campos = {};
    
    campos.dniCliente = document.getElementById("dni").value;
    campos.numPlazas = document.getElementById("numPlazas").value;
   
    const p = await fetch('http://localhost:8080/sql/crearReserva/' + id, {
        method: 'POST',
        body: JSON.stringify(campos),
        headers: {
            'Content-Type': 'application/json'
        }
    });

    listarViajes();

}
function mostrarFormulario() {
    let formulario = document.getElementById("formulario").style.visibility = "visible";
}