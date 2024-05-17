document.addEventListener('DOMContentLoaded', function () {
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
        console.log("Gmail:", gmail);
        console.log("Password:", password);
        if (gmail.endsWith("@deustoadvisor.es")) {
            const respuesta = await fetch('http://localhost:8080/sql/buscarTrabajador/' + gmail + '/' +  password,  {     
                method: 'GET',
                headers: {
                    'Accept-Type': 'application/json'
                }
            });

            if(respuesta.ok) {
                const trabajador = await respuesta.json();
                console.log("Trabajador:", trabajador);
                const params = new URLSearchParams();
                params.append('trabajador', JSON.stringify(trabajador));
                window.location.href = 'indexTrabajador.html?' + params.toString();
            } else {
                alert("Usuario o contraseña incorrectos");
            }
        } else {
            const response = await fetch('http://localhost:8080/sql/buscarCliente/' + gmail + '/' +  password,  {
                method: 'GET',
                headers: {
                        'Content-Type': 'application/json'
                }
            });
            
            if(response.ok) {
                window.location.href = "../templates/indexCliente.html";
            } else {
                alert("Usuario o contraseña incorrectos");
            }
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
