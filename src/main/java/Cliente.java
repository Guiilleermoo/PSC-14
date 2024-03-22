public class Cliente {

    protected String dni=null;
    protected String nombre=null;
    protected String gmail=null;
    protected String telefono=null;
    protected String residencia=null;

    protected Cliente() {
    }

    public Cliente(String dni, String nombre, String gmail, String telefono, String residencia) {
        this.dni = dni;
        this.nombre = nombre;
        this.gmail = gmail;
        this.telefono = telefono;
        this.residencia = residencia;
    }
    public String getDNI() {
        return dni;
    }

    public String getNombre() {
        return nombre;
    }

    public String getGmail() {
        return gmail;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getResidencia() {
        return residencia;
    }

    public void setDNI(String dni) {
        //Comprobación que los 8 primeros caracteres son números
        for(int i = 0; i < 8; i++) {
                if(!Character.isDigit(dni.charAt(i))) {
                    throw new IllegalArgumentException("El DNI no es válido");
                }
        }
        //Comprobación que el último caracter es una letra
        if(dni.length() != 9) {
            throw new IllegalArgumentException("El DNI no es válido");
        } else {
            this.dni = dni;
        }  
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setGmail(String gmail) {
        if(gmail.contains("@") && gmail.contains(".")) {
            this.gmail = gmail;
        } else {
            throw new IllegalArgumentException("El correo no es válido");
        }
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
        if(telefono.length() != 9) { //Comprobacion que el teléfono tiene 9 caracteres
            throw new IllegalArgumentException("El teléfono no es válido");
        } else {
            for(int i = 0; i < 9; i++) {
                if(!Character.isDigit(telefono.charAt(i))) { //Comprobación que todos los caracteres son números
                    throw new IllegalArgumentException("El teléfono no es válido");
                }
            this.telefono = telefono;
            }
        }
    }

    public void setResidencia(String residencia) {
        this.residencia = residencia;
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "dni='" + dni + '\'' +
                "nombre='" + nombre + '\'' +
                ", gmail='" + gmail + '\'' +
                ", telefono='" + telefono + '\'' +
                ", residencia='" + residencia + '\'' +
                '}';
    }
}