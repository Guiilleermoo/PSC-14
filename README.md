# PSC-14
## Proyecto de Proceso de Software y Calidad

### PASOS A SEGUIR PARA EJECUTAR EL PROYECTO

#### FASE 1
1. Ponemos en la terminal, el path del proyecto y los siguientes comandos:
    ```bash
    mvn compile
    mvn package
    ```
2. Para iniciar el servidor:
    ```bash
    mvn spring-boot:run
    ```
3. El servidor se iniciará correctamente y el `index.html` será visible en [localhost:8080/templates/index.html](http://localhost:8080/templates/index.html). A partir de este punto se podrá navegar por los distintos archivos `.html` desde el navegador.

#### FASE 2
1. Para ejecutar los JUnit tests:
    ```bash
    mvn test
    ```
2. Una vez terminado, utilizamos el comando `mvn clean` para borrar lo previamente compilado.
