window.onload = function () {
    listarViaje();
    listarViajes();
   
}
const queryString = window.location.search;

const urlParams = new URLSearchParams(queryString);

const viajeString = urlParams.get('viaje');

const viaje = JSON.parse(viajeString);

let listarViaje = async () => {
    let contenidoTabla = "";
          
                let contenidoFila = `<tr>
                    <td  class="Especial">Id</td>
                    <td>${viaje.id}</td>
                    </tr>
                    <tr>
                    <td class="Especial">Empresa</td>
                    <td>${viaje.empresa}</td>
                    </tr>
                    <tr>
                    <td class="Especial">Origen</td>
                    <td>${viaje.origen}</td>
                    </tr>
                    <tr>
                    <td class="Especial">Destino</td>
                    <td>${viaje.destino}</td>
                    </tr>
                    <tr>
                    <td class="Especial">Fecha</td>
                    <td>${viaje.fecha}</td>
                    </tr>
                    <tr>
                    <td class="Especial">Duracion</td>
                    <td id="duracionPrincipal">${viaje.duracion}</td>
                    </tr>
                    <tr>
                    <td class="Especial">Precio</td>
                    <td id="precioPrincipal">${viaje.precio}</td>
                    </tr>
                    <tr>
                    <td class="Especial">Asientos Total</td>
                    <td>${viaje.asientosTotales}</td>
                    </tr>
                    <tr>
                    <td class="Especial">Asientos Disponibles</td>
                    <td id="asientosDisponiblesPrincipal">${viaje.asientosDisponibles}</td>
                    </tr>
                    <tr>
                    <td class="Especial">Oferta</td>
                    <td id="ofertaPrincipal">${viaje.oferta}</td>
                    </tr>
                    ` 
                contenidoTabla += contenidoFila;
        
        document.querySelector("#tabla tbody").outerHTML = contenidoTabla;
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
            <td> <i onClick="viajeAComparar(${viaje.id}) "class="material-icons button search">search</i></td>
        </tr>
        ` 
        contenidoTabla += contenidoFila;
    }
    document.querySelector("#tabla2 tbody").outerHTML = contenidoTabla;

    quitarColores(viaje);
}

let viajeAComparar = async (id) => {
    
    idComparar = id;

    const peticion = await fetch('http://localhost:8080/sql/buscarViaje/' + idComparar, {

        method: 'GET',
        headers: {
            'Accept': 'application/json'
        }
    });

    const viajeComparar = await peticion.json();

    let contenidoTabla = "";
    let contenidoFila = `<tr>
            <td>${viajeComparar.id}</td>
            <td>${viajeComparar.empresa}</td>
            <td>${viajeComparar.origen}</td>
            <td>${viajeComparar.destino}</td>
            <td>${viajeComparar.fecha}</td>
            <td id="duracionComparar">${viajeComparar.duracion}</td>
            <td id="precioComparar">${viajeComparar.precio}</td>
            <td>${viajeComparar.asientosTotales}</td>
            <td id="asientosDisponiblesComparar">${viajeComparar.asientosDisponibles}</td>
            <td id="ofertaComparar">${viajeComparar.oferta}</td>
            <td> <i onClick="listarViajes() "class="material-icons button cancel">cancel</i></td>
        </tr>
        ` 
        contenidoTabla += contenidoFila;
    document.querySelector("#tabla2 tbody").outerHTML = contenidoTabla;

    asignarColores(viaje, viajeComparar);
   
}

function asignarColores(viaje, viajeComparar) {
    var duracion = document.getElementById("duracionPrincipal");
    var duracionComparar = document.getElementById("duracionComparar");
    if (viajeComparar.duracion < viaje.duracion) {
        duracionComparar.style.backgroundColor = "green";
        duracion.style.backgroundColor = "red";
    } else if (viajeComparar.duracion > viaje.duracion) {
        duracionComparar.style.backgroundColor = "red";
        duracion.style.backgroundColor = "green";
    }

    var precio = document.getElementById("precioPrincipal");
    var precioComparar = document.getElementById("precioComparar");
    if (viajeComparar.precio < viaje.precio) {
        precioComparar.style.backgroundColor = "red";
        precio.classList.add("compararRojo");
    } else if (viajeComparar.precio > viaje.precio) {
        precioComparar.style.backgroundColor = "red";
        precio.style.backgroundColor = "green";
    }

    var asientosDisponibles = document.getElementById("asientosDisponiblesPrincipal");
    var asientosDisponiblesComparar = document.getElementById("asientosDisponiblesComparar");
    if (viajeComparar.asientosDisponibles > viaje.asientosDisponibles) {
        asientosDisponiblesComparar.style.backgroundColor = "green";
        asientosDisponibles.style.backgroundColor = "red";
    } else if (viajeComparar.asientosDisponibles < viaje.asientosDisponibles) {
        asientosDisponiblesComparar.style.backgroundColor = "red";
        asientosDisponibles.style.backgroundColor = "green";
    }

    var oferta = document.getElementById("ofertaPrincipal");
    var ofertaComparar = document.getElementById("ofertaComparar");
    if (viajeComparar.oferta > viaje.oferta) {
        ofertaComparar.style.backgroundColor = "green";
        oferta.style.backgroundColor = "red";
    } else if (viajeComparar.oferta < viaje.oferta) {
        ofertaComparar.style.backgroundColor = "red";
        oferta.style.backgroundColor = "green";
    }
}

function quitarColores(viaje) {
    var duracion = document.getElementById("duracionPrincipal");
    duracion.style.backgroundColor = "white";

    var precio = document.getElementById("precioPrincipal");
    precio.style.backgroundColor = "white";


    var asientosDisponibles = document.getElementById("asientosDisponiblesPrincipal");
    asientosDisponibles.style.backgroundColor = "white";

    var oferta = document.getElementById("ofertaPrincipal");
    oferta.style.backgroundColor = "white";
}

