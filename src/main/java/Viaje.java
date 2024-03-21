public class Viaje {

    protected String origen=null;
    protected String destino=null;
    protected Date fecha=null;
    protected int duracion=null;
    protected int precio=null;
    protected int oferta=null; //Porcentaje de descuento
    protected String empresa=null;
    
    protected Cliente() {
    }

    public Viaje(String origen, String destino, Date fecha, int duracion, int precio, int oferta, String empresa) {
        this.origen = origen;
        this.destino = destino;
        this.fecha = fecha;
        this.duracion = duracion;
        this.precio = precio;
        this.oferta = oferta;
        this.empresa = empresa;
    }
    
    public String getOrigen() {
        return origen;
    }

    public setOrigen(String origen) {
        if(origen!=null && origen!=destino) {
            this.origen = origen;
        } else {
            throw new IllegalArgumentException("El origen no puede ser nulo o el mismo que el destino");
        }
    }

    public String getDestino() {
        return destino;
    }
    
    public void setDestino(String destino) {
        if(origen!=destino && destino!=null) {
            this.destino = destino;
        } else {
            throw new IllegalArgumentException("El destino no puede ser el mismo que el origen o nulo");
        }
    }
    
    public Date getFecha() {
        return fecha;
    }
    
    public void setFecha(Date fecha) {
        if(fecha.after(new Date()) || fecha.equals(new Date())) {
            this.fecha = fecha;
        } else {
            throw new IllegalArgumentException("La fecha no puede ser anterior a la actual");
        }
    }
    
    public int getDuracion() {
        return duracion;
    }
    
    public void setDuracion(int duracion) {
        if(duracion>0 && duracion!=null) {
            this.duracion = duracion;
        } else {
            throw new IllegalArgumentException("La duración no puede ser null ni negativa");
        }
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        if(precio>0 && precio!=null) {
            this.precio = precio;
        } else {
            throw new IllegalArgumentException("El precio no puede ser null ni negativo");
        }
    }

    public int getOferta() {
        return oferta;
    }

    public void setOferta(int oferta) {
        if(oferta>=0 && oferta<=100 && oferta!=null) {
            this.oferta = oferta;
        } else {
            throw new IllegalArgumentException("La oferta no puede ser null ni negativa ni mayor que 100");
        }
    }

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        if(empresa!=null) {
            this.empresa = empresa;
        } else {
            throw new IllegalArgumentException("La empresa no puede ser nula");
        }
    }

    @Override
    public String toString() {
        return "Viaje{" +
                "origen='" + origen + '\'' +
                ", destino='" + destino + '\'' +
                ", fecha='" + fecha + '\'' +
                ", duracion='" + duracion + '\'' +
                ", precio='" + precio + '€\'' +
                ", oferta='" + oferta + '%\'' +
                ", empresa='" + empresa + '\'' +
                "}";
    }
}