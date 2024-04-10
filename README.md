# PSC-14
Proyecto de Proceso de Software y calidad

PASOS A SEGUIR PARA EJECUTAR EL PROYECTO

    FASE 1 -->

    Ponemos en la terminal, el path del proyecto y los siguientes comandos:
        mvn compile
        mvn package

    Para iniciar el servidor hay que darle al botón run en la clase main (TravelAdvisorApplication.java). El servidor se quedara esperando a que le mandemos las peticiones desde el lado cliente.

    El lado cliente se encuentra en la carpeta templates. Index.html ejecutamos el html. Nosotros usamos una extensión (Live Server) que ejecuta el html en el puerto 5500 con la ip 127.0.0.1:5500/. 
    Si no tienes esta extensión puedes descargartela o borrar lo que te aparece en la url desde src y poner  http://127.0.0.1:5500/

    Una vez terminado utilizamos el comando mvn clear para borrar lo preciamente copilado