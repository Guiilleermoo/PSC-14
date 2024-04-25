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
            <i onClick="editarViaje(${viaje.id})"class="material-icons button edit">edit</i>
            <i onClick="borrarViaje(${viaje.id})"class="material-icons button delete">delete</i>
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

let borrarViaje = async (id) => {
    const peticion = await fetch('http://localhost:8080/sql/eliminarViaje', {

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

let idEditar;

let editarViaje = async (id) => {

    mostrarFormulario();

    idEditar = id;

    const peticion = await fetch('http://localhost:8080/sql/buscarViaje/' + id, {

        method: 'GET',
     
        headers: {
            'Accept': 'application/json',
            
        },
    });
    
    const viaje = await peticion.json();

    document.getElementById("idViaje").value = viaje.id;
    document.getElementById("empresa").value = viaje.empresa;
    document.getElementById("origen").value = viaje.origen;
    document.getElementById("destino").value = viaje.destino;
    document.getElementById("fecha").value = viaje.fecha;
    document.getElementById("duracion").value = viaje.duracion;
    document.getElementById("precio").value = viaje.precio;
    document.getElementById("asientosTotales").value = viaje.asientosTotales;
    document.getElementById("asientosDisponibles").value = viaje.asientosDisponibles;
    document.getElementById("oferta").value = viaje.oferta;

    let btnModificar = document.getElementById("registrar");

    btnModificar.addEventListener("click", evento => {

    aplicarActualizacion(idEditar);
});
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


let aplicarActualizacion = async (id) => {

    let campos = {};
    campos.id = id;
    campos.empresa = document.getElementById("empresa").value;
    campos.origen = document.getElementById("origen").value;
    campos.destino = document.getElementById("destino").value;
    campos.fecha = document.getElementById("fecha").value;
    campos.duracion = document.getElementById("duracion").value;
    campos.precio = document.getElementById("precio").value;
    campos.asientosTotales = document.getElementById("asientosTotales").value;
    campos.asientosDisponibles = document.getElementById("asientosDisponibles").value;
    campos.oferta = document.getElementById("oferta").value;
   
    const p = await fetch('http://localhost:8080/sql/editarViaje/' + id, {
        method: 'PUT',
        body: JSON.stringify(campos),
        headers: {
            'Content-Type': 'application/json',
            'Accept': 'application/json'    
        }
    });
    
    if(p.ok){
        const gmail = obtenerValorCookie("gmail");

        const response = await fetch('http://localhost:8080/sql/buscarTrabajador/' + gmail, {
            method: 'GET',
            headers: {
                'Accept': 'application/json'    
            }
        });

        const trabajador = await response.json();
        console.log(trabajador);

        const mensaje = "El trabajador " + trabajador.nombre + " ha modificado el viaje con id " + id;

        fetch('http://localhost:8080/api/registrarCambios', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({ mensaje: mensaje })
        })
        .then(response => {
            if (response.ok) {
                console.log("Mensaje de log enviado exitosamente");
            } else {
                console.error("Error al enviar mensaje de log:", response.status);
            }
        })
        .catch(error => {
            console.error("Error al enviar mensaje de log:", error);
        });
    } else {
        alert("Error editando el viaje");
    }

    listarViajes();
}

function mostrarFormulario() {
    let formulario = document.getElementById("formulario").style.visibility = "visible";
}