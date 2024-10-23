package sistemaAutogestion;

public class ListaLibro {
    private Nodo head;
    
    private class Nodo {
    Libro libro;
    Nodo siguiente;
   
        Nodo(Libro libro){
            this.libro = libro;
            this.siguiente = null;
        }
    }
    public ListaLibro(){
        this.head = null;
    }
    public void Add(Libro l){
        Nodo nuevoNodo = new Nodo(l);
        String isbnNuevo = l.GetISBN();
        if(head == null || isbnNuevo.compareTo(head.libro.GetISBN()) < 0){
            nuevoNodo.siguiente = head;
            head = nuevoNodo;
        }
        else{
            Nodo actual = head;
            while(actual.siguiente != null && actual.siguiente.libro.GetISBN().compareTo(isbnNuevo) < 0){
                actual = actual.siguiente;
            }
            nuevoNodo.siguiente = actual.siguiente;
            actual.siguiente = nuevoNodo;
        }
    }
    
    public Libro Get(String ISBN){
        Nodo actual = head;
        while(actual != null){
            if(actual.libro.GetISBN().equals(ISBN)){
                return actual.libro;
            }
            actual = actual.siguiente;
        }
        return null;
    }
    public boolean Remove(String ISBN){
        Libro libro = Get(ISBN);
        if (libro == null) {
            return false;
        }
        if (head.libro.GetISBN().equals(ISBN)) {
            head = head.siguiente;
            return true;
        }
        Nodo actual = head;
        while (actual.siguiente != null) {
            if (actual.siguiente.libro.GetISBN().equals(ISBN)) {
                actual.siguiente = actual.siguiente.siguiente;
                return true;
            }
            actual = actual.siguiente;
        }
        return false;
    }
    public String listarLibros() {
        StringBuilder resultado = new StringBuilder();
        Nodo actual = head;
        while (actual != null) {
            resultado.append(actual.libro.toString());

            if (actual.siguiente != null) {
                resultado.append("|");
            }
            actual = actual.siguiente;
        }
        return resultado.toString();
    }
    
    
    
    
    
    
    
    public String listarLibros(String categoria) {
        StringBuilder resultado = new StringBuilder();
        listarLibrosRecursivo(head, categoria, resultado);
        return resultado.toString();
    }

    
    private void listarLibrosRecursivo(Nodo actual, String categoria, StringBuilder resultado) {
        if (actual == null) {
            return;
        }
        listarLibrosRecursivo(actual.siguiente, categoria, resultado);

        if (actual.libro.GetCategoria().equals(categoria)) {
            resultado.append(actual.libro.GetNombre())
                .append("#")
                .append(actual.libro.GetISBN())
                .append("#")
                .append(actual.libro.GetCategoria())
                .append("|");
        }
    }

}
