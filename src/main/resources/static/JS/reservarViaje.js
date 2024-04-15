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

let listarViajesOrdenados = async () => {
    const peticion = await fetch('http://localhost:8080/sql/viajes', {

        method: 'GET',
        headers: {
            'Accept': 'application/json'
        }
    });

    const viajes = await peticion.json();
    viajes.sort((a, b) => a.duracion - b.duracion);

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

let listarViajesOrdenados2 = async () => {

// Paso 1: Obtener los datos de la tabla HTML
let tabla = document.getElementById("tabla");
let filas = tabla.getElementsByTagName("tr");
let datos = [];

for (let i = 2; i < filas.length; i++) { // Empezamos desde 2 para omitir la fila de encabezado
    let fila = filas[i];
    let celdas = fila.getElementsByTagName("td");
    let id = parseInt(celdas[0].innerText);
    let empresa = celdas[1].innerText;
    let origen = celdas[2].innerText;
    let destino = celdas[3].innerText;
    let fecha = celdas[4].innerText;
    let duracion = parseInt(celdas[5].innerText);
    let precio = parseFloat(celdas[6].innerText);
    let asientosTotales = parseInt(celdas[7].innerText);
    let asientosDisponibles = parseInt(celdas[8].innerText);
    let oferta = celdas[9].innerText;
    datos.push({ id: id, empresa: empresa, origen: origen, destino: destino, fecha: fecha, duracion: duracion, precio: precio, asientosTotales: asientosTotales, asientosDisponibles: asientosDisponibles, oferta: oferta});
}

// Paso 2: Ordenar los datos por edad
datos.sort((a, b) => a.duracion - b.duracion);

// Paso 3: Actualizar la tabla HTML con los datos ordenados
let contenidoTabla = "";
    for (let viaje of datos) {
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