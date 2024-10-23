package sistemaAutogestion;

public class Reserva {
    private String ISBN;
    private int numero;
    
    public Reserva(String ISBN, int numero){
        this.ISBN = ISBN;
        this.numero = numero;
    }
    public String GetISBN(){
        return this.ISBN;
    }
    public int GetNumero(){
        return this.numero;
    }
}
