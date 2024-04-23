window.onload = function () {
    listarViajes();
    marcarFavoritos();
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
            <i onClick="compararViaje(${viaje.id}) "class="material-icons button search">search</i>
            </td>
            <td>
            <i id="estrella-${viaje.id}" onClick="favorito(${viaje.id})"class="material-icons button star">star</i>
            </td>
            <td>
            <i onClick="reservarViaje(${viaje.id})"class="material-icons button check_circle">check_circle</i>
            </td>
        </tr>
        ` 
        contenidoTabla += contenidoFila;
    }
    document.querySelector("#tabla tbody").outerHTML = contenidoTabla;
}

async function marcarFavoritos() {
    const gmail = obtenerValorCookie("email");
    const busquedaCliente = await fetch('http://localhost:8080/sql/buscarCliente/' + gmail, {
        method: 'GET',
        headers: {
            'Accept': 'application/json'
        },
    });
    const cliente = await busquedaCliente.json();

    const peticionFavoritos = await fetch('http://localhost:8080/sql/buscarFavorito/' + cliente.dni, {
        method: 'GET',
        headers: {
            'Accept': 'application/json',   
        },
    });
    const favoritos = await peticionFavoritos.json();

    favoritos.forEach(favorito => {
        const estrella = document.getElementById("estrella-" + favorito.viaje.id);
        if (estrella) {
            estrella.style.color = "yellow";
        }
    });
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
                <i onClick="compararViaje(${viaje.id}) "class="material-icons button search">search</i>
                </td>
                <td>
                <i id="estrella-${viaje.id}" onClick="favorito(${viaje.id})"class="material-icons button star">star</i>
                </td>
                <td>
                <i onClick="reservarViaje(${viaje.id})"class="material-icons button check_circle">check_circle</i>
                </td>
            </tr>
            ` 
            contenidoTabla += contenidoFila;
        }
        document.querySelector("#tabla tbody").outerHTML = contenidoTabla;
    marcarFavoritos();
}

let idEditar;

let compararViaje = async (id) => {

        idEditar = id;
    
        const peticion = await fetch('http://localhost:8080/sql/buscarViaje/' + id, {
    
            method: 'GET',
        
            headers: {
                'Accept': 'application/json',
                
            },
        });
        
        const viaje = await peticion.json();
        const params = new URLSearchParams();
        params.append('viaje', JSON.stringify(viaje));
        window.location.href = 'CompararViajes.html?' + params.toString(); 
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

let favorito = async (id) => {
    const estrella = document.getElementById("estrella-" + id);
    if (estrella.style.color === "yellow") {
        await eliminarDeFavoritos(id);
        estrella.style.color = "";
    } else {
        await agregarAFavoritos(id);
        estrella.style.color = "yellow";
    }
};

async function agregarAFavoritos(id) {
    idEditar = id;

    const peticion = await fetch('http://localhost:8080/sql/buscarViaje/' + id, {
        method: 'GET',
        headers: {
            'Accept': 'application/json',   
        },
    });

    const viaje = await peticion.json();
    const gmail = obtenerValorCookie("email");

    const busqueda = await fetch('http://localhost:8080/sql/buscarCliente/' + gmail, {
        method: 'GET',
        headers: {
            'Accept': 'application/json'  
        },
    });

    const cliente = await busqueda.json();

    const response = await fetch('http://localhost:8080/sql/anadirFavorito/' + cliente.dni + "/" + viaje.id, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        }
    });

    if(response.ok) {
        console.log("Añadido a favoritos")
    } else {
        console.error("Error al añadir a favoritos")
    }
}

async function eliminarDeFavoritos(id) {
    idEditar = id;

    const gmail = obtenerValorCookie("email");

    const busqueda = await fetch('http://localhost:8080/sql/buscarCliente/' + gmail, {
        method: 'GET',
        headers: {
            'Accept': 'application/json'  
        },
    });

    const cliente = await busqueda.json();
    

    const peticion = await fetch('http://localhost:8080/sql/buscarFavorito/' + cliente.dni, {
        method: 'GET',
        headers: {
            'Accept': 'application/json',   
        },
    });

    const favorito = await peticion.json();
    let campos = {};

    for(let f of favorito) {
        if(f.viaje.id === id && f.cliente.dni === cliente.dni) {
            campos.id = f.id;
            const response = await fetch('http://localhost:8080/sql/eliminarFavorito', {
                method: 'DELETE',
                body: JSON.stringify(campos),
                headers: {
                    'Content-Type': 'application/json'
                }
            });
            if(response.ok) {
                console.log("Eliminado de favoritos")
            } else {
                console.error("Error al eliminar de favoritos")
            }
        }
    }
}

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