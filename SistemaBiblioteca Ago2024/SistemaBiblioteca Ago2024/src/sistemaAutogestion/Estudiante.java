package sistemaAutogestion;

public class Estudiante {
    private String nombre;
    private String apellido;
    private int numeroIdentificador;
    
    public Estudiante(String nombre, String apellido, int numeroIdentificador){
        this.nombre = nombre;
        this.apellido = apellido;
        this.numeroIdentificador = numeroIdentificador;
    }
    
    public int GetNumero(){
        return this.numeroIdentificador;
    }
    
    public boolean ValidarNombre(){
        return !(nombre == null || nombre.trim().isBlank() || apellido == null || apellido.trim().isBlank());
    }
    public boolean ValidarNumero(){
        return !(numeroIdentificador <= 0 || numeroIdentificador > 500000);
    }
    
    @Override
    public String toString() {
        return nombre + "#" + apellido + "#" + numeroIdentificador;
    }
}
