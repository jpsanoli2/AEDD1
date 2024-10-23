package sistemaAutogestion;

public class ListaEstudiante {
    private Nodo head;
    
    private class Nodo {
    Estudiante estudiante;
    Nodo siguiente;
   
        Nodo(Estudiante estudiante){
            this.estudiante = estudiante;
            this.siguiente = null;
        }
    }
    public ListaEstudiante(){
        this.head = null;
    }
    
    public void Add(Estudiante e) {
    Nodo nuevoNodo = new Nodo(e);

    if (head == null || e.GetNumero() < head.estudiante.GetNumero()) {
        nuevoNodo.siguiente = head;
        head = nuevoNodo;
    } else {
        Nodo actual = head;
        while (actual.siguiente != null && actual.siguiente.estudiante.GetNumero() < e.GetNumero()) {
            actual = actual.siguiente;
        }
        nuevoNodo.siguiente = actual.siguiente;
        actual.siguiente = nuevoNodo;
    }
}
    
    public Estudiante Get(int id){
        Nodo actual = head;
        while(actual != null){
            if(actual.estudiante.GetNumero() == id){
                return actual.estudiante;
            }
            actual = actual.siguiente;
        }
        return null;
    }
    public boolean Remove(int id){
        Estudiante estudiante = Get(id);
        if (estudiante == null) {
            return false;
        }
        if (head.estudiante.GetNumero() == id) {
            head = head.siguiente;
            return true;
        }
        Nodo actual = head;
        while (actual.siguiente != null) {
            if (actual.siguiente.estudiante.GetNumero() == id) {
                actual.siguiente = actual.siguiente.siguiente;
                return true;
            }
            actual = actual.siguiente;
        }
        return false;
    }
    public String listarEstudiantes() {
        StringBuilder resultado = new StringBuilder();
        Nodo actual = head;
        while (actual != null) {
            resultado.append(actual.estudiante.toString());

            if (actual.siguiente != null) {
                resultado.append("|");
            }
            actual = actual.siguiente;
        }
        return resultado.toString();
    }
}