window.onload = function () {
    listarTrabajador();
    
}
const queryString = window.location.search;

const urlParams = new URLSearchParams(queryString);

const trabajadorString = urlParams.get('trabajador');

const trabajador = JSON.parse(trabajadorString);

let listarTrabajador = async () => {
    let contenidoTabla = "";
          
                let contenidoFila = `<tr>
                    <td  class="Especial">Id</td>
                    <td>${trabajador.dni}</td>
                    </tr>
                    <tr>
                    <td class="Especial">Empresa</td>
                    <td>${trabajador.nombre}</td>
                    </tr>
                    <tr>
                    <td class="Especial">Origen</td>
                    <td>${trabajador.gmail}</td>
                    </tr>
                    <tr>
                    <td class="Especial">Destino</td>
                    <td>${trabajador.telefono}</td>
                    </tr>
                    <tr>
                    <td class="Especial">Fecha</td>
                    <td>${trabajador.sueldo}</td>
                    </tr>
                    <tr>
                    <td class="Especial">Duracion</td>
                    <td>${trabajador.password}</td>
                    </tr>
                    ` 
                contenidoTabla += contenidoFila;
        
        document.querySelector("#tabla tbody").outerHTML = contenidoTabla;
}