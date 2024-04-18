document.addEventListener('DOMContentLoaded', function () {
    // Your code here
    let boton = document.getElementById("btnLogin");

    boton.addEventListener("click", evento => {

        loginTrabajador();
    });

    let botonRegis = document.getElementById("btnRegis");

    botonRegis.addEventListener("click", evento => {

       
        window.location.href = "../templates/AñadirOfertas.html";
        
    });


    let loginTrabajador = async () => {

        const dni = document.getElementById("dni").value;

        const response = await fetch('http://localhost:8080/sql/buscarTrabajador/' + dni, {

            method: 'GET',
        });
    }
    
    
});
