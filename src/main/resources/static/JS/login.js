document.addEventListener('DOMContentLoaded', function () {
    // Your code here
    let boton = document.getElementById("btnLogin");

    boton.addEventListener("click", evento => {
        var correo =  document.getElementById("email").value;
        console.log("Dominio:", correo);   
        var dominio = correo.split('@')[1];
        console.log("Dominio:", dominio);   
        
        if (dominio === "cliente.com") {
            const esCliente = true;
            document.cookie = `esCliente=${esCliente}; path=/`;
           // loginCliente();
        } else if (dominio === "trabajador.com") {
            const esCliente = false;
            document.cookie = `esCliente=${esCliente}; path=/`;
            //loginTrabajador();
        } else {
           
           console.log("Email invalido");
        }
        console.log("Cookie esCliente establecida:", document.cookie);

        
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
