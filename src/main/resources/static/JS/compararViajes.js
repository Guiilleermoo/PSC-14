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
                    <td>${viaje.duracion}</td>
                    </tr>
                    <tr>
                    <td class="Especial">Precio</td>
                    <td>${viaje.precio}</td>
                    </tr>
                    <tr>
                    <td class="Especial">Asientos Total</td>
                    <td>${viaje.asientosTotales}</td>
                    </tr>
                    <tr>
                    <td class="Especial">Asientos Disponibles</td>
                    <td>${viaje.asientosDisponibles}</td>
                    </tr>
                    <tr>
                    <td class="Especial">Oferta</td>
                    <td>${viaje.oferta}</td>
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
            
        </tr>
        ` 
        contenidoTabla += contenidoFila;
    }
    document.querySelector("#tabla2 tbody").outerHTML = contenidoTabla;
}


const detallesViaje = document.getElementById('detalles-viaje');

const origenDestino = document.createElement('p');
origenDestino.textContent = 'Origen: ' + viaje.origen + ' - Destino: ' + viaje.destino;
detallesViaje.appendChild(origenDestino);

const fechaViaje = document.createElement('p');
fechaViaje.textContent = 'Fecha del Viaje: ' + viaje.fecha;
detallesViaje.appendChild(fechaViaje);
