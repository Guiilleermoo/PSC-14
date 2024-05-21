# PSC-14
## Proyecto de Proceso de Software y Calidad

### PASOS A SEGUIR PARA EJECUTAR EL PROYECTO

#### FASE DE EJECUCIÓN
1. Ponemos en la terminal, el path del proyecto y los siguientes comandos:
    ```bash
    mvn compile
    mvn package
    ```
2. Ejecutamos el script que crea la BD
    ```bash
    mysql -u root -p 
    ```
3. Ponemos la contraseña que tengamos con el usuario root
4. Ejecutamos el siguiente comando
     ```bash
    source sql/schema-BD.sql
    ```
5. Se ejecutara el script y para salir simplemente
      ```bash
    exit
    ```

6. Para iniciar el servidor:
    ```bash
    mvn spring-boot:run
    ```
7. El servidor se iniciará correctamente y el `index.html` será visible en [localhost:8080/templates/index.html](http://localhost:8080/templates/index.html). A partir de este punto se podrá navegar por los distintos archivos `.html` desde el navegador.

#### TESTS
Hay dos tipos de Test, por un lado, los Test unitarios y por otro lado los Test de rendimiento.

1. Para ejecutar los tests unitarios, que han sido implementados con JUnit. Ademas hemos incluido mockito y jacoco. Esta ultima herramienta te permite visualizar la cobertura de instrucciones y ramas (El index.html que genera jacoco se encuentra en rutaDondeTengasGuardadoElProyecto\PSC-14\target\site\jacoco):
    ```bash
    mvn test -Punitary-tests
    ```

2. Para ejecutar los tests de rendimiento. Lo hemos realizado a traves de JUnitPerf. Se crea una carpeta en target llamada junitperf con un report.html donde visualizar los tests:
    ```bash
    mvn test -Pperformance-tests
    ```

3. Una vez terminado, utilizamos el comando `mvn clean` para borrar lo previamente compilado.
    ```bash
    mvn clean
    ```
#### DATOS DE PRUEBA
1. Para hacer login con el trabajador:
    ```bash
    DNI: 21121546C
    Email: trabajador1@deustoadvisor.es
    Password: trabajador123
    ```
2. Para hacer login con el cliente:
    ```bash
    DNI: 14235378L
    Email: prueba@gmail.com
    Password: prueba123
    ```