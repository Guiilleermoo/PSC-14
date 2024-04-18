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
        let boton = document.getElementById("crear");
    
        boton.addEventListener("click", evento => {
    
            crearCliente();
        });
    
    
    
        let crearCliente = async () => {
    
            const DNI = document.getElementById("DNi").value;
            const nombre = document.getElementById("nombre").value;
            const gmail = document.getElementById("gmail").value;
            const telefono = document.getElementById("telefono").value;
            const residencia = document.getElementById("residencia").value;
            const contrasena = document.getElementById("contrasena").value;
            
    
            const response = await fetch('http://localhost:8080/sql/crearCliente', {
    
                method: 'POST',
                body: JSON.stringify({
                    dni: DNI,
                    nombre: nombre,
                    gmail: gmail, 
                    telefono: telefono,   
                    residencia: residencia,
                    
                }),
                headers: {
                    'Content-Type': 'application/json'
                }
            });
        }
        
    });
    