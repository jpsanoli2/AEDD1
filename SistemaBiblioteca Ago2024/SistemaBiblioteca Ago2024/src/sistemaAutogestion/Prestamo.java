package sistemaAutogestion;

public class Prestamo {
    private String ISBN;
    private int numero;
    private String estado = "activo";
    
    public Prestamo(String ISBN, int numero){
        this.ISBN = ISBN;
        this.numero = numero;
    }
    public void ToFinalizado(){
        estado = "finalizado";
    }
    public String GetISBN(){
        return this.ISBN;
    }
    public int GetNumero(){
        return this.numero;
    }
    public String GetEstado(){
        return this.estado;  
    }
}
