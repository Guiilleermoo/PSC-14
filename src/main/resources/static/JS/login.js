document.addEventListener('DOMContentLoaded', function () {
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
        var email =  document.getElementById("email").value;
        console.log("Email:", email);
        document.cookie = `email=${email}; path=/`;
        console.log("Cookie email establecida:", document.cookie);
        comprobarLogin();

    });

    let comprobarLogin = async () => {
        const gmail = document.getElementById("email").value;
        const password = document.getElementById("password").value;
        console.log("Gmail:1", gmail);
        console.log("Password:1", password);
        const response = await fetch('http://localhost:8080/sql/buscarCliente/' + gmail + '/' +  password,  {
                
            method: 'GET',
            headers: {
                    'Content-Type': 'application/json'
            }
        });

        const response2 = await fetch('http://localhost:8080/sql/buscarTrabajador/' + gmail + '/' +  password,  {
                
            method: 'GET',
            headers: {
                'Accept-Type': 'application/json'
            }
        });

        if(response2.status === 200) {
            const trabajador = await response2.json();
            console.log("Trabajador:", trabajador);
            const params = new URLSearchParams();
            params.append('trabajador', JSON.stringify(trabajador));
        }
        

        if(response.status === 200 && response2.status === 404){
            window.location.href = "../templates/indexCliente.html";
        }else if(response.status === 404 && response2.status === 200){
          
            window.location.href = 'indexTrabajador.html?' + params.toString();    
        }else{
            alert("Email o contraseña incorrectos");
        }

            

    };
    /*
    let compararViaje = async (id) => {

        idEditar = id;
    
        const peticion = await fetch('http://localhost:8080/sql/buscarViaje/' + id, {
    
            method: 'GET',
        
            headers: {
                'Accept': 'application/json',
                
            },
        });
        
        const viaje = await peticion.json();
        const params = new URLSearchParams();
        params.append('viaje', JSON.stringify(viaje));
        window.location.href = 'CompararViajes.html?' + params.toString(); 
    };
    */
        
    
    
});
