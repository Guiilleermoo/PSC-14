(function () {
    'use strict'
    const forms = document.querySelectorAll('.requires-validation')
    Array.from(forms)
      .forEach(function (form) {
        form.addEventListener('submit', function (event) {
          if (!form.checkValidity()) {
            event.preventDefault()
            event.stopPropagation()
          }
    
          form.classList.add('was-validated')
        }, false)
      })
    })()

document.addEventListener('DOMContentLoaded', function () {
        // Your code here
        let boton = document.getElementById("submit");
    
        boton.addEventListener("click", evento => {
    
            añadirViaje();
        });
    
    
    
        let añadirViaje = async () => {
    
            const id = document.getElementById("idViaje").value;
            const empresa = document.getElementById("empresa").value;
            const origen = document.getElementById("origen").value;
            const destino = document.getElementById("destino").value;
            const fecha = document.getElementById("fecha").value;
            const duracion = document.getElementById("duración").value;
            const precio = document.getElementById("precio").value;
            const asientosTotales = document.getElementById("asientosTotales").value;
            const asientosDisponibles = document.getElementById("asientosDisponibles").value;
            const oferta = document.getElementById("oferta").value;
    
            const response = await fetch('http://localhost:8080/sql/crearViaje', {
    
                method: 'POST',
                body: JSON.stringify({
                    id: id,
                    empresa: empresa,
                    origen: origen, 
                    destino: destino,   
                    fecha: fecha,
                    duracion: duracion,
                    precio: precio,
                    asientosTotales: asientosTotales,
                    asientosDisponibles: asientosDisponibles,
                    oferta: oferta
                }),
                headers: {
                    'Content-Type': 'application/json'
                }
            });
        }
        
    });
    