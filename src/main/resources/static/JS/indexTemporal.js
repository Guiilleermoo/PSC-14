function redirigirMisViajes() {
    window.location.href = "../templates/MisViajes.html";
}
function redirigirReserva() {
    window.location.href = "../templates/Reserva.html";
}
function redirigirEditarViajes() {
    window.location.href = "../templates/EditarViajes.html";
}
function redirigirAñadirOferta() {
    window.location.href = "../templates/AñadirOfertas.html";
}
document.addEventListener("DOMContentLoaded", function() {
    var esCliente= document.cookie.split('; ').find(row => row.startsWith('esCliente=')).split('=')[1] === 'true';
    //console.log(esCliente);
    
    if (esCliente) {
        var añadirViajesButton = document.getElementById("añadirViajes");
        var editarViajesButton = document.getElementById("editarViajes");

        if (añadirViajesButton) {
            añadirViajesButton.style.display = 'none';
        }

        if (editarViajesButton) {
            editarViajesButton.style.display = 'none';
        }
    }
});