package sistemaAutogestion;

public class ListaPrestamo {
    private Nodo head;
    
    private class Nodo {
    Prestamo prestamo;
    Nodo siguiente;
   
        Nodo(Prestamo prestamo){
            this.prestamo = prestamo;
            this.siguiente = null;
        }
    }
    public ListaPrestamo(){
        this.head = null; 
    }
    public void Add(Prestamo p){
        Nodo nuevoNodo = new Nodo(p);
        if(head == null){
            head = nuevoNodo;
        }
        else{
            Nodo actual = head;
            while(actual.siguiente != null){
                actual = actual.siguiente;
            }
            actual.siguiente = nuevoNodo;
        }
    }
    public Prestamo Get(String ISBN, int id){
        Nodo actual = head;
        while(actual != null){
            if(actual.prestamo.GetISBN().equals(ISBN) && actual.prestamo.GetNumero() == id){
                return actual.prestamo;
            }
            actual = actual.siguiente;
        }
        return null;
    }
    public Prestamo GetXISBN(String ISBN){
        Nodo actual = head;
        while(actual != null){
            if(actual.prestamo.GetISBN().equals(ISBN)){
                return actual.prestamo;
            }
            actual = actual.siguiente;
        }
        return null;
    }
    public boolean Remove(String ISBN, int id){
        Prestamo prestamo = Get(ISBN, id);
        if (prestamo == null) {
            return false;
        }
        if (head.prestamo.GetISBN().equals(ISBN) && head.prestamo.GetNumero() == id) {
            head = head.siguiente;
            return true;
        }
        Nodo actual = head;
        while (actual.siguiente != null) {
            if (actual.siguiente.prestamo.GetISBN().equals(ISBN) && actual.siguiente.prestamo.GetNumero() == id) {
                actual.siguiente = actual.siguiente.siguiente;
                return true;
            }
            actual = actual.siguiente;
        }
        return false;
    }
    public int PrestamosActivos(int numero){
        int contador = 0;
        Nodo actual = head;
        while(actual != null){
            Prestamo prestamo = actual.prestamo;
            if(prestamo.GetNumero() == numero && prestamo.GetEstado() == "activo"){
                contador++;
            }
            actual = actual.siguiente;
        }
        return contador;
    } 
    
}
