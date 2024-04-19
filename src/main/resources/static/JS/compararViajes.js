const queryString = window.location.search;

const urlParams = new URLSearchParams(queryString);

const viajeString = urlParams.get('viaje');

const viaje = JSON.parse(viajeString);

const detallesViaje = document.getElementById('detalles-viaje');

const origenDestino = document.createElement('p');
origenDestino.textContent = 'Origen: ' + viaje.origen + ' - Destino: ' + viaje.destino;
detallesViaje.appendChild(origenDestino);

const fechaViaje = document.createElement('p');
fechaViaje.textContent = 'Fecha del Viaje: ' + viaje.fecha;
detallesViaje.appendChild(fechaViaje);
