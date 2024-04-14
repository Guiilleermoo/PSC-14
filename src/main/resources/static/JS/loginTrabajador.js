document.addEventListener('DOMContentLoaded', function () {
    // Your code here
    let boton = document.getElementById("btnLogin");

    boton.addEventListener("click", evento => {

        loginTrabajador();
    });



    let loginTrabajador = async () => {

        const dni = document.getElementById("dni").value;

        const response = await fetch('http://localhost:8080/sql/buscarTrabajador/' + dni, {

            method: 'GET',
        });
    }
    
});
