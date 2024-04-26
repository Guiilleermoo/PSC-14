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
                    <td  class="Especial">DNI</td>
                    <td>${trabajador.dni}</td>
                    </tr>
                    <tr>
                    <td class="Especial">Nombre</td>
                    <td>${trabajador.nombre}</td>
                    </tr>
                    <tr>
                    <td class="Especial">Gmail</td>
                    <td>${trabajador.gmail}</td>
                    </tr>
                    <tr>
                    <td class="Especial">Telefono</td>
                    <td>${trabajador.telefono}</td>
                    </tr>
                    <tr>
                    <td class="Especial">Sueldo</td>
                    <td>${trabajador.sueldo}</td>
                    </tr>
                    <tr>
                    <td class="Especial">Password</td>
                    <td>${trabajador.password}</td>
                    </tr>
                    ` 
                contenidoTabla += contenidoFila;
        
        document.querySelector("#tabla tbody").outerHTML = contenidoTabla;
}
function redirigirAñadirViajes() {
    window.location.href = "../templates/AñadirOfertas.html";
}
function redirigirModificarViajes() {
    window.location.href = "../templates/EditarViajes.html";
}