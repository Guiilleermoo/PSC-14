# PSC-14
Proyecto de Proceso de Software y calidad

PASOS A SEGUIR PARA EJECUTAR EL PROYECTO

    FASE 1 -->

    Ponemos en la terminal, el path del proyecto y los siguientes comandos:
        mvn compile
        mvn package

    Para iniciar el servidor:
        mvn spring-boot:run
    
    El servidor se iniciará correctamente y el index.html será visible en localhost:8080/templates/index.html. A partir de este punto se podrá navegar por los distintos archivos .html desde el navegador.

    FASE 2 -->

    Para ejecutar los JUnit test:
        mvn test

    Una vez terminado utilizamos el comando mvn clear para borrar lo preciamente copilado