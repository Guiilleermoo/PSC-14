package es;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
;

@SpringBootApplication
public class TravelAdvisorApplication implements CommandLineRunner{


    public static void main(String[] args) {
        SpringApplication.run(TravelAdvisorApplication.class, args);
    }
  /**
     * Este metodo ejecuta el servidor.
    */
    @Override
    public void run(String... args) throws Exception {
        System.out.println("");
        System.out.println("¡Servidor Iniciado correctamente!");
    }
    
}
