document.addEventListener('DOMContentLoaded', function () {
    // Your code here
    //Ejemplo con cookies
    /*let boton = document.getElementById("btnLogin");

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
    */

    let botonRegis = document.getElementById("btnRegis");

    botonRegis.addEventListener("click", evento => {

       
        window.location.href = "../templates/Registro.html";
        
    });

    let botonLogin = document.getElementById("btnLogin");
    botonLogin.addEventListener("click", async evento => {

        comprobarLogin();

    });

    let comprobarLogin = async () => {
        const DNI = document.getElementById("dni").value;
        const gmail = document.getElementById("email").value;
        const response = await fetch('http://localhost:8080/sql/buscarCliente/' + DNI + '/' +  gmail,  {
                
                method: 'GET',
                headers: {
                    'Content-Type': 'application/json'
                }
        });
        if(response.status === 200){
            window.location.href = "../templates/MisViajes.html";
        }else{
            alert("Email o DNI incorrectos");
        }

    };
        
    
    
});
